package ru.list.victor_90.study.moneykeeper.database.models;


import java.sql.Date;

public class Payment {

    private int id;
    private Date date;
    private int value;
    private String note;
    private int categoryId;

    public Payment(){

    }

    public Payment(int id, Date date, int value, String note, int categoryId) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.note = note;
        this.categoryId = categoryId;
    }

    public Payment(Date date, int value, String note, int categoryId) {
        this.date = date;
        this.value = value;
        this.note = note;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
