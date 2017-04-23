package StockMarket.Stocks.Tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import StockMarket.Stocks.Stock;
import StockMarket.Stocks.StockType;
import StockMarket.Trades.Trade;
import StockMarket.Trades.TradeService;
import StockMarket.Trades.TradeType;
import StockMarket.Trades.TradesCalculationException;

import org.apache.commons.lang3.time.DateUtils;

public class TradeServiceTests {

	@Test
	public void FifteenMinuiteVolumeWeightedStockPriceTest() {
		double lastDividend = 20;
		double expected = 15;
		double parValue = 100;
		String TEASymbol = "TEA";
		String GINSymbol = "GIN";

		Stock TEAStock = new Stock(TEASymbol, StockType.Common, lastDividend, parValue);
		Stock GINStock = new Stock(GINSymbol, StockType.Common, lastDividend, parValue);

		TradeService tradeService = new TradeService();
		tradeService.RecordTrade(new Trade(DateUtils.addMinutes(new Date(), -16), 100, TradeType.Buy, 10, TEAStock));
		tradeService.RecordTrade(new Trade(new Date(), 100, TradeType.Buy, 10, GINStock));
		tradeService.RecordTrade(new Trade(new Date(), 100, TradeType.Buy, 10, TEAStock));
		tradeService.RecordTrade(new Trade(new Date(), 300, TradeType.Buy, 20, TEAStock));
		tradeService.RecordTrade(new Trade(new Date(), 200, TradeType.Buy, 10, TEAStock));

		try {
			double actual;

			// act
			actual = tradeService.FifteenMinuiteVolumeWeightedStockPrice(TEAStock);

			// assert
			assertEquals("Fifteen Minuite Volume Weighted Stock Price Test calcuated incorrectly", expected, actual, 0);
		} catch (TradesCalculationException e) {
			fail("Fifteen Minuite Volume Weighted Stock Price calculation threw an exception");
		}
	}

	@Test
	public void FifteenMinuiteVolumeWeightedStockPriceDivideByZeroTest() {
		double lastDividend = 20;
		double parValue = 100;
		String TEASymbol = "TEA";
		String GINSymbol = "GIN";

		Stock TEAStock = new Stock(TEASymbol, StockType.Common, lastDividend, parValue);
		Stock GINStock = new Stock(GINSymbol, StockType.Common, lastDividend, parValue);

		TradeService tradeService = new TradeService();
		tradeService.RecordTrade(new Trade(DateUtils.addMinutes(new Date(), -16), 100, TradeType.Buy, 10, TEAStock));
		tradeService.RecordTrade(new Trade(new Date(), 100, TradeType.Buy, 10, GINStock));
		tradeService.RecordTrade(new Trade(new Date(), 0, TradeType.Buy, 10, TEAStock));

		try {
			// act
			double actual = tradeService.FifteenMinuiteVolumeWeightedStockPrice(TEAStock);

		} catch (TradesCalculationException e) {
			// assert
			assertTrue("Wrong exception message returned", e.getMessage().contains(TradeService.NO_QUANTITY_MESSAGE));
			return;
		}

		fail("No exception was thrown.");
		
	}
}
