package ru.list.victor_90.study.moneykeeper.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.list.victor_90.study.moneykeeper.R;
import ru.list.victor_90.study.moneykeeper.rest.networks.ServiceBroker;


public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if(!ServiceBroker.getInstance().isAutorizated()){
            LoginActivity.show(StartActivity.this);
        } else {
            MainActivity.show(StartActivity.this);
        }
        this.finish();
    }

}
