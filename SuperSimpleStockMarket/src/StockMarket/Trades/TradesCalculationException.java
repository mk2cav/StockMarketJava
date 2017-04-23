package StockMarket.Trades;

public class TradesCalculationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7999963758917935719L;
	private String message;

	public TradesCalculationException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
