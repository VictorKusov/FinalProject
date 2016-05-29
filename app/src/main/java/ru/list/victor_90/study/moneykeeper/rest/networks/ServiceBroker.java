package ru.list.victor_90.study.moneykeeper.rest.networks;


import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Date;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.list.victor_90.study.moneykeeper.BaseApplication;
import ru.list.victor_90.study.moneykeeper.constants.Constants;
import ru.list.victor_90.study.moneykeeper.rest.events.LoginResponse;
import ru.list.victor_90.study.moneykeeper.rest.events.RegisterResponse;
import ru.list.victor_90.study.moneykeeper.rest.helpers.BusProvider;
import ru.list.victor_90.study.moneykeeper.rest.events.LoginRequest;
import ru.list.victor_90.study.moneykeeper.rest.events.RegisterRequest;
import ru.list.victor_90.study.moneykeeper.rest.models.Users;
import ru.list.victor_90.study.moneykeeper.rest.networks.api.IBaasApi;
import ru.list.victor_90.study.moneykeeper.rest.networks.deserealizer.DateDeserializer;
import ru.list.victor_90.study.moneykeeper.rest.networks.interceptors.HttpLoggingInterceptor;
import ru.list.victor_90.study.moneykeeper.utils.Logs;

public class ServiceBroker {

    private static final String TAG = ServiceBroker.class.getSimpleName();
    private Retrofit retrofit;
    private static ServiceBroker instance = new ServiceBroker();

    private ServiceBroker(){}

    public static ServiceBroker getInstance() {
        return instance;
    }

    public Retrofit getRetrofit(){
        if(retrofit == null){

            // создаем новое подключение с retrofit
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new HttpLoggingInterceptor())
                    .addNetworkInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request request = chain.request();
                            Headers.Builder builder = request.headers().newBuilder();

                            // забиваем в билдер запрос, делаем по F.A.Q.
                            builder.add("application-id", Constants.BAAS_REST_API_ID);
                            builder.add("secret-key", Constants.BAAS_REST_API_KEY);
                            builder.add("application-type", "REST");
                            builder.add("Content-Type", "application/json");

                            String token = getToken();
                            if(!TextUtils.isEmpty(token)) {
                                builder.add("user-token", token);
                            }
                            request = request.newBuilder().headers(builder.build()).build();
                            return chain.proceed(request);
                        }
                    })
                    .build();

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
            Gson gson = gsonBuilder.create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.API_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public void login(LoginRequest loginRequest){
        IBaasApi baasApi = getRetrofit().create(IBaasApi.class);
        Call<Users> call = baasApi.login(loginRequest);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, retrofit2.Response<Users> response) {

                Users user = response.body();
                if(response.isSuccessful() && user != null && !TextUtils.isEmpty(user.getUserToken())) {
                    //ВСЕ ОТЛИЧНО
                    Logs.sendLog(TAG, "all OK = " + user.toString());
                    saveToken(user.getUserToken());
                    BusProvider.getInstance().post(new LoginResponse());
                } else {
                    // ОШИБКА ЗАПРОСА
                    try {
                        Logs.sendLog(TAG, "error response code = " + response.code());
                        Logs.sendLog(TAG, "error response body = " + response.errorBody().string());
                    } catch (IOException e) {}
                    BusProvider.getInstance().post(new LoginResponse(true, "Ошибка из server"));
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                // ВСЕ СЛОМАЛСЬ - если нет интернета или что то закодили не правильно
                Logs.sendLog(TAG, "ERROR = " + t.toString());
                BusProvider.getInstance().post(new LoginResponse(true, "Ошибка из network"));
            }
        });
    }

    public void register(RegisterRequest registerRequest){
        IBaasApi baasApi = getRetrofit().create(IBaasApi.class);
        Call<Users> call = baasApi.register(registerRequest);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Users user = response.body();

                if(response.isSuccessful() && user != null ) {
                    //ВСЕ ОТЛИЧНО
                    Logs.sendLog(TAG, "all OK = " + user.toString());
                    saveToken(user.getUserToken());
                    BusProvider.getInstance().post(new RegisterResponse());
                } else {
                    // ОШИБКА ЗАПРОСА
                    try {
                        Logs.sendLog(TAG, "error response code = " + response.code());
                        Logs.sendLog(TAG, "error response body = " + response.errorBody().string());
                    } catch (IOException e) {}
                    BusProvider.getInstance().post(new RegisterResponse(true, "Ошибка из server"));
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                // ВСЕ СЛОМАЛСЬ - если нет интернета или что то закодили не правильно
                Logs.sendLog(TAG, "ERROR = " + t.toString());
                BusProvider.getInstance().post(new RegisterResponse(true,"проблема с сетью"));
            }
        });
    }

    private void saveToken(String token) {
        SharedPreferences sh = BaseApplication.getInstance()
                .getSharedPreferences("filename", Context.MODE_PRIVATE);
        sh.edit().putString(Constants.KEY_TOKEN, token).apply();
    }

    public String getToken() {
        SharedPreferences sh = BaseApplication.getInstance()
                .getSharedPreferences("filename", Context.MODE_PRIVATE);
        return sh.getString(Constants.KEY_TOKEN, null);
    }

    private void deleteToken() {
        SharedPreferences sh = BaseApplication.getInstance()
                .getSharedPreferences("filename", Context.MODE_PRIVATE);
        sh.edit().remove(Constants.KEY_TOKEN).apply();
    }

    public boolean isAutorizated() {
        if(!TextUtils.isEmpty(getToken())) {
            return true;
        }
        return false;
    }

    public void logout() {
        deleteToken();
    }

}
