package io.reactivestax.service;

import io.reactivestax.exception.FileReadingRuntimeException;
import io.reactivestax.repo.hibernate.HibernateInsertToNodeRepo;
import io.reactivestax.utilities.Properties;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static io.reactivestax.service.LineHandler.nodeList;

@Slf4j
public class BNYMFileReader {
    HibernateInsertToNodeRepo hibernateInsertToNodeRepo = new HibernateInsertToNodeRepo();

    public void readFile() {
        LineHandler lineHandler = new LineHandler();
        try (FileReader fileReader = new FileReader(Properties.getInstance().getReadToFilepath());
             Scanner scanner = new Scanner(fileReader)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineHandler.processLine(line);
            }
            if (!nodeList.isEmpty()) {
                hibernateInsertToNodeRepo.insertToNodeTable();
            }
        } catch (IOException e) {
            throw new FileReadingRuntimeException("File not found!");
        }
    }
}
