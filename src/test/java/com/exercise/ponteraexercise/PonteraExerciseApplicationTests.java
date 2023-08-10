package com.exercise.ponteraexercise;

import com.exercise.ponteraexercise.model.Owner;
import com.exercise.ponteraexercise.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.concurrent.atomic.AtomicReference;
import static com.exercise.ponteraexercise.service.impl.PriceService.getPrice;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PonteraExerciseApplicationTests {

	public static final String AAPL = "AAPL";
	@Autowired
	AccountService accountService;
	private Owner owner;

	@Test
	void contextLoads() {
		assertThat(accountService).isNotNull();
	}

	@BeforeEach
	public void initEach(){
		owner = new Owner("John", "Doe");
	}

	@Test
	void testOwnerWhoHasNoStockHoldingCannotSell() {
		Assertions.assertThrows(Exception.class, () -> {
			accountService.sellStock(owner, AAPL, 10.0, DateUtil.generateDate("01082023"));
		});
	}

	@Test
	void testOwnerAbleToBuyStocks() throws Exception {
		accountService.buyStock(owner, AAPL, 10.0, DateUtil.generateDate("01082023"));
		assertEquals(1, owner.getHoldings().size());
	}

	@Test
	void testOwnerFailedToBuyStocksWithNegativeQuantity() throws Exception {
		Assertions.assertThrows(Exception.class, () -> {
			accountService.buyStock(owner, AAPL, -2.0, DateUtil.generateDate("01082023"));
		});
	}

	@Test
	void testOwnerAbleToSellStocks() throws Exception {
		accountService.buyStock(owner, AAPL, 10.0, DateUtil.generateDate("01082023"));
		accountService.sellStock(owner, AAPL, 5.0, DateUtil.generateDate("01082023"));
		assertEquals(5.0, owner.getHoldings().get(0).getQuantity());
	}

	@Test
	void testOwnerFinalBalance() throws Exception {
		// price for this symbol at the below dates should be 2.0
		AtomicReference<Double> balance = new AtomicReference<>((double) 0);
		accountService.buyStock(owner, AAPL, 10.0, DateUtil.generateDate("01082023"));
		accountService.sellStock(owner, AAPL, 5.0, DateUtil.generateDate("01082023"));
		owner.getTransactions().forEach(transaction -> {
			balance.updateAndGet(v -> {
				try {
					return v + transaction.getQuantity() * getPrice(transaction.getStockSymbol(), transaction.getDate());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});
		});
		assertEquals(30.0, balance.get());
	}

}
