package core;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

//@author: Declan McKenna
//@version: 1.0.0
//@description: This is the class that handles reading from/writing to the CSV files.
public class Reader 
{
	public HashMap<String, Instrument> readFromFile(String fileName) throws Exception
	{
		File inputFile = new File(fileName);
		CSVReader csvReader = new CSVReader(new FileReader(inputFile),',');
		
		HashMap<String, Instrument> instrumentList = new HashMap<String, Instrument>();
		String[] nextLine;
		
		while((nextLine = csvReader.readNext()) != null)
		{
			Instrument tempInstrument = null;
			if (nextLine[0] != "instrument_type")
			{
				//If the first column read marks the row as an Equity
				if (nextLine[0].equalsIgnoreCase("Equity"))
				{
					//Make tempInstrument an instance of the Equity class
					tempInstrument = new Equity(nextLine[1], Integer.parseInt(nextLine[2]), Float.parseFloat(nextLine[3]), Float.parseFloat(nextLine[4])); 
				}
				//If the first column read marks the row as a Bond
				else if (nextLine[0].equalsIgnoreCase("Bond"))
				{
					//Make tempInstrument an instance of the Bond class
					tempInstrument = new Bond(nextLine[1], Integer.parseInt(nextLine[2]), Float.parseFloat(nextLine[5]));
				}//end if/ else if (nextLine[0])
				
				//Check if tempInstrument isn't still null before adding it to the HashMap
				if (tempInstrument != null)
				{
					instrumentList.put(tempInstrument.getName(), tempInstrument);
				}//end if (!tempInstrument)
			}//end if(!instrument_type)
		}//end while
		
		csvReader.close();
		return instrumentList;
	}//end readFromFile(String)
	
	public  void writeToFile(HashMap<String, Instrument> instruments, String fileName) throws IOException
	{
		CSVWriter writer = new CSVWriter(new FileWriter(fileName));
		Map<String, Instrument> treeMapIns = new TreeMap<String, Instrument>(instruments);
		Iterator<Map.Entry<String, Instrument>> it = treeMapIns.entrySet().iterator();
		
		while (it.hasNext())
		{
			Map.Entry<String, Instrument> currIns = (Map.Entry<String, Instrument>) it.next();
			String currentLine = currIns.getValue().getInstrumentType() + "," + currIns.getValue().getName() + "," 
								+ currIns.getValue().getQuantity() + "," + currIns.getValue().getProfit();
			writer.writeNext(currentLine.split(","), false);
		}//end while(it)
		
		writer.close();
	}//end writeToFile(HashMap<String, Instrument>)
	
	
}//end class Reader
