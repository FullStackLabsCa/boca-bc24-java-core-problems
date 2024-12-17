package io.reactivestax.service;

import io.reactivestax.entity.Node;
import io.reactivestax.exception.FileReadingRuntimeException;
import io.reactivestax.repo.hibernate.HibernateNodeRepo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteToFile {
    public void fileWriter() {
        HibernateNodeRepo hibernateNodeRepo = new HibernateNodeRepo();
        List<Node> data = hibernateNodeRepo.getData();

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("/Users/Dhruv.Desai/source/Student/boca-bc24-java-core-problems/src/problems/bnym-rule-set/src/main/resources/GeneratedFile/bony_ruleset.data"));

            for (Node line : data) {
                bufferedWriter.write(line.getParentId() + line.getData());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            throw new FileReadingRuntimeException("File not found!");
        }
    }
}
