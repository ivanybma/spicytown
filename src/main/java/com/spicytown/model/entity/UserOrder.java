package com.spicytown.model.entity;

import com.spicytown.utils.date.DateData;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by cheyikung on 4/27/16.
 */

@Entity
@Table(name = "user_order")
public class UserOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_order_id")
    private long id;


    @OneToMany(mappedBy = "userOrder", cascade = CascadeType.ALL)
    private List<Item> itemList = new ArrayList<Item>();

    private int totalPrepTime;

    private String status;

    private String orderDate;

    private int orderTime;

    private String requiredPickupDate;

    private int requiredPickupTime;

    private double totalPrice;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public UserOrder() {
    }

    public UserOrder(List<Item> itemList, String status, String requiredPickupDate, int requiredPickupTime) {
        this.itemList = itemList;
        this.status = status;
        this.requiredPickupDate = requiredPickupDate;
        this.requiredPickupTime = requiredPickupTime;
        this.setTotalPriceAndTotalPrepTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public int getTotalPrepTime() {
        return totalPrepTime;
    }

    public void setTotalPrepTime(int totalPrepTime) {
        this.totalPrepTime = totalPrepTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRequiredPickupDate() {
        return requiredPickupDate;
    }

    public void setRequiredPickupDate(String requiredPickupDate) {
        this.requiredPickupDate = requiredPickupDate;
    }

    public int getRequiredPickupTime() {
        return requiredPickupTime;
    }

    public void setRequiredPickupTime(int requiredPickupTime) {
        this.requiredPickupTime = requiredPickupTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addItem(Item item){
        this.itemList.add(item);
        this.setTotalPriceAndTotalPrepTime();
    }



    private void setTotalPriceAndTotalPrepTime(){
        this.totalPrepTime = 0;
        this.totalPrice = 0;
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            Menu menu = item.getMenu();
            int requiredTime = menu.getPrepTime();
            int quantity = item.getQuantity();
            this.totalPrepTime += (requiredTime * quantity);
            this.totalPrice += (menu.getPrice() * quantity);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(int orderTime) {
        this.orderTime = orderTime;
    }
}
