package com.exercise.ponteraexercise.model;

import com.exercise.ponteraexercise.enums.TransactionType;
import java.util.Date;

public class Transaction {

    private Date date;
    private String stockSymbol;
    private double quantity;
    private TransactionType type;

    public Transaction(Date date, String stockSymbol, double quantity, TransactionType type) {
        this.date = date;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
