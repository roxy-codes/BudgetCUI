package core;

import java.util.*;

/*
 * This class contains the Budget Class, which encapsulates the following methods:
 * Data:
 * - A HashMap of budgets by month (monthlyBudget)
 * 
 * Methods:
 * - An empty constructor
 * - Adds a budget amount to a month (monthlyBudget)
 * - Checks if a month has a budget (monthHasBudget)
 * - Removes key/month from the budget (removeMonth)
 * 
 * @author Roxy Dao 1073633
 */

public class Budget {
	//Variables------------------------------------------------------------------------
	protected HashMap<Month, Double> monthlyBudget = new HashMap<Month, Double>();
	
	//Constructors---------------------------------------------------------------------
	public Budget() {
		
	}
	
	/*
	 * Core functions: Adds a budget amount to a month
	 * 
	 * @param month to add budget to (month)
	 * @param budget amount (amount)
	 * 
	 * @author 1073633
	 */
	public void monthlyBudget(Month month, double amount) {
		monthlyBudget.put(month, amount);
	}
	
	/*
	 * Core functions: Checks if the month has a budget
	 * 
	 * @return true if it does
	 * @return false otherwise
	 * 
	 * @author 1073633
	 */
	public boolean monthHasBudget(Month month) {
		if(monthlyBudget.containsKey(month)) {
			return true;
		}
		return false;
	}
	
    /*
     * Core functions: Removes key/month from the budget
     *
     * @param month/key to remove
     * 
     * @author 1073633
     */
    public void removeMonth(Month month){
        monthlyBudget.remove(month);
    }
	
	//Getters and Setters--------------------------------------------------------------
	public double getMonthBudget(Month month) {
		return monthlyBudget.get(month);
	}

	public HashMap<Month, Double> getMonthlyBudget() {
		return monthlyBudget;
	}

	public void setMonthlyBudget(HashMap<Month, Double> monthlyBudget) {
		this.monthlyBudget = monthlyBudget;
	}	
}