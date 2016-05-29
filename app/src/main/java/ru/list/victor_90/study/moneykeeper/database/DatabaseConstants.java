package ru.list.victor_90.study.moneykeeper.database;


public final class DatabaseConstants {

    private DatabaseConstants() {
    }

    protected static final String DATABASE_NAME = "MoneyKeeper";
    protected static final int DATABASE_VERSION = 5;


    protected static final String TABLE_PAYMENT = "Payment";
    protected static final String PAYMENT_COLUMN_ID = "id";
    protected static final String PAYMENT_COLUMN_DATE = "date";
    protected static final String PAYMENT_COLUMN_VALUE = "value";
    protected static final String PAYMENT_COLUMN_NOTE = "note";
    protected static final String PAYMENT_COLUMN_USER_ID = "user_id";           // сюда вставляем либо user-token, либо objectId
    protected static final String PAYMENT_COLUMN_CATEGORY_ID = "category_id";

    protected static final String TABLE_CATEGORY = "Category";
    protected static final String CATEGORY_COLUMN_ID = "id";
    protected static final String CATEGORY_COLUMN_CATEGORY = "category";
    protected static final String CATEGORY_COLUMN_ICON_ID = "icon_id";
    protected static final String CATEGORY_COLUMN_USER_ID = "user_id";           // сюда вставляем либо user-token, либо objectId

    // строка создания таблицы с записями
    protected static final String CREATE_TABLE_PAYMENT = "CREATE TABLE " + TABLE_PAYMENT + " ("
            + PAYMENT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PAYMENT_COLUMN_DATE + " DATE, "
            + PAYMENT_COLUMN_VALUE + " INTEGER, "
            + PAYMENT_COLUMN_NOTE + " TEXT,"
            + PAYMENT_COLUMN_USER_ID + " TEXT,"
            + PAYMENT_COLUMN_CATEGORY_ID + " INTEGER);";

    // строка создания таблицы с категориями
    protected static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + " ("
            + CATEGORY_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CATEGORY_COLUMN_CATEGORY + " TEXT, "
            + CATEGORY_COLUMN_ICON_ID + " INTEGER, "
            + CATEGORY_COLUMN_USER_ID + " TEXT);";


    // строка выборки всех категорий
    protected static final String SELECT_CATEGORIES = "Select * from " + TABLE_CATEGORY
            + " where " + CATEGORY_COLUMN_ID + ">0;";

    // строка выборки всех записей
    protected static final String SELECT_PAYMENTS = "Select * from " + TABLE_PAYMENT
            + " where " + PAYMENT_COLUMN_ID + ">0;";

    // строка выборки всех категорий
    protected static final String SELECT_CATEGORY_COUNT = "Select count(id) from " + TABLE_CATEGORY + ";";

    // строка выборки суммы значений записей в одной категории
    protected static final String SELECT_PAYMENT_BY_CATEGORY = "Select " + TABLE_CATEGORY + "." + CATEGORY_COLUMN_ID + " as id,"
            + " sum(" + PAYMENT_COLUMN_VALUE + ") as value, "
            + TABLE_CATEGORY + "." + CATEGORY_COLUMN_CATEGORY + " as label"
            + " from " + TABLE_PAYMENT + ", " + TABLE_CATEGORY
            + " where " + PAYMENT_COLUMN_CATEGORY_ID + " = " + TABLE_CATEGORY + "." + CATEGORY_COLUMN_ID
            + " group by id;";

}
