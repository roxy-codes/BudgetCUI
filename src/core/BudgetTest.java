package core;

import java.io.IOException;
import java.util.Scanner;

/*
 * Budget Test Class which contains the main method.
 * 
 * @author Roxy Dao 1073633
 */
public class BudgetTest {
    public static Scanner scanner = new Scanner(System.in);
    public static Ledger newLedger = new Ledger();

    public static void main(String[] args) throws IOException {
        boolean quit = false;
        UserChoices userChoices = new UserChoices(scanner, newLedger);
        String response;

        //switch-case for user choices.
        while(!quit) {
            response = userChoices.welcome();

            switch(response) {
                case "NB":
                    userChoices.addBudget();
                    break;
                case "DB":
                    userChoices.deleteBudget();
                    break;
                case "AE":
                    userChoices.addEntry();
                    break;
                case "DE":
                    userChoices.deleteEntry();
                    break;
                case "S":
                    userChoices.saveAll("budget.txt");
                    break;
                case "L":
                    userChoices.loadAll("budget.txt");
                    break;
                case "Q":
                    quit = true;
                    break;
            }
        }
    }
}
