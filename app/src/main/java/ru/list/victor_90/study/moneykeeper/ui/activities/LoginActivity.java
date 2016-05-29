package ru.list.victor_90.study.moneykeeper.ui.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import ru.list.victor_90.study.moneykeeper.R;
import ru.list.victor_90.study.moneykeeper.constants.Constants;
import ru.list.victor_90.study.moneykeeper.rest.events.LoginRequest;
import ru.list.victor_90.study.moneykeeper.rest.events.LoginResponse;
import ru.list.victor_90.study.moneykeeper.rest.helpers.BusProvider;
import ru.list.victor_90.study.moneykeeper.rest.networks.ServiceBroker;
import ru.list.victor_90.study.moneykeeper.utils.Logs;
import ru.list.victor_90.study.moneykeeper.utils.Validator;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edLogin, edPassword;
    private Button btnLogin, btnRegister;
    private ProgressBar pbBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edLogin = (EditText) findViewById(R.id.ed_login);
        edPassword = (EditText) findViewById(R.id.ed_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);
        pbBar = (ProgressBar) findViewById(R.id.pb_bar_login);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: {
                login();
                break;
            }
            case R.id.btn_register: {
                startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), Constants.REGISTER_ACTIVITY_RESULT_VALUE);
                break;
            }
        }
    }

    private void login() {
        // можно добавить валидацию, если необходимо
        Validator.loginValidation();

        pbBar.setVisibility(View.VISIBLE);
        LoginRequest request = new LoginRequest(
                String.valueOf(edLogin.getText()),
                String.valueOf(edPassword.getText())
        );
        // делаем запрос
        ServiceBroker.getInstance().login(request);
        pbBar.setVisibility(View.GONE);
    }

    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.REGISTER_ACTIVITY_RESULT_VALUE) {
                edLogin.setText(data.getStringExtra(Constants.RESULT_EMAIL));
                edPassword.setText(data.getStringExtra(Constants.RESULT_PASSWORD));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void onLoginResponse(LoginResponse response) {
        if (response.isError()) {
            Toast.makeText(LoginActivity.this, "Неправильный логин или пароль", Toast.LENGTH_SHORT).show();

        } else {
            MainActivity.show(LoginActivity.this);
        }
    }
}
