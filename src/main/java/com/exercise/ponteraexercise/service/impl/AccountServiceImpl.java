package com.exercise.ponteraexercise.service.impl;

import com.exercise.ponteraexercise.ValidationUtil;
import com.exercise.ponteraexercise.enums.TransactionType;
import com.exercise.ponteraexercise.model.Holding;
import com.exercise.ponteraexercise.model.Owner;
import com.exercise.ponteraexercise.model.Transaction;
import com.exercise.ponteraexercise.service.AccountService;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public void buyStock(Owner owner, String stockSymbol, double quantity, Date currentDate) throws Exception {

        ValidationUtil.validateQuantityPositive(quantity);

        Holding ownerHolding = getOwnerHoldingByStockSymbol(owner, stockSymbol);

        // in case user has no holding of that stock
        if(ownerHolding == null) {
            owner.getHoldings().add(createNewHolding(stockSymbol, quantity));
        } else {
            ownerHolding.setQuantity(ownerHolding.getQuantity() + quantity);
        }

        addTransaction(owner, stockSymbol, quantity, TransactionType.BUY, currentDate);
    }

    @Override
    public void sellStock(Owner owner, String stockSymbol, double quantity, Date currentDate) throws Exception {
        ValidationUtil.validateQuantityPositive(quantity);
        Holding ownerHolding = getOwnerHoldingByStockSymbol(owner, stockSymbol);
        if(ownerHolding == null) {
            throw new Exception("Owner can't sell. No holding for stock " + stockSymbol);
        }

        double currentStockQuantity = ownerHolding.getQuantity();
        if(quantity > currentStockQuantity) {
            throw new Exception("Owner can't sell. owner has less quantity than the request to sell");
        }

        ownerHolding.setQuantity(currentStockQuantity - quantity);
        addTransaction(owner, stockSymbol, quantity, TransactionType.SELL, currentDate);
    }

    @Override
    public double getFinalBalance(List<Holding> initialHoldings, Date initialDate, List<Transaction> transactions, Date finalDate) {
        AtomicReference<Double> finalBalance = new AtomicReference<>((double) 0);
        if(initialHoldings != null && !initialHoldings.isEmpty()) {
            for (Holding holding : initialHoldings) {
               transactions.forEach(transaction -> {

                   // Check that the transaction holds the same holding stock
                   if(ValidationUtil.validateStockSymbol(holding.getStockSymbol() ,transaction.getStockSymbol())) {

                       // Check that the transaction executed between the initial and final date
                       if(transaction.getDate().before(finalDate) && transaction.getDate().after(initialDate)) {
                           try {
                               finalBalance.updateAndGet(v -> v + holding.getQuantity() * getStockPrice(holding.getStockSymbol(), transaction.getDate()));
                           } catch (Exception e) {
                               throw new RuntimeException(e);
                           }
                       }
                   }
               });
            }
        }

        return finalBalance.get();
    }

    /**
     * This method will create and add a transaction to the owner transactions list
     * @param owner - the owner who holds the transactions
     * @param stockSymbol - the symbol of the stock
     * @param quantity - the amount of stocks to buy or sell
     * @param trxType - the type of the transaction (BUY / SELL)
     * @param date - the date the transaction is being executed
     * @throws Exception
     */
    private static void addTransaction(Owner owner, String stockSymbol, double quantity, TransactionType trxType, Date date) throws Exception {
        double stockPrice = 0;
        try {
            stockPrice = PriceService.getPrice(stockSymbol, date);
        } catch (Exception e) {
            throw new Exception("Sorry there was a problem to retrieve the price. Please try later");
        }
        List<Transaction> ownerTransactions = owner.getTransactions();
        ownerTransactions.add(new Transaction(date, stockSymbol, quantity, trxType));
    }


    /**
     * This method will retrieve the price of a given stock by a given date
     * @param stockSymbol - the symbol of the stock
     * @param date - the date to search for
     * @return a price for the stock
     */
    private double getStockPrice(String stockSymbol, Date date)  {
        try {
            return PriceService.getPrice(stockSymbol, date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method will create a new owner holding
     * @param stockSymbol - the symbol of the stock
     * @param quantity - the amount of stocks
     * @return owner holding
     */
    private Holding createNewHolding(String stockSymbol, double quantity) {
        return new Holding(stockSymbol, quantity);
    }

    /**
     * This method will retrieve the owner holding by the given stock symbol
     * @param owner - the owner of the holdings
     * @param stockSymbol - the symbol of the stock
     * @return the owner holding for a given stock
     */
    private Holding getOwnerHoldingByStockSymbol(Owner owner, String stockSymbol) {
        List<Holding> ownerHoldings = owner.getHoldings();
        AtomicReference<Holding> ownerHolding = new AtomicReference<>();
        ownerHoldings.forEach(holding -> {
            if(holding.getStockSymbol().equals(stockSymbol)) {
                ownerHolding.set(holding);
            }
        });
        return ownerHolding.get();
    }
}
