package io.reactivestax.service;

import io.reactivestax.repo.hibernate.HibernateNodeRepo;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileWriter {
    public void writeToFile() {
        HibernateNodeRepo hibernateNodeRepo = new HibernateNodeRepo();
        List data = hibernateNodeRepo.getData();

        File myObj = new File("/Users/Dhruv.Desai/source/Student/boca-bc24-java-core-problems/src/problems/bnym-rule-set/src/main/resources/GeneratedFile/newFile.data");
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
