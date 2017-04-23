package StockMarket.Stocks;

public class StockPrice {
	private String symbol;
	private double price;

	// Initialise the Stock Price object
	public StockPrice(String symbol, double price) {
		this.symbol = symbol;
		this.price = price;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
