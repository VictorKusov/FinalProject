package ru.list.victor_90.study.moneykeeper.database.models;

import ru.list.victor_90.study.moneykeeper.R;

public class Category {

    private int id;
    private String category;
    private int iconId;

    public Category() {
    }

    public Category(int id, String category) {
        this.id = id;
        this.category = category;
    }

    public Category(int id, String category, int iconId) {
        this.id = id;
        this.category = category;
        this.iconId = iconId;
    }

    public Category(String category, int iconId) {
        this.category = category;
        this.iconId = iconId;
    }



    public int getIconId() {
        return iconId;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIconById() {

        switch (this.id) {
            case 1: {
                return R.drawable.ic_wash;
            }
            case 2: {
                return R.drawable.ic_ice_cream;
            }
            case 3: {
                return R.drawable.ic_house;
            }
            case 4: {
                return R.drawable.ic_injection;
            }
            case 5: {
                return R.drawable.ic_tea;
            }
            case 6: {
                return R.drawable.ic_car;
            }
            case 7: {
                return R.drawable.ic_cloth;
            }
            case 8: {
                return R.drawable.ic_fish;
            }
            case 9: {
                return R.drawable.ic_present;
            }
            case 10: {
                return R.drawable.ic_playstation;
            }
            case 11: {
                return R.drawable.ic_telephone;
            }
            case 12: {
                return R.drawable.ic_socker_ball;
            }
            case 13: {
                return R.drawable.ic_abacus;
            }
            case 14: {
                return R.drawable.ic_car;
            }
            case 15: {
                return R.drawable.ic_school_bus;
            }
            default:{
                return R.drawable.ic_android_24dp;
            }
        }
    }

}
