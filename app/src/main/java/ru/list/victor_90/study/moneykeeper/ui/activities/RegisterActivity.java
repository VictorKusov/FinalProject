package ru.list.victor_90.study.moneykeeper.ui.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


import butterknife.BindView;
import ru.list.victor_90.study.moneykeeper.R;
import ru.list.victor_90.study.moneykeeper.constants.Constants;
import ru.list.victor_90.study.moneykeeper.rest.events.RegisterRequest;
import ru.list.victor_90.study.moneykeeper.rest.networks.ServiceBroker;
import ru.list.victor_90.study.moneykeeper.utils.Validator;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edMail, edLogin, edName, edPassword;
    private Button btnRegister, btnBack;
    private ProgressBar pbRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edName = (EditText) findViewById(R.id.ed_name);
        edLogin = (EditText) findViewById(R.id.ed_login);
        edMail = (EditText) findViewById(R.id.ed_email);
        edPassword = (EditText) findViewById(R.id.ed_pass);
        btnRegister = (Button) findViewById(R.id.btn_reg);
        btnBack = (Button) findViewById(R.id.btn_back);
        pbRegister = (ProgressBar) findViewById(R.id.pb_bar_reg);

        btnRegister.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back: {
                this.finish();
                break;
            }
            case R.id.btn_reg: {
                makeRegistration();
                break;
            }
        }
    }


    private void makeRegistration() {
        Validator.registerValidation();

        pbRegister.setVisibility(View.VISIBLE);
        RegisterRequest request = new RegisterRequest(
                String.valueOf(edMail.getText()),
                String.valueOf(edLogin.getText()),
                String.valueOf(edPassword.getText()),
                String.valueOf(edName.getText())
        );
        ServiceBroker.getInstance().register(request);
        pbRegister.setVisibility(View.GONE);

        Intent intent = new Intent();
        intent.putExtra(Constants.RESULT_EMAIL, edMail.getText().toString());
        intent.putExtra(Constants.RESULT_PASSWORD, edPassword.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }




}
