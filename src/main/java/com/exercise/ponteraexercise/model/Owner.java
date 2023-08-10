package com.exercise.ponteraexercise.model;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    private final String name;
    private final String lastname;
    private final List<Holding> holdings;
    private final List<Transaction> transactions;

    public Owner(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
        this.holdings = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public List<Holding> getHoldings() {
        return holdings;
    }

    public void addHolding(Holding holding) {
        this.holdings.add(holding);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
}
