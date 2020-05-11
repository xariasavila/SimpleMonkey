package com.ciisa.simplemonkey.model;

public class Category {
    private int id;
    private String name;
    private String description;
    private String color;
    private String icon;
    private double expenses;
    private double incomes;

    public Category(int id) {
        this.id = id;
    }

    public Category(int id, String name, String description, String color, String icon) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.icon = icon;
    }

    public Category(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.color = builder.color;
        this.icon = builder.icon;
        this.expenses = builder.expenses;
        this.incomes = builder.incomes;
    }

    // Builder pattern applied to Category class
    public static class Builder {
        private int id;
        private String name;
        private String description;
        private String color;
        private String icon;
        private double expenses;
        private double incomes;

        public Builder(int id, String name, String description, String color, String icon) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.color = color;
            this.icon = icon;
        }

        public Builder withMovements(double expenses, double incomes) {
            this.expenses = expenses;
            this.incomes = incomes;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public double getIncomes() {
        return incomes;
    }

    public void setIncomes(double incomes) {
        this.incomes = incomes;
    }
}
