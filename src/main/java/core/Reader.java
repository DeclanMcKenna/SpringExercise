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
	//This method is used to read the data from the input csv file and return  a hashmap with the data
	//@param: fileName - This is the string holding the name of the file to be read from
	//@return: This is the hashmap created from the data read in from the input csv file
	public HashMap<String, Instrument> readFromFile(String fileName) throws Exception
	{
		File inputFile = new File(fileName);
		CSVReader csvReader = new CSVReader(new FileReader(inputFile),',');
		
		HashMap<String, Instrument> instrumentList = new HashMap<String, Instrument>();
		//Create the string array that will hold each line read from the file
		String[] nextLine;
		
		//While there is a next line in the file to move on to
		while((nextLine = csvReader.readNext()) != null)
		{
			//Set tempInstrument as null at the beginning of each loop
			Instrument tempInstrument = null;
			if (nextLine[0] != "instrument_type")
			{
				//If the first column read marks the row as an Equity
				if (nextLine[0].equalsIgnoreCase("Equity"))
				{
					tempInstrument = new Equity(nextLine[1], Integer.parseInt(nextLine[2]), Float.parseFloat(nextLine[3]), Float.parseFloat(nextLine[4])); 
				}
				//If the first column read marks the row as a Bond
				else if (nextLine[0].equalsIgnoreCase("Bond"))
				{
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
	
	//This method will be called in order to write the data retrieved from readFromFile to the output csv file. 
	//@param: instruments - This is the hashmap containing the key-value pairs of the different instruments read in from the input csv
	//file that will be used to take each instrument's attributes for writing.
	//@param: fileName - This is the string holding the name of the file that is to be written to
	public  void writeToFile(HashMap<String, Instrument> instruments, String fileName) throws IOException
	{
		CSVWriter writer = new CSVWriter(new FileWriter(fileName));
		//Convert the input hashmap of key-value pairs into a treemap in order to sort them in ascending order based on their name
		Map<String, Instrument> treeMapIns = new TreeMap<String, Instrument>(instruments);
		Iterator<Map.Entry<String, Instrument>> it = treeMapIns.entrySet().iterator();
		writer.writeNext("instrument_type,name,quantity,profit".split(","), false);
	
		//While there is another key-value pair in the map
		while (it.hasNext())
		{
			//Assign a temporary instrument as the key-value pair held by the instrument in order to be able to access the instrument's
			//attribute values through its getter methods
			Map.Entry<String, Instrument> currIns = (Map.Entry<String, Instrument>) it.next();
			//Arrange the attributes int othe correct order and concatenate them into the one string
			String currentLine = currIns.getValue().getInstrumentType() + "," + currIns.getValue().getName() + "," 
								+ currIns.getValue().getQuantity() + "," + currIns.getValue().getProfit();
			//Write the string containing the current instrument's attribute values to the file, using the same split method as earlier
			//on the string to split it up into separate elements. The false boolean indicates no quote characters are to be used
			writer.writeNext(currentLine.split(","), false);
		}//end while(it)
		
		writer.close();
	}//end writeToFile(HashMap<String, Instrument>)
	
	
}//end class Reader
