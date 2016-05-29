package ru.list.victor_90.study.moneykeeper.utils;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public abstract class Logs {

    private static boolean isNeedLog = false;

    private Logs() throws Exception {
        throw new Exception("нельзя создать объект");
    }

    public static void sendLog(String tag, String message){
        if(isNeedLog){
            Log.d(tag,message);
        }
    }

    public static void toast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

}
