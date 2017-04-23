package StockMarket.Stocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

    public class StockService
    {
    	private final static Logger log = LogManager.getLogger(StockService.class);
        private Map<String, Stock> stocks = new HashMap<String, Stock>();
        
        public StockService()
        {
        }

        // Initialise the stocks list
        public void LoadStockList()
        {
            stocks.put("TEA", new Stock("TEA", StockType.Common, 0, 100));
            stocks.put("POP", new Stock("POP", StockType.Common, 8, 100));
            stocks.put("ALE", new Stock("ALE", StockType.Common, 23, 60));
            stocks.put("GIN", new Stock("GIN", StockType.Common, 8, 100, 2));
            stocks.put("JOE", new Stock("JOE", StockType.Common, 13, 250));
        }

        /*
        * Get a Stock object for a Stock Symbol
        * 
        * @param symbol The Stock Symbol
        * @return The Stock
        */
        public Stock GetStock(String symbol)
        {
            return this.stocks.containsKey(symbol) ? this.stocks.get(symbol) : null;
        }

        /*
        * Get a Stock price for a Stock Symbol
        * 
        * @param symbol The Stock Symbol
        * @return The Stock
        */
        public double GetStockPrice(String symbol)
        {
            // Return random number for stock price, assumed that true implementation will use a price service to retrieve current stock prices.
            Random rnd = new Random();
            return rnd.nextInt(1000)+1;
        }

        /*
        * Get a Stock price list for all Stocks
        * 
         * @return The StockPrice List
        */
        public List<StockPrice> GetAllStockPriceList()
        {
            List<StockPrice> stockPrices = new ArrayList<StockPrice>();
            Iterator<Entry<String, Stock>> stocksIterator = this.stocks.entrySet().iterator();
            while (stocksIterator.hasNext()) {
                Map.Entry<String, Stock> pair = (Map.Entry<String, Stock>) stocksIterator.next();
                stockPrices.add(new StockPrice(pair.getValue().getSymbol(), GetStockPrice(pair.getValue().getSymbol())));
                stocksIterator.remove();
            }
            
            return stockPrices;
        }

        /*
        * Get a Stock price for a Stock Symbol
        * 
        * @param symbol The Stock Symbol
        * @return The Stock
        */
        public double CalculateGBCEAllShareIndex(List<StockPrice> stockPrices)
        {
            return Math.pow(stockPrices.stream().mapToDouble(sp -> sp.getPrice()).sum(), 1.0 / stockPrices.size());
        }        
    }
