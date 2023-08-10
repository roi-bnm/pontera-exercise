package com.exercise.ponteraexercise.model;

public class Holding {

    private String stockSymbol;
    private double quantity;

    public Holding(String stockSymbol, double quantity) {
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
