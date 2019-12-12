package com.dovhan.application.entities;

public class FoodMachine {
    private final String name;
    private final String company;
    private final String good;
    private final String address;
    private final String picture;

    public FoodMachine(String name, String company, String good,
                       String address, String url) {
        this.name = name;
        this.company = company;
        this.good = good;
        this.address = address;
        this.picture = url;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getGood() {
        return good;
    }

    public String getAddress() {
        return address;
    }

    public String getPicture() {
        return picture;
    }
}
