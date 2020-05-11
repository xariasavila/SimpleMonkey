package com.example.simplemonkey.model;

import java.io.Serializable;
import java.util.Date;

public class Movement extends Finance implements Serializable {
    private boolean income;
    private int feeNumber;
    private int payday;
    private boolean done;
    private Category category;

    public Movement() {
    }

    public Movement(int uid, String name, String description, Date date, double amount, String currency, boolean income) {
        super(uid, name, description, date, amount, currency);
        this.income = income;
    }

    public boolean isIncome() {
        return income;
    }

    public void setIncome(boolean income) {
        this.income = income;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getFeeNumber() {
        return feeNumber;
    }

    public void setFeeNumber(int feeNumber) {
        this.feeNumber = feeNumber;
    }

    public int getPayday() {
        return payday;
    }

    public void setPayday(int payday) {
        this.payday = payday;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
