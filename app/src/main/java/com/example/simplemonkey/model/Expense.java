package com.example.simplemonkey.model;

import java.io.Serializable;
import java.util.Date;

public class Expense extends Finance implements Serializable {
    private int feeNumber;
    private int payday;
    private boolean done;
    private int categoryId;
    private int budgetId;
    private int debtId;
    private int borrowId;

    public Expense(int id, int uid, String name, String description, Date date, double amount, String currency, Boolean sync, String coordinates, Date createdAt, Date updatedAt, int feeNumber, int payday, boolean done, int categoryId, int budgetId, int debtId, int borrowId) {
        super(id, uid, name, description, date, amount, currency, sync, coordinates, createdAt, updatedAt);
        this.feeNumber = feeNumber;
        this.payday = payday;
        this.done = done;
        this.categoryId = categoryId;
        this.budgetId = budgetId;
        this.debtId = debtId;
        this.borrowId = borrowId;
    }

    public Expense(int uid, String name, String description, Date date, double amount, String currency, int categoryId, int payday) {
        super(uid, name, description, date, amount, currency);
        this.categoryId = categoryId;
        this.payday = payday;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public int getDebtId() {
        return debtId;
    }

    public void setDebtId(int debtId) {
        this.debtId = debtId;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
