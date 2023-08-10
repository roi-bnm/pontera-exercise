package com.exercise.ponteraexercise;

public class ValidationUtil {

    public static boolean validateStockSymbol(String stockSymbol1, String stockSymbol2) {
        return stockSymbol1.equals(stockSymbol2);
    }

    public static void validateQuantityPositive(double quantity) throws Exception {
        if(quantity < 0.0) {
            throw new Exception("Quantity cannot be negative");
        }
    }
}
