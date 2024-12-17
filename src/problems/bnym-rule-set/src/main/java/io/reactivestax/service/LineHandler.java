package io.reactivestax.service;

import io.reactivestax.model.Node;
import io.reactivestax.repo.hibernate.HibernateNodeRepo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Slf4j
public class LineHandler implements LineHandlerService {
    int counter = 0;

    Stack<Node> stack = new Stack<>();

    public static List<Node> nodeList = new ArrayList<>();

    HibernateNodeRepo hibernateNodeRepo = new HibernateNodeRepo();

    public void processLine(String line) {
        String key = line.substring(0, 2);

        switch (key) {
            case "01" -> ruleSetIdentifier(line);
            case "02", "05", "06" -> accountNumberSetValuesConcentrationLimit(line);
            case "03" -> eligibilityGroup(line);
            case "04" -> eligibilityRule(line);
            case "07" -> margins(line);
            default -> log.warn("Invalid key!");
        }
    }

    @Override
    public void ruleSetIdentifier(String line) {
        while (!stack.isEmpty()) {
            Node remainingNodeInStack = stack.pop();
            counter++;
            remainingNodeInStack.setRight(counter);

            counter++;

            log.info("{} -- {} -- {}", remainingNodeInStack.getLeft(), remainingNodeInStack.getRight(), remainingNodeInStack.getData());
        }

        hibernateNodeRepo.insertToNodeTable();

        Node node = new Node();

        node.setLeft(counter);
        node.setData(line);

        stack.push(node);

        nodeList.add(node);

        log.info("{} -- {} -- {}", node.getLeft(), node.getRight(), node.getData());
    }

    @Override
    public void accountNumberSetValuesConcentrationLimit(String line) {
        Node node = new Node();

        counter++;
        node.setLeft(counter);

        counter++;
        node.setRight(counter);

        node.setData(line);

        nodeList.add(node);

        log.info("{} -- {} -- {}", node.getLeft(), node.getRight(), node.getData());
    }

    @Override
    public void eligibilityGroup(String line) {
        while (stack.size() > 1) {
            Node node = stack.pop();
            counter++;
            node.setRight(counter);

            log.info("{} -- {} -- {}", node.getLeft(), node.getRight(), node.getData());
        }
        Node node = new Node();
        counter++;
        node.setLeft(counter);
        node.setData(line);
        stack.push(node);

        nodeList.add(node);

        log.info("{} -- {} -- {}", node.getLeft(), node.getRight(), node.getData());
    }

    @Override
    public void eligibilityRule(String line) {
        while (stack.size() > 2) {
            Node node = stack.pop();
            counter++;
            node.setRight(counter);

            log.info("{} -- {} -- {}", node.getLeft(), node.getRight(), node.getData());
        }
        Node node = new Node();
        counter++;
        node.setLeft(counter);
        node.setData(line);
        stack.push(node);

        nodeList.add(node);

        log.info("{} -- {} -- {}", node.getLeft(), node.getRight(), node.getData());
    }

    @Override
    public void margins(String line) {
        while (stack.size() > 2) {
            Node node = stack.pop();
            counter++;
            node.setRight(counter);

            log.info("{} -- {} -- {}", node.getLeft(), node.getRight(), node.getData());
        }
        accountNumberSetValuesConcentrationLimit(line);
    }
}