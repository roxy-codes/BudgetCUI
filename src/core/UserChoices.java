package core;

import java.io.IOException;
import java.util.*;

/*
 * This class contains the UserChoices Class, which encapsulates the following functions:
 * Data: 
 * - A Scanner and Ledger (scanner, newLedger)
 * - String arrays for choices, months and categories (function, months, categories)
 * - A final String of months (MONTHS)
 * 
 * Methods: 
 * - A constructor
 * - Classes from the Choice Interface
 * - Check if user input is a double (isDouble)
 * 
 * @author Roxy Dao 1073633
 */

public class UserChoices implements Choices {
    //Variables------------------------------------------------------------------------
    private Scanner scanner;
    private Ledger newLedger;

    private String[] functions = {"NB", "DB", "AE", "DE", "S", "Q", "L", "H"};
    private String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEPT", "NOV", "DEC"};
    private String[] categories = {"FOOD", "RENT", "UTILITIES", "MEDICAL", "FUN", "CLOTHING", "MISC"};

    //Messages------------------------------------------------------------------------
    static String MONTHS = "Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sept, Nov, Dec";
    static String MENU = "Choose what you would like to do \n\n"
    		+ "--------ADD-------- \n"
    		+ "NB: add budget \n"
    		+ "DB: delete budget \n"
    		+ "AE: add new entry \n"
    		+ "DE: delete entry \n"
    		+ "-------LOAD-------- \n"
    		+ "S: save \n"
    		+ "L: load data \n"
    		+ "--------QUIT------- \n"
    		+ "Q: quit \n";
    
    //Error Messages------------------------------------------------------------------
    static final String INVALID_MONTH = "[ERROR] Please write correct month. \n";
    static final String INVALID_NUMBER = "[ERROR] Not a number. Please enter a number.";

    //Constructor---------------------------------------------------------------------
    public UserChoices (Scanner scanner, Ledger newLedger) {
        this.scanner = scanner;
        this.newLedger = newLedger;
    }
    
    /*
     * Core functions: Print a list of choices for the user to choose from.
     * Check that the user has entered the correct input. 
     * 
     * @return userChoice
     * @author 1073633
     */
    @Override
    public String welcome() {
        System.out.println(MENU);

        while(true) {
            String userChoice = scanner.next().toUpperCase();

            if(Arrays.asList(functions).contains(userChoice)) {
                if(userChoice == "H"){
                    System.out.println(MENU);
                    continue;
                }
                return userChoice;
            }
            else {
                System.out.println("[ERROR] Type 'H' to show choices.");
            }
        }
    }
    
    /*
     * Core functions: Allow the user to add a budget amount to a month.
     * Check whether the user input it within the parameters.
     * 
     * @author 1073633
     */
    @Override
    public void addBudget() {
        System.out.println();
        System.out.println("Enter the month you want to budget for. \n" + MONTHS);
        
        while(true) {
            String userMonth = scanner.next().toUpperCase();

            if(Arrays.asList(months).contains(userMonth)) {
                System.out.println("Enter the budget amount: ");

                while(true) {
                    String userInput = scanner.next();
                    
                	if(isDouble(userInput)) {
                		double userBudgetAmount = Double.parseDouble(userInput);
                				
                		System.out.println(newLedger.addBudget(Month.valueOf(userMonth.toUpperCase()), userBudgetAmount));
                        return;
                	}
                	else {
                		System.out.println(INVALID_NUMBER);
                	}
                }
            }
            else {
                System.out.println(INVALID_MONTH + MONTHS);
            }
        }
    }

    /*
     * Core functions: Delete a budget from a month
     * Verify user input
     * 
     * @author 1073633
     */
    @Override
    public void deleteBudget() {
        System.out.println();
        System.out.println("Enter a month: \n" + MONTHS);

        while(true) {
            String userMonth = scanner.next().toUpperCase();

            if(Arrays.asList(months).contains(userMonth)) {
                newLedger.removeBudget(Month.valueOf(userMonth));
                return;
            }
            else {
                System.out.println(INVALID_MONTH + MONTHS);
            }
        }
    }
    
    /*
     * Core functions:Aadd an entry into the ledger
     * Verify user input
     * 
     * @author 1073633
     */
    @Override
    public void addEntry() {
        System.out.println();
        System.out.println("Enter a month: \n" + MONTHS);

        while(true){
            String userMonth = scanner.next().toUpperCase();

            if(Arrays.asList(months).contains(userMonth)) {
                System.out.println("Select a category: \n"
                        + "Food, Rent, Utilities, Medical, Fun, Clothing, Misc");

                while(true) {
                    String category = scanner.next().toUpperCase();

                    if (Arrays.asList(categories).contains(category)) {
                        System.out.println("Add amount: ");

                        while(true) {
                            String userInput = scanner.next();

                        	if(isDouble(userInput)) {
                        		double amount = Double.parseDouble(userInput);
                        				
                                newLedger.addExpense(Month.valueOf(userMonth), amount, Category.valueOf(category));
                                return;
                        	}
                        	else {
                        		System.out.println(INVALID_NUMBER);
                        	}
                        }
                    }
                    else {
                        System.out.println("[ERROR] Select a category: \n"
                                + "Food, Rent, Utilities, Medical, Fun, Clothing, Misc");
                    }
                }
            }
            else {
                System.out.println(INVALID_MONTH + MONTHS);
            }
        }
    }
    
    /*
     * Core function: Delete an entry from a month
     * User verification
     * 
     * @author 1073633
     */
    @Override
    public void deleteEntry() {
        System.out.println();
        System.out.println("Which month would you like to delete from?\n" + MONTHS);
        String userMonth = scanner.next().toUpperCase();

        while(true) {
            if(Arrays.asList(months).contains(userMonth)) {
                int maxValue = Ledger.printSingleEntriesMonth(newLedger, Month.valueOf(userMonth)) - 1;

                System.out.println("Enter the index you want to delete from (starting from 0 to " + maxValue + ").");

                while (true) {
                    int userIndex = scanner.nextInt();

                    if (userIndex >= 0 && userIndex <= maxValue) {
                        newLedger.removeEntry(Month.valueOf(userMonth), userIndex);
                        return;
                    }
                    else {
                        //TODO: Helper function to reprint month "H"
                        System.out.println("[ERROR] Select between 0 and " + maxValue);
                    }
                }
            }
            else {
                System.out.println(INVALID_MONTH + MONTHS);
            }
        }
    }

    /*
     * Core function: Save user input into a txt file
     * 
     * @author 1073633
     */
    @Override
    public void saveAll(String fileName) throws IOException {
        FileIO.file(newLedger, fileName);
    }
    
    /*
     * Core function: Load data from text file
     * 
     * @author 1073633
     */
    @Override
    public void loadAll(String fileName) throws IOException {    	
        newLedger.deserializeBudget(fileName);
    }
    
    /*
     * Core function: Check if user input is an Integer
     * 
     * @author 1073633
     */
    private static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
}