package untrace.test.models;

public class ExternalDataPoint {
	
	private String date;
	private float closingPrice;
	
	public ExternalDataPoint(String date, float closingPrice) {
		this.date = date;
		this.closingPrice = closingPrice;
	}

	public String getDate() {
		return date;
	}

	public float getClosingPrice() {
		return closingPrice;
	}

}
