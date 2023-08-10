package com.exercise.ponteraexercise.service.impl;

import java.util.Date;

public class PriceService {

    public static double getPrice(String symbol, Date date) throws Exception {

        double price = 0;
        switch (symbol) {
            case "AAPL":
                if(date.before(new Date())) {
                    price = 2.0;
                } else if(date.after(new Date())) {
                    price = 5.0;
                } else {
                    price = 8.0;
                }
                break;
            case "FB":
                if(date.before(new Date())) {
                    price = 4.0;
                } else if(date.after(new Date())) {
                    price = 9.0;
                } else {
                    price = 3.0;
                }
                break;
            default:
                throw new Exception("Symbol is invalid");
        }
        return price;
    }

}
