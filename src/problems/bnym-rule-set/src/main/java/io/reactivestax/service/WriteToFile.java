package io.reactivestax.service;

import io.reactivestax.entity.Node;
import io.reactivestax.exception.FileReadingRuntimeException;
import io.reactivestax.repo.hibernate.HibernateReadFromNodeRepo;
import io.reactivestax.utilities.Properties;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteToFile {
    public void fileWriter() {
        HibernateReadFromNodeRepo hibernateReadFromNodeRepo = new HibernateReadFromNodeRepo();
        List<Node> data = hibernateReadFromNodeRepo.getData();

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(Properties.getInstance().getWriteToFilepath()));

            for (Node line : data) {
                bufferedWriter.write(line.getParentId() + line.getData());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            System.out.println("Successfully wrote to the file.");
            System.out.println();
        } catch (IOException e) {
            throw new FileReadingRuntimeException("File not found!");
        }
    }
}
