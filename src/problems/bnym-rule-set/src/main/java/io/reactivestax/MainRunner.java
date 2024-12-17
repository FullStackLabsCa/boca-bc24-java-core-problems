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
        log.info("File processing completed.");

        WriteToFile writeToFile = new WriteToFile();
        writeToFile.fileWriter();

        Duration duration = Duration.between(startTime, LocalDateTime.now());

        System.out.println("Duration: " + duration.getNano());
    }
}