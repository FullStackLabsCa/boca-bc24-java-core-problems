package io.reactivestax.service;

import io.reactivestax.exception.FileReadingRuntimeException;
import io.reactivestax.utilities.Properties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

public class bnymFileReader {
    static int counter = 1;

    static Stack<Node> stack = new Stack<>();

    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader(Properties.getInstance().getFilepath()));
        } catch (FileNotFoundException e) {
            throw new FileReadingRuntimeException("File not found!");
        }

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
        System.out.println("Final stack size: " + stack.size());
        System.out.println("Processing completed.");
    }

    private static void processLine(String line) {
        String key = line.substring(0, 2);

        switch (key) {
            case "01" -> {
                Node node = new Node();

                node.setLeft(counter);
                node.setData(line);

                stack.push(node);
                System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
            }
            case "02", "05", "06" -> {
                Node node = new Node();

                counter++;
                node.setLeft(counter);

                counter++;
                node.setRight(counter);

                node.setData(line);
                System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
            }
            case "03" -> {
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
            case "04" -> {
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
            case "07" -> {
                while (stack.size() > 2) {
                    Node node = stack.pop();
                    counter++;
                    node.setRight(counter);
                    System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
                }
                Node node = new Node();
                counter++;
                node.setLeft(counter);
                counter++;
                node.setRight(counter);
                node.setData(line);
                System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
            }
            default -> System.out.println("Invalid key!");
        }
    }
}
