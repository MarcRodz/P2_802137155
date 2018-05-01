package testers;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import classes.Reader;
import classes.SLMS;
import classes.Customer;
import classes.MLMS;
import classes.MLMSBLL;
import classes.MLMSBWT;
/**
 * 
 * @author Edgardo R. Hernandez Matos, Mario A. Rodriguez
 *
 */
public class Main {
	/**
	 * This is the main loop in charge of the processing and handling of files, objects, and exceptions
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException 
	{
		Main main = new Main();
		
		try{
			main.loopy();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void loopy() throws FileNotFoundException
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Provide a filename to be processed:");

		// Checks to see if the user said yes or no
		// to there being more filenames in the provided file

		String input = scan.nextLine();

		// Verifies that it is a valid file name and that it exists in the project directorydirectory
		// if(input.equals("") || input.equals(" ") || !Reader.fileExists(new File(input)))
		 while(input.equals("") || input.equals(" ") || !Reader.fileExists(new File(input)) )
		 {
			 System.out.println("Sorry file does not exist!\n");
			 System.out.println("Provide a filename to be processed:");
			 input = scan.nextLine();
		 }
		 
		 //Closes the scanner object
		 scan.close();
		 
		 
		String InputFilename = input;
		
		//if file ends in a .txt format
		if (InputFilename.contains("Files"))
		{
			//Create a list of files, which were contained in the .txt file
			List<String> inputfiles = Reader.readMultipleFiles(InputFilename);
			
			//For each input file in the list,
			//create the corresponding outputfile
			for (String i : inputfiles) 
			{
				InputFilename = i;
				File f = new File(InputFilename);
				if(f.exists()&& !f.isDirectory()){
					
					List<Customer> customers = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers1 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers2 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers3 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers4 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers5 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers6 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers7 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers8 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers9 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers10 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers11 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers12 = Reader.readCustomersFromCSV(InputFilename);
					
					if(!customers.isEmpty()){

						SLMS mySLMS_1server = new SLMS(customers, 1);
						SLMS mySLMS_3server = new SLMS(customers1, 3);
						SLMS mySLMS_5server = new SLMS(customers2, 5);

						MLMS myMLMS_1server = new MLMS(1);
						myMLMS_1server.evaluate(customers3);
						MLMS myMLMS_3server = new MLMS(3);
						myMLMS_3server.evaluate(customers4);
						MLMS myMLMS_5server = new MLMS(5);
						myMLMS_5server.evaluate(customers5);

						MLMSBLL myMLMSBLL_1server = new MLMSBLL(1);
						myMLMSBLL_1server.evaluate(customers6);
						MLMSBLL myMLMSBLL_3server = new MLMSBLL(3);
						myMLMSBLL_3server.evaluate(customers7);
						MLMSBLL myMLMSBLL_5server = new MLMSBLL(5);
						myMLMSBLL_5server.evaluate(customers8);

						MLMSBWT myMLMSBWT_1server = new MLMSBWT(1);
						myMLMSBWT_1server.evaluate(customers9);
						MLMSBWT myMLMSBWT_3server = new MLMSBWT(3);
						myMLMSBWT_3server.evaluate(customers10);
						MLMSBWT myMLMSBWT_5server = new MLMSBWT(5);
						myMLMSBWT_5server.evaluate(customers11);
				
						// Removes the .cvs and place .out in the input name and places
						// the new string in the output name
						String OutputFilename = InputFilename.replaceAll(".txt", "_OUT.txt");
		
						PrintStream out = new PrintStream(new FileOutputStream(OutputFilename));
						System.setOut(out);
						// output to the file a line
						
						out.println("Number of customers is: " + (double) customers12.size());
						out.println("SLMS 1:" + String.format("%.2f", (double) mySLMS_1server.Time()) + " " 
						+ String.format("%.2f", mySLMS_1server.AvergaeWaitTime())+ " " + 
								String.format("%.2f", mySLMS_1server.AvergaeNoCOverpassing()));
						out.println("SLMS 3:" + String.format("%.2f", (double) mySLMS_3server.Time()) + " " 
								+ String.format("%.2f", mySLMS_3server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", mySLMS_3server.AvergaeNoCOverpassing()));
						out.println("SLMS 5:" + String.format("%.2f", (double)mySLMS_5server.Time()) + " " 
								+ String.format("%.2f", mySLMS_5server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", mySLMS_5server.AvergaeNoCOverpassing()));
						out.println("MLMS 1:" + String.format("%.2f", (double)myMLMS_1server.Time()) + " " 
								+ String.format("%.2f", myMLMS_1server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMS_1server.AvergaeNoCOverpassing()));
						out.println("MLMS 3:" + String.format("%.2f", (double)myMLMS_3server.Time()) + " " 
								+ String.format("%.2f", myMLMS_3server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMS_3server.AvergaeNoCOverpassing()));
						out.println("MLMS 5:" + String.format("%.2f", (double)myMLMS_5server.Time()) + " " 
								+ String.format("%.2f", myMLMS_5server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMS_5server.AvergaeNoCOverpassing()));
						out.println("MLMSBLL 1:" + String.format("%.2f", (double)myMLMSBLL_1server.Time()) + " " 
								+ String.format("%.2f", myMLMSBLL_1server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMSBLL_1server.AvergaeNoCOverpassing()));
						out.println("MLMSBLL 3:" + String.format("%.2f", (double)myMLMSBLL_3server.Time()) + " " 
								+ String.format("%.2f", myMLMSBLL_3server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMSBLL_3server.AvergaeNoCOverpassing()));
						out.println("MLMSBLL 5:" + String.format("%.2f", (double)myMLMSBLL_5server.Time()) + " " 
								+ String.format("%.2f", myMLMSBLL_5server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMSBLL_5server.AvergaeNoCOverpassing()));
						out.println("MLMSBWT 1:" + String.format("%.2f", (double)myMLMSBWT_1server.Time()) + " " 
								+ String.format("%.2f", myMLMSBWT_1server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMSBWT_1server.AvergaeNoCOverpassing()));
						out.println("MLMSBWT 3:" + String.format("%.2f", (double)myMLMSBWT_3server.Time()) + " " 
								+ String.format("%.2f", myMLMSBWT_3server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMSBWT_3server.AvergaeNoCOverpassing()));
						out.println("MLMSBWT 5:" + String.format("%.2f", (double)myMLMSBWT_5server.Time()) + " " 
								+ String.format("%.2f", myMLMSBWT_5server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMSBWT_5server.AvergaeNoCOverpassing()));
						out.close();	
					}
					else{
						
						//If the file is empty or it is detected that its content does not match the expected format.
						String OutputFilename = InputFilename.replaceAll(".txt", "_OUT.txt");

						PrintStream out = new PrintStream(new FileOutputStream(OutputFilename));
						System.setOut(out);
						out.println("Input file does not meet the expected format or it is empty.");
						out.close();
					}
				  
					  
				  }
				else{
					//When a file name shows in dataFiles.txt, but the file is not in the directory.
					String OutputFilename = InputFilename.replaceAll(".txt", "_OUT.txt");

					PrintStream out = new PrintStream(new FileOutputStream(OutputFilename));
					System.setOut(out);
					out.println("Input file not found. ");
					out.close();
				  }

			}
		} 
		//Otherwise just process a single file
		else {
			File f = new File(InputFilename);
			  if(f.exists()&& !f.isDirectory()){
				  List<Customer> customers = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers1 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers2 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers3 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers4 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers5 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers6 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers7 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers8 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers9 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers10 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers11 = Reader.readCustomersFromCSV(InputFilename);
					List<Customer> customers12 = Reader.readCustomersFromCSV(InputFilename);
					
					if(!customers.isEmpty()){

						SLMS mySLMS_1server = new SLMS(customers, 1);
						SLMS mySLMS_3server = new SLMS(customers1, 3);
						SLMS mySLMS_5server = new SLMS(customers2, 5);

						MLMS myMLMS_1server = new MLMS(1);
						myMLMS_1server.evaluate(customers3);
						MLMS myMLMS_3server = new MLMS(3);
						myMLMS_3server.evaluate(customers4);
						MLMS myMLMS_5server = new MLMS(5);
						myMLMS_5server.evaluate(customers5);

						MLMSBLL myMLMSBLL_1server = new MLMSBLL(1);
						myMLMSBLL_1server.evaluate(customers6);
						MLMSBLL myMLMSBLL_3server = new MLMSBLL(3);
						myMLMSBLL_3server.evaluate(customers7);
						MLMSBLL myMLMSBLL_5server = new MLMSBLL(5);
						myMLMSBLL_5server.evaluate(customers8);

						MLMSBWT myMLMSBWT_1server = new MLMSBWT(1);
						myMLMSBWT_1server.evaluate(customers9);
						MLMSBWT myMLMSBWT_3server = new MLMSBWT(3);
						myMLMSBWT_3server.evaluate(customers10);
						MLMSBWT myMLMSBWT_5server = new MLMSBWT(5);
						myMLMSBWT_5server.evaluate(customers11);
				
						// Removes the .cvs and place .out in the input name and places
						// the new string in the output name
						String OutputFilename = InputFilename.replaceAll(".txt", "_OUT.txt");
		
						PrintStream out = new PrintStream(new FileOutputStream(OutputFilename));
						System.setOut(out);
						// output to the file a line
						
						out.println("Number of customers is: " + (double) customers12.size());
						out.println("SLMS 1:" + String.format("%.2f", (double) mySLMS_1server.Time()) + " " 
						+ String.format("%.2f", mySLMS_1server.AvergaeWaitTime())+ " " + 
								String.format("%.2f", mySLMS_1server.AvergaeNoCOverpassing()));
						out.println("SLMS 3:" + String.format("%.2f", (double) mySLMS_3server.Time()) + " " 
								+ String.format("%.2f", mySLMS_3server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", mySLMS_3server.AvergaeNoCOverpassing()));
						out.println("SLMS 5:" + String.format("%.2f", (double)mySLMS_5server.Time()) + " " 
								+ String.format("%.2f", mySLMS_5server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", mySLMS_5server.AvergaeNoCOverpassing()));
						out.println("MLMS 1:" + String.format("%.2f", (double)myMLMS_1server.Time()) + " " 
								+ String.format("%.2f", myMLMS_1server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMS_1server.AvergaeNoCOverpassing()));
						out.println("MLMS 3:" + String.format("%.2f", (double)myMLMS_3server.Time()) + " " 
								+ String.format("%.2f", myMLMS_3server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMS_3server.AvergaeNoCOverpassing()));
						out.println("MLMS 5:" + String.format("%.2f", (double)myMLMS_5server.Time()) + " " 
								+ String.format("%.2f", myMLMS_5server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMS_5server.AvergaeNoCOverpassing()));
						out.println("MLMSBLL 1:" + String.format("%.2f", (double)myMLMSBLL_1server.Time()) + " " 
								+ String.format("%.2f", myMLMSBLL_1server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMSBLL_1server.AvergaeNoCOverpassing()));
						out.println("MLMSBLL 3:" + String.format("%.2f", (double)myMLMSBLL_3server.Time()) + " " 
								+ String.format("%.2f", myMLMSBLL_3server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMSBLL_3server.AvergaeNoCOverpassing()));
						out.println("MLMSBLL 5:" + String.format("%.2f", (double)myMLMSBLL_5server.Time()) + " " 
								+ String.format("%.2f", myMLMSBLL_5server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMSBLL_5server.AvergaeNoCOverpassing()));
						out.println("MLMSBWT 1:" + String.format("%.2f", (double)myMLMSBWT_1server.Time()) + " " 
								+ String.format("%.2f", myMLMSBWT_1server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMSBWT_1server.AvergaeNoCOverpassing()));
						out.println("MLMSBWT 3:" + String.format("%.2f", (double)myMLMSBWT_3server.Time()) + " " 
								+ String.format("%.2f", myMLMSBWT_3server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMSBWT_3server.AvergaeNoCOverpassing()));
						out.println("MLMSBWT 5:" + String.format("%.2f", (double)myMLMSBWT_5server.Time()) + " " 
								+ String.format("%.2f", myMLMSBWT_5server.AvergaeWaitTime())+ " " + 
										String.format("%.2f", myMLMSBWT_5server.AvergaeNoCOverpassing()));
						out.close();	
				}
				else{
					//If the file is empty or it is detected that its content does not match the expected format.
					String OutputFilename = InputFilename.replaceAll(".txt", "_OUT.txt");

					PrintStream out = new PrintStream(new FileOutputStream(OutputFilename));
					System.setOut(out);
					out.println("Input file does not meet the expected format or it is empty.");
					out.close();
				}
				
			  }
			  else{
				//:When a file name shows in dataFiles.txt, but the file is not in the directory.
				String OutputFilename = InputFilename.replaceAll(".txt", "_OUT.txt");

				PrintStream out = new PrintStream(new FileOutputStream(OutputFilename));
				System.setOut(out);
				out.println("Input file not found. ");
				out.close();
				  
			  }

		}
	}


	
}
