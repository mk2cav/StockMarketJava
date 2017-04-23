package StockMarket.Trades;

import java.util.Date;
import StockMarket.Stocks.Stock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Trade {
	private final static Logger log = LogManager.getLogger(Trade.class);

	private Date timestamp;
	private int quantity;
	private TradeType type;
	private double price;
	private Stock stock;

	// Initialise the trade object
	public Trade(Date timestamp, int quantity, TradeType type, double price, Stock stock) {
		this.timestamp = timestamp;
		this.quantity = quantity;
		this.type = type;
		this.price = price;
		this.stock = stock;
	}
	
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public TradeType getType() {
		return this.type;
	}

	public void setType(TradeType type) {
		this.type = type;
	}
	
	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
}
