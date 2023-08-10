package com.exercise.ponteraexercise.service;


import com.exercise.ponteraexercise.model.Holding;
import com.exercise.ponteraexercise.model.Owner;
import com.exercise.ponteraexercise.model.Transaction;
import java.util.Date;
import java.util.List;

public interface AccountService {

    /**
     * This method will enable the owner to buy a stock
     * @param owner - the owner of the account
     * @param stockSymbol - the symbol of the stock
     * @param quantity - the amount of stocks to buy
     * @param currentDate - the current date of the purchase
     * @throws Exception
     */
    public void buyStock(Owner owner, String stockSymbol, double quantity, Date currentDate) throws Exception;

    /**
     * This method will enable the owner to sell a stock
     * @param owner - the owner of the account
     * @param stockSymbol - the symbol of the stock
     * @param quantity - the amount of stocks to sell
     * @param currentDate - the current date of the sell
     * @throws Exception
     */
    public void sellStock(Owner owner, String stockSymbol, double quantity, Date currentDate) throws Exception;

    /**
     * This method will retrieve the total owner balance from all of his holdings between specified dates.
     * @param initialHoldings - the owner holdings
     * @param initialDate - the date of the first transaction to consider in the calculation
     * @param transactions - the owner transactions
     * @param finalDate - the date of the last transaction to consider in the calculation
     * @return total balance of the owner
     * @throws Exception
     */
    public double getFinalBalance(List<Holding> initialHoldings,
                                  Date initialDate,
                                  List<Transaction> transactions,
                                  Date finalDate) throws Exception;
}
