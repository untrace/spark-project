package untrace.test.advidi;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import untrace.test.models.ExternalDataPoint;
import untrace.test.models.InternalClosingPriceModel;
import untrace.test.models.ProcessingModel;

public class ChartDataSingleton {
	
	/*** NOTE: NOT THREAD SAFE - NO LOCK MECHANISM ***/
	
	private static ChartDataSingleton chartData = null;
	private static List<InternalClosingPriceModel> allData = null;
	
	private ChartDataSingleton() {
		// Private no-args constructor
	}
	
	private ChartDataSingleton(List<InternalClosingPriceModel> allData ){
		
		this.allData = allData;
	}
	
	private static ChartDataSingleton init() {
		
		// TODO: NOT THREAD SAFE - LOCK IMPLEMENTATION MAY BE REQUIRED
		
		if ( allData == null ) {
			chartData = new ChartDataSingleton( new CsvReader().extractAllData() );
		} 
				
		
		return chartData;
	}
	
	public static ChartDataSingleton getInstance() {
		 // Init if empty
		
			if ( chartData == null ) {
				init();
			}
		
		return chartData;
	}
	
	
	public List<ExternalDataPoint> processDailyData( int year, int month ){
		
		HashMap<Integer, ProcessingModel> totalsProcessing = new HashMap<Integer, ProcessingModel>(); // hold the totals in memory
		for ( InternalClosingPriceModel lodeadData : allData ) {
			
			if ( lodeadData.getYear() == year && lodeadData.getMonth() == month ) { // find the matching year and month for data extraction
				
 				if ( totalsProcessing.containsKey( lodeadData.getDay()) ) {
 					
 					totalsProcessing.get( lodeadData.getDay() ).addValue( lodeadData.getClosingPrice() ); // update the total
				}
				else { // the month doesn't exist in the map - add it
					
					totalsProcessing.put( lodeadData.getDay(), new ProcessingModel( lodeadData.getClosingPrice() ));
				}
			}
		}
		
		// TODO: DETECT MISSING DAYS AND INJECT ZEROS IF REQUIRED - COLLECTION INTERSECTION EASY
		
		List<ExternalDataPoint> output = new ArrayList<ExternalDataPoint>(); // guarantee an empty collection at worst
		for ( Integer day : totalsProcessing.keySet() ) {
			
			output.add( new ExternalDataPoint(""+year+"-"+month+"-"+day ,  
						totalsProcessing.get( day ).getAverage().setScale(2, RoundingMode.DOWN ).floatValue() ));
		}
		
		return output;
		
	}
	
	public List<ExternalDataPoint> processMonthlyData( int year ) {
		
		HashMap<Integer, ProcessingModel> totalsProcessing = new HashMap<Integer, ProcessingModel>(); // hold the totals in memory
		for ( InternalClosingPriceModel lodeadData : allData ) {
			
			if ( lodeadData.getYear() == year) { // find the matching year for data extraction
				
 				if ( totalsProcessing.containsKey( lodeadData.getMonth()) ) {
 					
 					totalsProcessing.get( lodeadData.getMonth() ).addValue( lodeadData.getClosingPrice() ); // update the total
				}
				else { // the month doesn't exist in the map - add it
					
					totalsProcessing.put( lodeadData.getMonth(), new ProcessingModel( lodeadData.getClosingPrice() ));
				}
			}
		}
		
		// TODO: DETECT MISSING MONTHS AND INJECT ZEROS IF REQUIRED - COLLECTION INTERSECTION EASY
		
		List<ExternalDataPoint> output = new ArrayList<ExternalDataPoint>(); // guarantee an empty collection at worst
		for ( Integer month : totalsProcessing.keySet() ) {
			
			output.add( new ExternalDataPoint(""+year+"-"+month ,  
						totalsProcessing.get( month ).getAverage().setScale(2, RoundingMode.DOWN ).floatValue() ));
		}
		
		return output;
	}
	
	public List<ExternalDataPoint> processYearlyData() {
		
		HashMap<Integer, ProcessingModel> totalsProcessing = new HashMap<Integer, ProcessingModel>(); // hold the totals in memory
		for ( InternalClosingPriceModel lodeadData : allData ) {
				
 				if ( totalsProcessing.containsKey( lodeadData.getYear()) ) {
 					
 					totalsProcessing.get( lodeadData.getYear() ).addValue( lodeadData.getClosingPrice() ); // update the total
				}
				else { // the month doesn't exist in the map - add it
					
					totalsProcessing.put( lodeadData.getYear(), new ProcessingModel( lodeadData.getClosingPrice() ));
				}
			}
		
		// TODO: DETECT MISSING YEARS AND INJECT ZEROS IF REQUIRED - COLLECTION INTERSECTION EASY
		
		List<ExternalDataPoint> output = new ArrayList<ExternalDataPoint>(); // guarantee an empty collection at worst
		for ( Integer year : totalsProcessing.keySet() ) {
			
			output.add( new ExternalDataPoint(""+year+"-" ,  
						totalsProcessing.get( year ).getAverage().setScale(2, RoundingMode.DOWN ).floatValue() ));
		}
		
		return output;
		
	}
	
	public List<ExternalDataPoint> processDay( Integer year, Integer month, Integer day ) {
		
		List<ExternalDataPoint> output = new ArrayList<ExternalDataPoint>(); // guarantee an empty collection at worst
		for ( InternalClosingPriceModel lodeadData : allData ) {
			
			if ( lodeadData.getDay() == day && lodeadData.getMonth() == month && lodeadData.getYear() == year ) {
				
				output.add(new ExternalDataPoint(""+year+"-"+month+"-"+day, lodeadData.getClosingPrice().setScale(2, RoundingMode.DOWN ).floatValue() )); // There should only be one result.
			}
		}
		return output;

	}

	
}