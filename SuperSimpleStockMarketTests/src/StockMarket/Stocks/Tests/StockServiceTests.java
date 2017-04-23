package StockMarket.Stocks.Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import StockMarket.Stocks.StockPrice;
import StockMarket.Stocks.StockService;

public class StockServiceTests {

	@Test
	public void CalculateGBCEAllShareIndexTest() {
		double expected = Math.pow(60, 1.0 / 3);

		List<StockPrice> stockPrices = new ArrayList<StockPrice>();
		stockPrices.add(new StockPrice("TEA", 10));
		stockPrices.add(new StockPrice("POP", 20));
		stockPrices.add(new StockPrice("ALE", 30));

		StockService stockService = new StockService();

		// act
		double actual = stockService.CalculateGBCEAllShareIndex(stockPrices);

		// assert
		assertEquals("GBCE All Share Index calcuated incorrectly", expected, actual, 0);

	}
	
	@Test
	public void CalculateGBCEAllShareIndexTestEmptyList() {
		double expected = 0;

		List<StockPrice> stockPrices = new ArrayList<StockPrice>();

		StockService stockService = new StockService();

		// act
		double actual = stockService.CalculateGBCEAllShareIndex(stockPrices);

		// assert
		assertEquals("GBCE All Share Index empty list calcuated incorrectly", expected, actual, 0);

	}

}
