package StockMarket.Stocks.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import StockMarket.Stocks.Stock;
import StockMarket.Stocks.StockCalculationException;
import StockMarket.Stocks.StockType;

public class StockTests {

	@Test
	public void DividendYieldCommonStockTypeTest() {
		double lastDividend = 20;
		double price = 2.5;
		double expected = 8;
		double parValue = 100;
		String symbol = "TEA";

		Stock TEAStock = new Stock(symbol, StockType.Common, lastDividend, parValue);

		try {
			double actual;

			// act
			actual = TEAStock.DividendYield(price);

			// assert
			assertEquals("Common Stock Dividend Yield calcuated incorrectly", expected, actual, 0);
		} catch (StockCalculationException e) {
			fail("Common Stock Dividend Yield calculation threw an exception");
		}

	}

	@Test
	public void DividendYieldPreferredStockTypeTest() {
		double lastDividend = 8;
		double fixedDividend = 2;
		double price = 2.5;
		double parValue = 100;
		String symbol = "GIN";
		double expected = 0.8;

		Stock GINStock = new Stock(symbol, StockType.Preferred, lastDividend, parValue, fixedDividend);

		try {
			double actual;

			// act
			actual = GINStock.DividendYield(price);

			// assert
			assertEquals("Preferred Stock Dividend Yield calcuated incorrectly", expected, actual, 0);
		} catch (StockCalculationException e) {
			fail("Common Stock Dividend Yield calculation threw an exception");
		}
	}

	@Test
	public void DividendYieldDivideByZeroTest() {
		double lastDividend = 8;
		double fixedDividend = 0.02;
		double price = 0;
		double parValue = 100;
		String symbol = "GIN";

		Stock GINStock = new Stock(symbol, StockType.Preferred, lastDividend, parValue, fixedDividend);

		try {
			// act
			double actual = GINStock.DividendYield(price);

		} catch (StockCalculationException e) {
			// assert
			assertTrue("Wrong exception message returned", e.getMessage().contains(Stock.PRICE_ZERO_MESSAGE));
			return;
		}

		fail("No exception was thrown.");
	}

	@Test
	public void PERatioStockTest() {
		double lastDividend = 8;
		double fixedDividend = 0.02;
		double price = 2.5;
		double parValue = 100;
		String symbol = "GIN";
		double expected = 0.3125;

		Stock GINStock = new Stock(symbol, StockType.Preferred, lastDividend, parValue, fixedDividend);

		try {
			double actual;

			// act
			actual = GINStock.PERatio(price);

			// assert
			assertEquals("P/E Ratio calcuated incorrectly", expected, actual, 0);
		} catch (StockCalculationException e) {
			fail("P/E Ratio calculation threw an exception");
		}

	}

	@Test
	public void PERatioDivideByZeroTest() {
		double lastDividend = 0;
		double fixedDividend = 0.02;
		double price = 10;
		double parValue = 100;
		String symbol = "GIN";

		Stock GINStock = new Stock(symbol, StockType.Preferred, lastDividend, parValue, fixedDividend);

		try {
			// act
			double actual = GINStock.PERatio(price);

		} catch (StockCalculationException e) {
			// assert
			assertTrue("Wrong exception message returned", e.getMessage().contains(Stock.LAST_DIVIDEND_ZERO_MESSAGE));
			return;
		}

		fail("No exception was thrown.");

	}
}
