package ru.list.victor_90.study.moneykeeper.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ru.list.victor_90.study.moneykeeper.constants.Constants;
import ru.list.victor_90.study.moneykeeper.database.models.Category;
import ru.list.victor_90.study.moneykeeper.database.models.Payment;
import ru.list.victor_90.study.moneykeeper.database.models.ReportPayment;
import ru.list.victor_90.study.moneykeeper.rest.networks.ServiceBroker;
import ru.list.victor_90.study.moneykeeper.ui.fragments.Pager;
import ru.list.victor_90.study.moneykeeper.utils.DateParser;
import ru.list.victor_90.study.moneykeeper.utils.Logs;

public class BaseDBHelper extends SQLiteOpenHelper {

    public BaseDBHelper(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);

        Logs.sendLog("DATABASE", "Constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // создаем таблицы
        db.execSQL(DatabaseConstants.CREATE_TABLE_PAYMENT);
        db.execSQL(DatabaseConstants.CREATE_TABLE_CATEGORY);

        // добавляем дефолтные категории
        for (int i = 0; i < Constants.CategoryValues.length; i++) {
            ContentValues value = new ContentValues();
            value.put(DatabaseConstants.CATEGORY_COLUMN_CATEGORY, Constants.CategoryValues[i]);
            value.put(DatabaseConstants.CATEGORY_COLUMN_ICON_ID, i);
            value.put(DatabaseConstants.CATEGORY_COLUMN_USER_ID, ServiceBroker.getInstance().getToken());

            db.insert(DatabaseConstants.TABLE_CATEGORY, null, value);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table  " + DatabaseConstants.TABLE_PAYMENT + ";");
        db.execSQL("drop table  " + DatabaseConstants.TABLE_CATEGORY + ";");

        db.execSQL(DatabaseConstants.CREATE_TABLE_PAYMENT);
        db.execSQL(DatabaseConstants.CREATE_TABLE_CATEGORY);
    }

    public void insertCategory(Category category, String userToken) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(DatabaseConstants.CATEGORY_COLUMN_CATEGORY, category.getCategory());
        value.put(DatabaseConstants.CATEGORY_COLUMN_ICON_ID, category.getIconId());
        value.put(DatabaseConstants.CATEGORY_COLUMN_USER_ID, userToken);

        db.insert(DatabaseConstants.TABLE_CATEGORY, null, value);
        db.close();
    }

    public ArrayList<Category> getCategoryFromDatabase() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Category> categories = new ArrayList<Category>();

        Cursor cursor = db.rawQuery(DatabaseConstants.SELECT_CATEGORIES, null);
        if (cursor.moveToFirst()) {

            do {
                Category category = new Category(
                        cursor.getInt(cursor.getColumnIndex(DatabaseConstants.CATEGORY_COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseConstants.CATEGORY_COLUMN_CATEGORY)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseConstants.CATEGORY_COLUMN_ICON_ID))
                );
                categories.add(category);
            } while (cursor.moveToNext());
            db.close();
            return categories;
        }
        db.close();
        return null;
    }


    public void insertPayment(Payment payment, String userToken) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(DatabaseConstants.PAYMENT_COLUMN_DATE, String.valueOf(payment.getDate()));
        value.put(DatabaseConstants.PAYMENT_COLUMN_CATEGORY_ID, payment.getCategoryId());
        value.put(DatabaseConstants.PAYMENT_COLUMN_NOTE, payment.getNote());
        value.put(DatabaseConstants.PAYMENT_COLUMN_VALUE, payment.getValue());
        value.put(DatabaseConstants.PAYMENT_COLUMN_USER_ID, userToken);

        db.insert(DatabaseConstants.TABLE_PAYMENT, null, value);
        db.close();
    }

    public ArrayList<Payment> getPaymentFromDatabase() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Payment> payments = new ArrayList<Payment>();

        Cursor cursor = db.rawQuery(DatabaseConstants.SELECT_PAYMENTS, null);
        if (cursor.moveToFirst()) {

            do {
                Payment payment = new Payment(
                        cursor.getInt(cursor.getColumnIndex(DatabaseConstants.PAYMENT_COLUMN_ID)),
                        DateParser.parseDateFromDatabase(cursor.getString(cursor.getColumnIndex(DatabaseConstants.PAYMENT_COLUMN_DATE))),
                        cursor.getInt(cursor.getColumnIndex(DatabaseConstants.PAYMENT_COLUMN_VALUE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseConstants.PAYMENT_COLUMN_NOTE)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseConstants.PAYMENT_COLUMN_CATEGORY_ID))
                );
                payments.add(payment);
            } while (cursor.moveToNext());
            db.close();
            return payments;
        }
        db.close();
        return null;
    }

    public int getCategoryCapacity() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(DatabaseConstants.SELECT_CATEGORY_COUNT, null);
        if (cursor.moveToFirst()) {
            Logs.sendLog("COUNT", cursor.getInt(0) + "");
            db.close();
            return cursor.getInt(0);
        } else {
            db.close();
            return 0;
        }
    }

    public ArrayList<ReportPayment> getPaymentValuesByCategory() {
        ArrayList<ReportPayment> values = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(DatabaseConstants.SELECT_PAYMENT_BY_CATEGORY, null);
        if (cursor.moveToFirst()) {
            do {
                values.add(new ReportPayment(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getInt(cursor.getColumnIndex("value")),
                        cursor.getString(cursor.getColumnIndex("label"))
                ));
            } while (cursor.moveToNext());
            return values;
        }
        return null;
    }
}
