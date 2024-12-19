package io.reactivestax.service;

import io.reactivestax.model.Node;
import io.reactivestax.repo.hibernate.HibernateInsertToNodeRepo;
import io.reactivestax.types.RuleSetEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Slf4j
public class LineHandler implements LineHandlerService {
    int counter = 0;

    Stack<Node> stack = new Stack<>();

    public static List<Node> nodeList = new ArrayList<>();

    HibernateInsertToNodeRepo hibernateInsertToNodeRepo = new HibernateInsertToNodeRepo();

    public void processLine(String line) {
        String key = line.substring(0, 2);
        String value = line.substring(2);

        RuleSetEnum ruleSetEnum = RuleSetEnum.fromCode(key);

        switch (ruleSetEnum) {
            case RULE_01 -> ruleSetIdentifier(key, value);
            case RULE_02, RULE_05, RULE_06 -> accountNumberSetValuesConcentrationLimit(key, value);
            case RULE_03 -> eligibilityGroup(key, value);
            case RULE_04 -> eligibilityRule(key, value);
            case RULE_07 -> margins(key, value);
            default -> log.warn("Invalid key!");
        }
    }

    @Override
    public void ruleSetIdentifier(String key, String value) {
        while (!stack.isEmpty()) {
            Node remainingNodeInStack = stack.pop();
            remainingNodeInStack.setRight(++counter);

            log.info("{} -- {} -- {}", remainingNodeInStack.getLeft(), remainingNodeInStack.getRight(), remainingNodeInStack.getData());
        }

        hibernateInsertToNodeRepo.insertToNodeTable();

        Node node = new Node();

        node.setLeft(counter);
        node.setData(value);
        node.setParentId(key);

        stack.push(node);

        nodeList.add(node);

        log.info("{} -- {} -- {}", node.getLeft(), node.getRight(), node.getData());
    }

    @Override
    public void accountNumberSetValuesConcentrationLimit(String key, String value) {
        Node node = new Node();

        node.setLeft(++counter);

        node.setRight(++counter);

        node.setData(value);

        node.setParentId(key);

        nodeList.add(node);

        log.info("{} -- {} -- {}", node.getLeft(), node.getRight(), node.getData());
    }

    @Override
    public void eligibilityGroup(String key, String value) {
        while (stack.size() > 1) {
            Node node = stack.pop();
            node.setRight(++counter);

            log.info("{} -- {} -- {}", node.getLeft(), node.getRight(), node.getData());
        }
        Node node = new Node();
        node.setLeft(++counter);
        node.setData(value);
        node.setParentId(key);
        stack.push(node);

        nodeList.add(node);

        log.info("{} -- {} -- {}", node.getLeft(), node.getRight(), node.getData());
    }

    @Override
    public void eligibilityRule(String key, String value) {
        while (stack.size() > 2) {
            Node node = stack.pop();
            node.setRight(++counter);

            log.info("{} -- {} -- {}", node.getLeft(), node.getRight(), node.getData());
        }
        Node node = new Node();
        node.setLeft(++counter);
        node.setData(value);
        node.setParentId(key);
        stack.push(node);

        nodeList.add(node);

        log.info("{} -- {} -- {}", node.getLeft(), node.getRight(), node.getData());
    }

    @Override
    public void margins(String key, String value) {
        while (stack.size() > 2) {
            Node node = stack.pop();
            node.setRight(++counter);

            log.info("{} -- {} -- {}", node.getLeft(), node.getRight(), node.getData());
        }
        accountNumberSetValuesConcentrationLimit(key, value);
    }
}