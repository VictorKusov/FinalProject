package ru.list.victor_90.study.moneykeeper.constants;


public final class Constants {

    private Constants() {
    }

    // ****************** Backendless constants ******************
    public static final String API_URL = "https://api.backendless.com/";
    public static final String BAAS_REST_API_ID = "5E3C3BB4-A84A-D08C-FF74-E65745BFF000";
    public static final String BAAS_REST_API_KEY = "21A6DE59-BAFC-F24D-FF0A-9E003E239400";
    public static final String DATE_FORMAT = "mm/dd/yyyy hh:mm:ss +zone";
    // ***********************************************************

    // ****************** SharedPreferences constants ******************
    public static final String KEY_TOKEN = "AUTHENTICATION";
    public static final int REGISTER_ACTIVITY_RESULT_VALUE = 12;
    public static final String RESULT_EMAIL = "login";
    public static final String RESULT_PASSWORD = "password";
    // *****************************************************************

    // ****************** Category database values *********************
    public static String[] CategoryValues = {
            "Гигиена", "Еда", "Жилье", "Здоровье", "Кафе",
            "Машина", "Одежда", "Питомцы", "Подарки", "Развлечения",
            "Связь", "Спорт", "Счета", "Такси", "Транспорт"};
    // *****************************************************************

    // ****************** количество столбцов для показа записей **********
    public static final int ITEMS_GRID_COLUMNS_COUNT = 3;

    // ****************** intent add note constants ***********************
    public static final String NOTE_KEY_ID = "ID";
    public static final String NOTE_KEY_CATEGORY = "CATEGORY";
    public static final int RESULT_NOTE = 10;
}
