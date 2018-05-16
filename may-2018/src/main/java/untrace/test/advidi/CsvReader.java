package untrace.test.advidi;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import untrace.test.models.InternalClosingPriceModel;

public class CsvReader {
	
	private static final Path path = Paths.get("src/main/resources/F.csv");
	
	public CsvReader() {
		
	}
	
	public List<InternalClosingPriceModel> extractAllData() {
		
		List<InternalClosingPriceModel> allData = new ArrayList<InternalClosingPriceModel>();
		try {
			
			allData = new ArrayList<InternalClosingPriceModel>();
		
			for ( String line : Files.lines( path ).
												skip( 1 ). // skip the header row of the file
												collect( Collectors.toList() )){
				
				
				if ( line != null && line.length() > 0 && line.contains(",") ) { // ensure the line is readable and can be split
	
						InternalClosingPriceModel entry = new InternalClosingPriceModel( line.split(",")[0] , line.split(",")[4] ); // column 0 = date, column 4 = closing price 
						allData.add( entry );
					}
				}	
		
		}catch(Exception e) {
			e.printStackTrace(); // TODO: specific exception handling suggested.
		}
		
		return allData;
	}
	
}
