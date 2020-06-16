package com.thattwodevs.manishaapp.model;

import java.io.Serializable;

public class Menu implements Serializable {
    private String name, desc;
    Integer count=1;
    Integer price;
    public Menu() {
    }
    public Menu(String name, String desc, Integer price, Integer count) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.count=count;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getDesc() {
        return desc;
    }
 
    public void setDesc(String desc) {
        this.desc = desc;
    }
 
    public Integer getPrice() {
        return price;
    }
 
    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}