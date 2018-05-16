package untrace.test.rest.models;

import java.util.List;

import untrace.test.models.ExternalDataPoint;

public class ClosingPriceResponse {
	
	List<ExternalDataPoint> datapoints;
	
	public ClosingPriceResponse() {
	}
	
	public ClosingPriceResponse( List<ExternalDataPoint> datapoints ) {
		this.datapoints = datapoints;
	}

	public List<ExternalDataPoint> getDatapoints() {
		return datapoints;
	}

	public void setDatapoints(List<ExternalDataPoint> datapoints) {
		this.datapoints = datapoints;
	}

}