package core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@author: Declan McKenna
//@version: 1.0.0
//@description: This is the class that is executed when the application is run.
public class MainApp 
{
	public static void main(String[] args) throws Exception
	{
		ApplicationContext context = new AnnotationConfigApplicationContext(config.AppConfig.class);
		Reader readWrite = (Reader) context.getBean(Reader.class);
		
		String inputFile = "inputCSV.txt";
		String outputFile = "outputCSV.txt";
		readWrite.writeToFile(readWrite.readFromFile(inputFile), outputFile);
		
		((ConfigurableApplicationContext)context).close();
	}//end main(String[])
}//end class Driver
