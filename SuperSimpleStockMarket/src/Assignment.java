import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import StockMarket.Stocks.Stock;
import StockMarket.Stocks.StockCalculationException;
import StockMarket.Stocks.StockService;
import StockMarket.Trades.Trade;
import StockMarket.Trades.TradeService;
import StockMarket.Trades.TradeType;
import StockMarket.Trades.TradesCalculationException;

public class Assignment {

	private final static Logger log = LogManager.getLogger(Assignment.class);

	public static void main(String[] args) {
		log.debug("Start Program");

        log.debug("Load stock service");
        StockService stockService = new StockService();
        stockService.LoadStockList();
        log.debug("Load trade service");
        TradeService tradeService = new TradeService();

        // a) i.
        CalculateTEAStockDividendYield(stockService);

        // a) ii.
        CalculatePOPStockPERatio(stockService);

        // a) iii.
        CreateSampleALEStockTrades(stockService, tradeService);

        // a) iv.
        CalculateALEStockFMVWStockPrice(stockService, tradeService);

        // b)
        CalculateGBCEAllShareIndex(stockService);

	}
	
	/*
     * a) i. Example of calculating the Dividend Yield for a stock.
     * 
     * @param stockService The StockService
     */
     private static void CalculateTEAStockDividendYield(StockService stockService)
     {
         log.debug("Calculate TEA Stock Dividend Yield");
         try
         {
             Stock TEAStock = stockService.GetStock("TEA");
             if (TEAStock != null)
             {
                 double price = 30;
                 double TEADividendYield = TEAStock.DividendYield(price);
                 log.debug("TEA Stock P/E Ratio. " + Double.toString(TEADividendYield));
             }
             else
             {
                 log.warn("Unable to find TEA stock.");
             }
         }
         catch (StockCalculationException e)
         {
             log.warn("Unable to calculate TEA Stock Dividend Yield. " + e.getMessage());
         }
     }

     /*
     * a) ii. Example of calculating the P/E Ratio for a stock.
     * 
     * @param stockService The StockService
     */
     private static void CalculatePOPStockPERatio(StockService stockService)
     {
         log.debug("Calculate POP Stock P/E Ratio");
         try
         {
             Stock POPStock = stockService.GetStock("POP");
             if (POPStock != null)
             {
                 double price = 20;
                 double POPPERatio = POPStock.PERatio(price);
                 log.debug("POP Stock P/E Ratio = " + Double.toString(POPPERatio));
             }
             else
             {
                 log.warn("Unable to find POP stock.");
             }
         }
         catch (StockCalculationException e)
         {
             log.debug("Unable to calculate POP Stock P/E Ratio. " + e.getMessage());
         }
     }

     /*
     * a) iii. Record sample Trades for ALE stocks.
     * 
     * @param stockService The StockService
     * @param tradeService The TradeService
     */
     private static void CreateSampleALEStockTrades(StockService stockService, TradeService tradeService)
     {
         log.debug("Record Sample Trades");
         Stock ALEStock = stockService.GetStock("ALE");
         if (ALEStock != null)
         {
             tradeService.RecordTrade(new Trade(new Date(), 100, TradeType.Buy, 10, ALEStock));
             tradeService.RecordTrade(new Trade(new Date(), 50, TradeType.Buy, 15, ALEStock));
             tradeService.RecordTrade(new Trade(new Date(), 150, TradeType.Buy, 18, ALEStock));
         }
         else
         {
             log.warn("Unable to find ALE stock.");
         }
     }

     /*
     * a) iv. Record sample Trades for ALE stocks
     * 
     * @param stockService The StockService
     * @param tradeService The TradeService
     */
     private static void CalculateALEStockFMVWStockPrice(StockService stockService, TradeService tradeService)
     {
         log.debug("Calculate ALE Stock FMVWSP");
         Stock ALEStock = stockService.GetStock("ALE");
         if (ALEStock != null)
         {
             try
             {
                 double FMVWSP = tradeService.FifteenMinuiteVolumeWeightedStockPrice(ALEStock);
                 log.debug("ALE Stock FMVWSP = " + Double.toString(FMVWSP));
             }
             catch (TradesCalculationException e)
             {
                 log.debug("Unable to calculate ALE FMVWSP. " + e.getMessage());
             }
         }
         else
         {
             log.warn("Unable to find ALE stock.");
         }
     }

    /*
    * b) Calculate the GBCE All Share Index using the geometric mean of prices for all stocks
    * 
    * @param stockService The StockService
    */
     private static void CalculateGBCEAllShareIndex(StockService stockService)
     {
         log.debug("Calculate GBCE All Share Index");
         double GBCEAllShareIndex = stockService.CalculateGBCEAllShareIndex(stockService.GetAllStockPriceList());
         log.debug("GBCE All Share Index = " + Double.toString(GBCEAllShareIndex));
     }

}
