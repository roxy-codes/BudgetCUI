package core;

import java.io.*;

/*
 * This class contains the FileIO Class, which encapsulates the following methods:
 * Methods:
 * - A static method for writing into a txt file (file)
 * 
 * @author Roxy Dao 1073633
 */

public class FileIO {
	/*
	 * Core functions: Creates a txt file to write information about the ledger. 
	 * 
	 * @param ledger
	 * @param fileName
	 * 
	 * @author 1073633
	 */
    public static void file(Ledger ledger, String fileName) throws IOException {
        try {
            FileWriter text = new FileWriter(fileName);
            String budget = ledger.serializeBudget();
            text.write(budget);
            text.close();
        }
        catch(IOException e) {
            System.out.println("Can't write to file!");
        }
    }
}