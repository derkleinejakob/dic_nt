package model;

import java.util.List;

/**
 * This class is responsible for persistent data management of the game statistics. This could be either done with an
 * concrete implementation, that manages a csv file (as currently planned) or - in the future -
 * with another concrete implementation of this interface, which manages a database
 */
public interface FileManagement {
    /**
     * Returns a list of usage entries, retrieved from a csv file.
     * If there is no CSV file or an i/o problem, return an empty list
     */
    List<Item> retrieve();

    /**
     * Appends one UsageEntry to the CSV file by adding a new line
     */
    boolean save(Item usageEntry);

}
