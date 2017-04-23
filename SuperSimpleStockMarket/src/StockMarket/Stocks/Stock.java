package StockMarket.Stocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Stock {
	private  final static Logger log = LogManager.getLogger(Stock.class);

	public static final	String PRICE_ZERO_MESSAGE = "Ticker Price cannot be Zero";
	public static final	String LAST_DIVIDEND_ZERO_MESSAGE = "Last Dividend cannot be Zero";

	private String symbol;
	private StockType type;
	private double lastDividend;
	private double fixedDividend;
	private double parValue;

	// Initialise the stock object
	public Stock(String symbol, StockType type, double lastDividend, double parValue, double fixedDividend)
    {
        this.symbol = symbol;
        this.type = type;
        this.lastDividend = lastDividend;
        this.fixedDividend = parValue;
        this.parValue = fixedDividend;
    }

	public Stock(String symbol, StockType type, double lastDividend, double parValue)
    {
		this(symbol, type, lastDividend, parValue, 0);
    }
	
	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public StockType getType() {
		return this.type;
	}

	public void setType(StockType type) {
		this.type = type;
	}
	
	public double getLastDividend() {
		return this.lastDividend;
	}

	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}
	
	public double getFixedDividend() {
		return this.fixedDividend;
	}

	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	
	public double getParValue() {
		return this.parValue;
	}

	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	/*
	 * Calculate the dividend yield of a stock based on the Stock price
	 * 
	 * @param price The Stock price
	 * 
	 * @return The Dividend Yield
	 */
	public double DividendYield(double price) throws StockCalculationException {
		if (price == 0) {
			log.debug("Dividend Yield cannot be calculated, Ticker Price cannot be Zero");
			throw new StockCalculationException(PRICE_ZERO_MESSAGE);
		}

		if (this.type == StockType.Common) {
			return this.lastDividend / price;
		} else {
			return (this.fixedDividend * (this.parValue / 100)) / price;
		}
	}

	/*
	 * Calculate the P/E Ratio of a stock based on the stock price
	 * 
	 * @param price The Stock price
	 * 
	 * @return The PE Ratio
	 */
	public double PERatio(double price) throws StockCalculationException {
		if (this.lastDividend == 0) {
			log.debug("P/E Ratio cannot be calculated, Last Dividend cannot be Zero");
			throw new StockCalculationException(LAST_DIVIDEND_ZERO_MESSAGE);
		}

		return price / this.lastDividend;
	}
}
