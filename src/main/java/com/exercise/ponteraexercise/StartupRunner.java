package com.exercise.ponteraexercise;

import com.exercise.ponteraexercise.model.Owner;
import com.exercise.ponteraexercise.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import java.util.Date;

public class StartupRunner implements CommandLineRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner("John", "Doe");
		try {
            accountService.buyStock(owner1,"AAPL", 10.0, DateUtil.generateDate("01082023"));
            accountService.buyStock(owner1,"FB", 15.0, DateUtil.generateDate("04082023"));
            accountService.sellStock(owner1,"AAPL", 5.0, new Date());
            accountService.buyStock(owner1,"FB", 2.0, new Date());
            double finalBalance = accountService.getFinalBalance(owner1.getHoldings(), DateUtil.generateDate("25072023"),
                    owner1.getTransactions(),DateUtil.generateDate("05082023"));
            System.out.println("Owner " + owner1.getName() + " " + owner1.getLastname() + " has final balance of " + finalBalance + " dollars");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

    }
}
