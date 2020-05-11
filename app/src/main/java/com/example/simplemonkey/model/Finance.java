package com.example.simplemonkey.model;

import com.example.simplemonkey.utils.DateConvert;

import java.util.Date;

abstract class Finance {
    private long id;
    private int uid;
    private String name;
    private String description;
    private Date date;
    private double amount;
    private String currency;
    private Boolean sync;
    private Date createdAt;
    private Date updatedAt;
    private String coordinates;

    public Finance() {
    }

    public Finance(int uid, String name, String description, Date date, double amount, String currency) {
        this.uid = uid;
        this.name = name;
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getSync() {
        return sync;
    }

    public void setSync(Boolean sync) {
        this.sync = sync;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getDateToString() {
        return DateConvert.dateToString(this.date);
    }

    public String getCreatedAtToString() {
        return DateConvert.dateTimeToString(this.createdAt);
    }

    public String getUpdatedAtToString() {
        return DateConvert.dateTimeToString(this.createdAt);
    }
}
