package com.spicytown.entity;


import javax.persistence.*;

/**
 * Created by cheyikung on 4/27/16.
 */
@Entity
@Table(name = "menuItem")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String category;
    private String name;
    private String imageUrl;
    private double price;
    private int calories;
    private int prepTimeMins;

    public MenuItem(){}

    public MenuItem(int prepTimeMins, int calories, double price, String imageUrl, String name, String category) {
        this.prepTimeMins = prepTimeMins;
        this.calories = calories;
        this.price = price;
        this.imageUrl = imageUrl;
        this.name = name;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getPrepTimeMins() {
        return prepTimeMins;
    }

    public void setPrepTimeMins(int prepTimeMins) {
        this.prepTimeMins = prepTimeMins;
    }
}
