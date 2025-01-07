package com.arhan.fileParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileParserNumbersBySpace {


    String filePath;

    FileParserNumbersBySpace(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses the file and populates the given 2D ArrayList with numbers.
     * This method assumes a file input that contains numbers only on each line
     * and that there are no whitespace lines in between
     *
     * @param input The 2D ArrayList to populate.
     * @return The populated 2D ArrayList.
     */
    ArrayList<ArrayList<Long>> parseFile(ArrayList<ArrayList<Long>> input) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            int rowIndex = 0;

            while ((line = reader.readLine()) != null) {

                // Uses regex expression (//s is for space and + for multiple) to split the line into parts
                String[] parts = line.split("\\s+");

                // Ensure the row exists in the 2D ArrayList and create if necessary
                if (input.size() <= rowIndex) {
                    // Adds new row
                    input.add(new ArrayList<>());
                }

                for (int i = 0; i < parts.length; i++) {
                    try {
                        input.get(rowIndex).add(Long.parseLong(parts[i]));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number skipped: " + parts[i]);
                    }
                }

                rowIndex++;

            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        return input;
    }
}

