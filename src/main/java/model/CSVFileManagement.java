package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CSVFileManagement implements FileManagement {
    private static final String FILENAME = "dataset.csv";

    @Override
    public List<Item> retrieve() {
        try {
            return Files.lines(Path.of(FILENAME)).map(this::parseEntryFromLine).filter(Objects::nonNull).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error loading entries: " + e);
            return new ArrayList<>();
        }
    }

    private Item parseEntryFromLine(String line) {
        String[] tokens = line.split(","); //the attributes are separated with commas
        if (tokens.length < 3) {
            System.err.println("Not enough arguments for entry '" + line + "'.");
            return null;
        }
        try {
//            int score = Integer.parseInt(tokens[0]);
//            LocalDateTime startTime = LocalDateTime.parse(tokens[1], FORMATTER);
//            LocalDateTime endTime = LocalDateTime.parse(tokens[2], FORMATTER);
            return new Item("", "", "");
        } catch (NumberFormatException | DateTimeParseException e) {
            System.err.println("Skipping invalid entry '" + line + "': " + e);
            return null;
        }
    }

    /**
     * Converts an entry object to a String by separating its attributes with commas.
     */
    private String convertEntryToString(Item entry) {
        return "";
//        return (String.valueOf(entry.score()) + ',' +
//                entry.startTime().format(FORMATTER) + ',' +
//                entry.endTime().format(FORMATTER) + '\n');
    }

    public boolean save(Item entry) {
        try {
            Files.writeString(Path.of(FILENAME), convertEntryToString(entry), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving entry: " + e);
            return false;
        }
    }
}
