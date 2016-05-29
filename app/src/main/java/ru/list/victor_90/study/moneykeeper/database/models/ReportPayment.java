package ru.list.victor_90.study.moneykeeper.database.models;



public class ReportPayment {

    private int id;
    private int count;
    private String category;

    public ReportPayment(int id, int count, String category) {
        this.id = id;
        this.count = count;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public String getCategory() {
        return category;
    }
}
