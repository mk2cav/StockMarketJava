package StockMarket.Trades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import StockMarket.Stocks.Stock;

public class TradeService {
	private final static Logger log = LogManager.getLogger(TradeService.class);

	public static final	String NO_TRADES_MESSAGE = "No Matching trades found";
	public static final	String NO_QUANTITY_MESSAGE = "Quantity cannot be Zero";

	private List<Trade> trades = new ArrayList<Trade>();

	public TradeService() {
	}

	/*
	 * Record a new Trade for a Stock
	 * 
	 * @param trade The trade
	 */
	public void RecordTrade(Trade trade) {
		trades.add(trade);
	}

	/*
	 * Calculate the Volume Weighted Stock Price based on trades in past 15
	 * minutes for a stock
	 * 
	 * @return The Fifteen Minute Volume Weighted Stock Price
	 */
	public double FifteenMinuiteVolumeWeightedStockPrice(Stock stock) throws TradesCalculationException
        {
            Date comparisonTime = DateUtils.addMinutes(new Date(), -15);
            List<Trade> matchingTrades = trades.stream().filter(t -> t.getStock().equals(stock) && t.getTimestamp().after(comparisonTime)).collect(Collectors.toList());

            if (matchingTrades.size() == 0)
            {
                log.debug("No Matching trades found");
                throw new TradesCalculationException(NO_TRADES_MESSAGE);
            }

            double quantity = matchingTrades.stream().mapToDouble(t -> t.getQuantity()).sum();
            if (quantity == 0)
            {
                log.debug("Fifteen Minuite Volume Weighted Stock Price cannot be calculated, Quantity cannot be Zero");
                throw new TradesCalculationException(NO_QUANTITY_MESSAGE);
            }

            double tradePriceQuantity = matchingTrades.stream().mapToDouble(t -> (t.getQuantity() * t.getPrice())).sum();

            return tradePriceQuantity / quantity;
        }
}
