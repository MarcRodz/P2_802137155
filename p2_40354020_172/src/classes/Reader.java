package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Edgardo R. Hernandez Matos, Mario A. Rodriguez
 *
 */
public class Reader {
	/**
	 * 
	 * @param fileName
	 *            name of file to be processed
	 * @return a list of the customers found in file
	 */
	public static List<Customer> readCustomersFromCSV(String fileName) {
		List<Customer> customers = new ArrayList<>();
		Path pathToFile = Paths.get(fileName); // create an instance of
												// BufferedReader // using try
												// with resource, Java 7 feature
												// to close resources

		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) 
		{
			// read the first line from the text file
			String line = br.readLine();
			// loop until all lines are read

			while (line != null) {
				// use string.split to load a string array with the values from
				// each line of
				// the file, using a comma as the delimiter
				line = line.replaceAll("[$]*", "");
				String[] attributes = line.split("\\s+");
				if(attributes.length!=2){
					customers = new ArrayList<>();
					break;
				}
				for (int i = 0; i < attributes.length; i++) {
					attributes[i] = attributes[i].trim();
				}

				Customer customer = createCustomer(attributes);
				// adding customer into ArrayList
				customers.add(customer);
				// read next line before looping
				// if end of file reached, line would be null
				line = br.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return customers;
	}

	/**
	 * Returns the filename contained inside a file
	 * @param metadata String array 
	 * @return filename corresponding to the read file
	 */
	public static String createfilename(String[] metadata) {
		if (metadata.equals(null)) {
			System.out.println("Sorry metadata is null!");
			return null;
		}
		String filename = (metadata[0]);

		// create and return customer of this metadata
		return filename;
	}
	
	
	/**
	 * Returns a List of strings representing a file
	 * composed of other filenames
	 * @param Filename String of file that contains other filenames
	 * @return List of Strings representing files inside Filename
	 */
	public static List<String> readMultipleFiles(String Filename)
	{
		List<String> files = new ArrayList<>();
		Path pathToFile = Paths.get(Filename); 	// create an instance of
												// BufferedReader // using try
												// with resource, Java 7 feature
												// to close resources

		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			// read the first line from the text file
			String line = br.readLine();

			// loop until all lines are read
			while (line != null) 
			{
				// use string.split to load a string array with the values from
				// each line of
				// the file, using a comma as the delimiter
				line = line.replaceAll("[$]*", "");
				String[] attributes = line.split("\\s+");
				
				for (int i = 0; i < attributes.length; i++) {
					attributes[i] = attributes[i].trim();
				}

				String filename = createfilename(attributes);
				// adding customer into ArrayList
				files.add(filename);
				// read next line before looping
				// if end of file reached, line would be null
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return files;
	}
	
	/**
	 * Verifies that the File exists.
	 * 
	 * @param f
	 *            File which we want to verify,
	 * @return boolean denoting the existence of the file f.
	 */
	public static boolean fileExists(File f) 
	{
		boolean exists = false;

		if (f.exists() && !f.isDirectory()) 
		{
//			System.out.println("File does exist!\n");
			exists = true;
		}

		return exists;
	}
	
	/**
	 * 
	 * @param metadata
	 *            String array representing customer info
	 * @return customer object with params from metadata
	 */
	public static Customer createCustomer(String[] metadata) 
	{
		int MomentofArrival = Integer.parseInt(metadata[0]);
		int ServiceTime = Integer.parseInt(metadata[1]);

		// create and return customer of this metadata
		return new Customer(MomentofArrival, ServiceTime);
	}
}
