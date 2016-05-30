package com.spicytown.model.entity;

import javax.persistence.*;

/**
 * Created by cheyikung on 5/1/16.
 */
@Entity
@Table(name = "chef_schedule")
public class ChefSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chef_schedule_id")
    private long id;

    private int chefId;

    private int startTime;

    private int endTime;

    private boolean isAvailable;

    private String date;

    @ManyToOne
    @JoinColumn(name="user_order_id")
    private UserOrder userOrder;

    public ChefSchedule() {
    }

    public ChefSchedule(int chefId, int startTime, int endTime, boolean isAvailable, String date, UserOrder userOrder) {
        this.chefId = chefId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = isAvailable;
        this.date = date;
        this.userOrder = userOrder;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getChefId() {
        return chefId;
    }

    public void setChefId(int chefId) {
        this.chefId = chefId;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }
}
