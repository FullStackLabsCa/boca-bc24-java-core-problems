package io.reactivestax.service;

import io.reactivestax.exception.FileReadingRuntimeException;
import io.reactivestax.utilities.Properties;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class bnymFileReader {
    static int counter = 1;

    static Node node;

    static Stack<Node> stack = new Stack<>();

    static Map<String, Integer> map = new HashMap<>();

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
        if (!scanner.hasNext()) {
            if (!stack.isEmpty()) {
                System.out.println(stack.size());
                Node remainingNodeInStack = stack.peek();
                String line = remainingNodeInStack.getData();
                processLine(line);
            }
            System.out.println("No element left to process");
        }
    }

    private static void processLine(String line) {
        String key = line.substring(0, 2);

        switch (key) {
            case "01" -> {
                node = new Node();

                node.setLeft(counter);
                node.setData(line);

                stack.push(node);
                System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
            }
            case "02", "05", "06" -> {
                node = new Node();

                counter++;
                node.setLeft(counter);

                counter++;
                node.setRight(counter);

                node.setData(line);
                System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
            }
            case "03" -> {
                node = new Node();
                if (stack.size() == 1) {
                    counter++;
                    node.setLeft(counter);

                    node.setData(line);

                } else {
                    while (stack.size() > 1) {
                        node = stack.pop();
                        counter++;
                        node.setRight(counter);
                        System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
                    }
                    counter++;
                    node.setLeft(counter);

                    node.setData(line);
                    node.setRight(0);
                }
                stack.push(node);
                System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
            }
            case "04" -> {
                node = new Node();
                if (stack.size() == 2) {

                    counter++;
                    node.setLeft(counter);

                    node.setData(line);

                } else {
                    while (stack.size() > 2) {
                        node = stack.pop();
                        counter++;
                        node.setRight(counter);
                        System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
                    }
                    counter++;
                    node.setLeft(counter);

                    node.setData(line);
                    node.setRight(0);
                }
                stack.push(node);
                System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
            }
            case "07" -> {
                node = new Node();
                if (stack.size() < 2) {
                    counter++;
                    node.setLeft(counter);

                    counter++;
                    node.setRight(counter);

                    node.setData(line);
                    System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
                } else if (stack.size() > 2) {
                    while (stack.size() > 2) {
                        node = stack.pop();
                        counter++;
                        node.setRight(counter);
                        System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
                    }
                    counter++;
                    node.setLeft(counter);

                    node.setData(line);
                    node.setRight(0);
                    stack.push(node);
                    System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
                }
                else if (stack.size() == 2) {
                    Node poppedElement = stack.pop();
                    counter++;
                    poppedElement.setRight(counter);
                    System.out.println(node.getLeft() + " -- " + node.getRight() + " -- " + node.getData());
                }
            }
            default -> System.out.println("Invalid key!");
        }
    }
}
