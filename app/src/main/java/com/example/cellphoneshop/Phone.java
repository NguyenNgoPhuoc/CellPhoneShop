package com.example.cellphoneshop;

import java.io.Serializable;

public class Phone implements Serializable {
    String code, name;
    int price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Phone() {
    }

    public Phone(String code, String name, int price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }
}
