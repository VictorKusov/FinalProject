package ru.list.victor_90.study.moneykeeper.utils;


public abstract class Validator {

    // на всякий случай добавим класс для валидации данных
    private static String LOG = Validator.class.getSimpleName();

    private Validator() {
    }

    public static void registerValidation() {
        Logs.sendLog(LOG, "проводим валидацию");
    }

    public static void loginValidation() {
        Logs.sendLog(LOG, "проводим валидацию");
    }
}
