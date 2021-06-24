package core;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * This class contains the EntryList Class, which encapsulates the following methods:
 * Data: 
 * - A HashMap of entries by month (singleEntryList)
 * 
 * Methods:
 * - An empty constructor
 * - A one parameter constructor
 * - Add a SingleEntry object into the entry list by month (addSingleEntry)
 * - Get entries for all months (getEntryList)
 * - Getters and setters
 * 
 * @author Roxy Dao 1073633
 */

public class EntryList {
	//Variables------------------------------------------------------------------------
	private HashMap<Month, ArrayList<SingleEntry>> singleEntryList;
		
	//Constructors---------------------------------------------------------------------
	public EntryList() {
		singleEntryList = new HashMap<Month, ArrayList<SingleEntry>>();
	}
		
	public EntryList(SingleEntry newSingleEntry) {
		singleEntryList = new HashMap<Month, ArrayList<SingleEntry>>();
		
		addSingleEntry(newSingleEntry);
	}
		
	/*
	 * Core functions: Adds a SingleEntry object into the entry list by month
	 * 
	 * @param newSingleEntry
	 * 
	 * @author 1073633
	 */
	public void addSingleEntry(SingleEntry newSingleEntry) {
		ArrayList<SingleEntry> tempList = new ArrayList<SingleEntry>();
		
		if(singleEntryList.containsKey(newSingleEntry.getMonth())) {
			tempList = singleEntryList.get(newSingleEntry.getMonth());
		}
			
		tempList.add(newSingleEntry);
		singleEntryList.put(newSingleEntry.getMonth(), tempList);
	}
	
	/*
	 * Core functions: Get entries for all months.
	 * 
	 * @return all entries (allList)
	 * 
	 * @author 1073633
	 */
	public ArrayList<SingleEntry> getEntryList(){
		ArrayList<SingleEntry> allList = new ArrayList<SingleEntry>();
		
		for(Month month : singleEntryList.keySet()) {
			ArrayList<SingleEntry> tempList = singleEntryList.get(month);
			allList.addAll(tempList);
		}
			
		return allList; 
	}

	//Getters and Setters--------------------------------------------------------------
	public HashMap<Month, ArrayList<SingleEntry>> getSingleEntryList() {
		return singleEntryList;
	}
	
	public ArrayList<SingleEntry> getSingleEntry(Month month){
		return singleEntryList.get(month);
	}
}