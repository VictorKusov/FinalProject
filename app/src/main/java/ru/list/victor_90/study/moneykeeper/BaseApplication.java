package ru.list.victor_90.study.moneykeeper;

import android.app.Application;

import ru.list.victor_90.study.moneykeeper.utils.Logs;


public class BaseApplication extends Application {

    private static final String LOG = BaseApplication.class.getSimpleName();
    private static BaseApplication myApplication;

    private static String userToken = "";

    public static String getUserToken() {
        return userToken;
    }

    public static void setUserToken(String userToken) {
        BaseApplication.userToken = userToken;
    }

    public static BaseApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

}
