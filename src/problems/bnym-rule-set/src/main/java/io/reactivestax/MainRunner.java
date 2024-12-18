package io.reactivestax;

import io.reactivestax.service.BNYMFileReader;
import io.reactivestax.service.WriteToFile;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
public class MainRunner {
    public static void main(String[] args) {

        LocalDateTime startTime = LocalDateTime.now();

        BNYMFileReader bnymFileReader = new BNYMFileReader();
        bnymFileReader.readFile();

        System.out.println();

        log.info("File processing completed.");

        System.out.println();

        WriteToFile writeToFile = new WriteToFile();
        writeToFile.fileWriter();

        Duration duration = Duration.between(startTime, LocalDateTime.now());

        System.out.println("Duration to complete the reading and writing to the file: " + duration.getSeconds() + " seconds.");
    }
}