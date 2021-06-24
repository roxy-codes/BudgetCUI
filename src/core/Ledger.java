package core;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/*
 * This class contains the Ledger Class, which encapsulates the following functions:
 * Data: 
 * - List of Entries (EntryList)
 * - A class of budgets for each month (budget)
 * 
 * Methods: 
 * - An empty constructor
 * - Add a new budget for a month (addBudget)
 * - Remove budget from a month (removeBudget)
 * - Add an entry to the EntryList and minus the amount from the budget (addExpense)
 * - Removes an entry from the EntryList and add amount back into budget (removeExpense)
 * 
 * Helper Functions:
 * - Get data to print into txt file (serializeBudget)
 * - Print single entries by month so that the user can choose which one to delete (printSingleEntriesMonth)
 * 
 * @author Roxy Dao 1073633
 */

public class Ledger extends Budget {
	//Static Error Messages------------------------------------------------------------
	static final String MONTH_HAS_BUDGET = "[ERROR] This month already has a budget.";
	static final String TOO_EXPENSIVE = "[ERROR] The amount is greater than the current budget";
	static final String NO_BUDGET = " doesn't have a budget.";

	//Variables------------------------------------------------------------------------
	public EntryList entries = new EntryList();
	private Budget budget = new Budget();
	public DecimalFormat df = new DecimalFormat("#.00");

	//Constructors---------------------------------------------------------------------
	public Ledger() {
		super();
	}

	/*
	 * Core functions: Add a budget to a month if that month doesn't have a budget.
	 *
	 * @return MONTH_HAS_A_BUDGET if the month has a budget
	 * @return (month) will have a budget of (amount)
	 * 
	 * @author 1073633
	 */
	public String addBudget(Month month, double amount) {
		df.setMinimumIntegerDigits(2);

		if (budget.monthHasBudget(month)) {
			return MONTH_HAS_BUDGET;
		}

		budget.monthlyBudget.put(month, amount);
		return String.valueOf(month) + " will have a budget of " + df.format(amount);
	}

	/*
	 * Core functions: Removes a budget from a month
	 * 
	 * @author 1073633
	 */
	public void removeBudget(Month month) {
        if (budget.monthHasBudget(month)) {
            budget.removeMonth(month);

            System.out.println(String.valueOf(month) + " now has no budget.");
            System.out.println();
            return;
        }
        
        System.out.println(String.valueOf(month) + NO_BUDGET);
        System.out.println();
        return;
    }
	
	/*
	 * Core function: Add an entry to the EntryList and minus the amount from the budget
	 *
	 * @return true if the amount is less than the budget
	 * @return false if the amount is greater than budget
	 * 
	 * @author 1073633
	 */
	public boolean addExpense(Month month, double amount, Category category) {
		df.setMinimumIntegerDigits(2);

		if (budget.monthHasBudget(month)) {
			double monthBudget = budget.getMonthBudget(month);

			if (!(amount > monthBudget)) {
				SingleEntry inputEntry = new SingleEntry(month, category, amount);
				double newBudget = monthBudget - amount;

				budget.monthlyBudget(month, newBudget);
				entries.addSingleEntry(inputEntry);

				System.out.println(String.valueOf(month) + "'s remaining budget is: " + df.format(newBudget));
				return true;
			}

			System.out.println(TOO_EXPENSIVE
					+ "Budget: " + df.format(monthBudget) + "\n"
					+ "Amount: " + df.format(amount));
			return false;
		}

		System.out.println(String.valueOf(month) + NO_BUDGET);
		return false;
	}

	/*
	 * Core functions: Removes an entry from the EntryList and add amount back into budget.
	 *
	 * @return true if the month has a budget
	 * @return false if the month doesn't have a budget
	 * 
	 * @author 1073633
	 */
	public boolean removeEntry(Month month, int index) {
		df.setMinimumIntegerDigits(2);

		if (budget.monthHasBudget(month)) {
			double valueToRemove = entries.getSingleEntryList().get(month).get(index).getAmount();

			// Remove entry.
			entries.getSingleEntryList().get(month).remove(index);

			// Add to budget.
			double newBudget = budget.getMonthlyBudget().get(month) + valueToRemove;

			budget.monthlyBudget(month, newBudget);

			System.out.println(String.valueOf(month) + "'s remaining budget is: " + df.format(newBudget));
			return true;
		}

		System.out.println(String.valueOf(month) + NO_BUDGET);
		return false;
	}
	
	/*
	 * Core function: Get data to print into text file
	 * 
	 * @return monthly budget (result1) and expense entries (result2)
	 * 
	 * @author 1073633
	 */
    public String serializeBudget() {
        String result1 = "==Monthly Budget==";
        String result2 = "==Expenses==";
        ArrayList<SingleEntry> myEntries = entries.getEntryList();

        for (Map.Entry<Month, Double> entry : budget.monthlyBudget.entrySet()) {
            result1 += "\n" + entry.getKey() + " : " + entry.getValue();

        }

        for (int counter = 0; counter < myEntries.size(); counter++) {
            SingleEntry sentry = myEntries.get(counter);
            result2 += "\nExpense:"
                    + "\nMonth: " + sentry.getMonth()
                    + "\nAmount: " + String.valueOf(sentry.getAmount())
                    + "\nCategory: " + String.valueOf(sentry.getCategory());
        }

        return result1 + "\n" + result2;
    }
	
	/*
	 * Core function: Get data from the text file
	 * 
	 * @author 1073633
	 */
    public void deserializeBudget(String fileName) {
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                
                if (nextLine.equals("==Monthly Budget==")) {
                    nextLine= myReader.nextLine();
                    
                    while (!nextLine.equals("==Expenses==")) {
                        String[] budgetSplit = nextLine.split(" : ");
                        addBudget(Month.valueOf(budgetSplit[0]), Double.parseDouble(budgetSplit[1]));
                        nextLine= myReader.nextLine();
                    }
                }
                
                if (nextLine.equals("==Expenses==")) {
                    while (myReader.hasNextLine()) {
                        nextLine = myReader.nextLine();
                        
                        if (nextLine.equals("Expense:")) {
                            String[] monthSplit = myReader.nextLine().split(": ");
                            String[] amountSplit = myReader.nextLine().split(": ");
                            String[] categorySplit = myReader.nextLine().split(": ");
                            addExpense(Month.valueOf(monthSplit[1]), Double.parseDouble(amountSplit[1]), Category.valueOf(categorySplit[1]));
                        }
                    }
                }
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("Budget.txt not found.");
            e.printStackTrace();
        }
    }
	
	/*
	 * Print single entries by month so that the user can choose which one to delete
	 * 
	 * @param ledger
	 * @param month
	 * @return max index of array (index)
	 * 
	 * @author 1073633
	 */
	public static int printSingleEntriesMonth(Ledger ledger, Month month) {
		ArrayList<SingleEntry> entryList = ledger.entries.getSingleEntry(month);
		int index = 0;

		for (SingleEntry printEntries : entryList) {
			System.out.println(index + ". Month: " + printEntries.getMonth().toString() + ", category: " + printEntries.getCategory() +
					", amount: " + printEntries.getAmount());
			index++;
		}
		return index;
	}
}