package io.reactivestax.service;

import io.reactivestax.exception.FileReadingRuntimeException;
import io.reactivestax.utilities.Properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class BNYMFileReader {
    static int counter = 1;

    static Stack<Node> stack = new Stack<>();

    public static void main(String[] args) {
        try (FileReader fileReader = new FileReader(Properties.getInstance().getFilepath());
             Scanner scanner = new Scanner(fileReader)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                processLine(line);
            }
            while (!stack.isEmpty()) {
                Node remainingNodeInStack = stack.pop();
                counter++;
                remainingNodeInStack.setRight(counter);
                System.out.println(remainingNodeInStack.getLeft() + " -- " + remainingNodeInStack.getRight() + " -- " + remainingNodeInStack.getData());
            }
            System.out.println("Processing completed.");
        } catch (IOException e) {
            throw new FileReadingRuntimeException("File not found!");
        }
    }

    private static void processLine(String line) {
        String key = line.substring(0, 2);

        switch (key) {
            case "01" -> handle01(line);
            case "02", "05", "06" -> handle020506(line);
            case "03" -> handle03(line);
            case "04" -> handle04(line);
            case "07" -> handle07(line);
            default -> System.out.println("Invalid key!");
        }
    }

    private static void handle01(String line) {
        Node node = new Node();

        node.setLeft(counter);
        node.setData(line);

        stack.push(node);
        System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
    }

    private static void handle020506(String line) {
        Node node = new Node();

        counter++;
        node.setLeft(counter);

        counter++;
        node.setRight(counter);

        node.setData(line);
        System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
    }

    private static void handle03(String line) {
        while (stack.size() > 1) {
            Node node = stack.pop();
            counter++;
            node.setRight(counter);
            System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
        }
        Node node = new Node();
        counter++;
        node.setLeft(counter);
        node.setData(line);
        stack.push(node);
        System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
    }

    private static void handle04(String line) {
        while (stack.size() > 2) {
            Node node = stack.pop();
            counter++;
            node.setRight(counter);
            System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
        }
        Node node = new Node();
        counter++;
        node.setLeft(counter);
        node.setData(line);
        stack.push(node);
        System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
    }

    private static void handle07(String line) {
        while (stack.size() > 2) {
            Node node = stack.pop();
            counter++;
            node.setRight(counter);
            System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
        }
        handle020506(line);
    }
}