package com.spicytown.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivanybma on 5/20/16.
 */
public class OrderDetail implements Serializable{

    private long orderId;

    private List<ItemDetail> itemList = new ArrayList<ItemDetail>();

    private String customerEmail;

    private String status;

    private String orderDateTime;

    private String fullfillmentDateTime;

    private String ReadyDatetime;

    private String requiredPickupDateTime;

    private double totalPrice;

    public OrderDetail() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public List<ItemDetail> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemDetail> itemList) {
        this.itemList = itemList;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getFullfillmentDateTime() {
        return fullfillmentDateTime;
    }

    public void setFullfillmentDateTime(String fullfillmentDateTime) {
        this.fullfillmentDateTime = fullfillmentDateTime;
    }

    public String getReadyDatetime() {
        return ReadyDatetime;
    }

    public void setReadyDatetime(String readyDatetime) {
        ReadyDatetime = readyDatetime;
    }

    public String getRequiredPickupDateTime() {
        return requiredPickupDateTime;
    }

    public void setRequiredPickupDateTime(String requiredPickupDateTime) {
        this.requiredPickupDateTime = requiredPickupDateTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
