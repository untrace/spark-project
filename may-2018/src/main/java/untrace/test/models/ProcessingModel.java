package untrace.test.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProcessingModel {
	
	private BigDecimal total;
	private int datasetSize;
	
	public ProcessingModel(){
		this.total=new BigDecimal(0);
		this.datasetSize=0;
	}
	
	public ProcessingModel( BigDecimal value ){
		this.total = value;
		this.datasetSize = 1; // starting set size should be 1 with a specified constructor
	}
	
	public void addValue( BigDecimal value ) {
		this.total.add(value);
		this.datasetSize++;
	}
	
	public BigDecimal getAverage() {
		return this.total.divide( new BigDecimal( datasetSize ), 2, RoundingMode.HALF_DOWN); // this is a raw unrounded value 
	}

}
