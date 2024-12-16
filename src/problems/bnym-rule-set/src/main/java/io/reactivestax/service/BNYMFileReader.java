package io.reactivestax.service;

import io.reactivestax.exception.FileReadingRuntimeException;
import io.reactivestax.utilities.Properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static io.reactivestax.service.LineHandler.processLine;

public class BNYMFileReader {
    public void readFile() {
        try (FileReader fileReader = new FileReader(Properties.getInstance().getFilepath());
             Scanner scanner = new Scanner(fileReader)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                processLine(line);
            }
            System.out.println("Processing completed.");
        } catch (IOException e) {
            throw new FileReadingRuntimeException("File not found!");
        }
    }
}
