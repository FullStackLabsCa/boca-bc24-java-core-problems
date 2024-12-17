package io.reactivestax;

import io.reactivestax.repo.hibernate.HibernateNodeRepo;
import io.reactivestax.service.BNYMFileReader;
import io.reactivestax.service.FileWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainRunner {
    public static void main(String[] args) {
        BNYMFileReader bnymFileReader = new BNYMFileReader();
        bnymFileReader.readFile();
        log.info("File processing completed.");

        FileWriter fileWriter = new FileWriter();
        fileWriter.writeToFile();
    }
}