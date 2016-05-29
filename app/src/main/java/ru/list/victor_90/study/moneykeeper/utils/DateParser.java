package ru.list.victor_90.study.moneykeeper.utils;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ru.list.victor_90.study.moneykeeper.database.DatabaseConstants;

public abstract class DateParser {

    public static Date parseDateFromDatabase(String stringDate) {

        java.sql.Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsed = format.parse(stringDate);
            date = new java.sql.Date(parsed.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date makeSQLDate(){
        java.util.Date utilDate = new java.util.Date();
        Date sqlDate = new java.sql.Date(utilDate.getTime());

        return sqlDate;
    }
}