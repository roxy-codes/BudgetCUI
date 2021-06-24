package core;

import java.io.IOException;

/*
 * This class contains the Choice interface, which contain the following methods:
 * - welcome message (welcome)
 * - addBudget
 * - deleteBudget
 * - addEntry
 * - deleteEntry
 * - saveAll
 *
 * @author Roxy Dao 1073633
 */

public interface Choices {
    public String welcome();

    public void addBudget();
    public void deleteBudget();

    public void addEntry();
    public void deleteEntry();

    public void saveAll(String fileName) throws IOException;
    public void loadAll(String fileName) throws IOException;
}
