package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CSVFileManagement implements FileManagement {

    /**
     * reads a list of ist of items from a textfile
     * @param dataset URL of a textfile containing a list of information on different emitters
     * @return an Arraylist of Items
     */
    @Override
    public List<Item> retrieve(String dataset) {
        try {
            return Files.lines(Path.of(dataset)).map(this::parseEntryFromLine).filter(Objects::nonNull).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error loading entries: " + e);
            return new ArrayList<>();
        }
    }

    /**
     * @param line information on a single emitter (name, description, emissions and imagepath), seperated by comma
     * @return Item containing the given information
     */
    private Item parseEntryFromLine(String line) {
        String[] tokens = line.split(","); //the attributes are separated with commas
        if (tokens.length < 3) {
            System.err.println("Not enough arguments for entry '" + line + "'.");
            return null;
        }
        try {
            return new Item(tokens[0], tokens[1], Double.parseDouble(tokens[2]), tokens[3]);
        } catch (Exception e) {
            System.err.println("Skipping invalid entry '" + line + "': " + e);
            return null;
        }
    }
}
