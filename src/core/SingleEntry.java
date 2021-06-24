package core;

/*
 * This class contains the SingleEntry Class, which encapsulates the following methods:
 * Data:
 * - Expense amount (double)
 * - Category enumeration (category)
 * - Month enumeration (month)
 * 
 * Methods:
 * - An empty constructor
 * - A three parameter constructor
 * - Setter and getter methods for the variables
 * - Compares two SingleEntry objects by month (compareTo)
 * 
 * @author Roxy Dao 1073633
 */

public class SingleEntry implements Comparable<SingleEntry> {
	//Variables------------------------------------------------------------------------
	private double amount;
	private Category category;
	private Month month;
	
	//Constructors---------------------------------------------------------------------
		public SingleEntry() {
			this.month = null;
			this.setCategory(null);
			this.amount = 0;
		}
		
		public SingleEntry(Month month, Category category, double amount) {
			this.setMonth(month);
			this.setCategory(category);
			this.amount = amount;
		}
		
		//Getters and setters--------------------------------------------------------------
		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}
		
		public Month getMonth() {
			return month;
		}

		public void setMonth(Month month) {
			this.month = month;
		}
		
		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}
		
		@Override
		public int compareTo(SingleEntry o) {
			return month.compareTo(o.month);
		}
}