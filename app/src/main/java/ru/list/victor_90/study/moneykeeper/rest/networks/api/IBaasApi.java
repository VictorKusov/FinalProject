package ru.list.victor_90.study.moneykeeper.rest.networks.api;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.list.victor_90.study.moneykeeper.rest.events.LoginRequest;
import ru.list.victor_90.study.moneykeeper.rest.events.RegisterRequest;
import ru.list.victor_90.study.moneykeeper.rest.models.Users;


public interface IBaasApi {

    @POST("v1/users/login")
    Call<Users> login(@Body LoginRequest loginRequest);

    @POST("v1/users/register")
    Call<Users> register(@Body RegisterRequest registerRequest);


}
