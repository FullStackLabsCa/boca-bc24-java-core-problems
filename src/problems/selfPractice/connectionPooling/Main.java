package problems.selfPractice.connectionPooling;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserDAO userDAO  = new UserDAO();

    public static void main(String[] args) {
boolean running = true;

        while(running){
            System.out.println("Select Option:");
            System.out.println("1. Add a new user");
            System.out.println("2. List all users");
            System.out.println("3. Add a new order");
            System.out.println("4. List all orders");
            System.out.println("5. Exit");

            int choice = getValidIntegerInput("Please enter a number (1-5): ");

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    listUsers();
                    break;
                case 3:
                    addOrder();
                    break;
                case 4:
                    listOrders();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    private static void addUser() {
        String name = getValidStringInput("Enter the user's name: ");
        String email = getValidStringInput("Enter the user's email: ");
        userDAO.addUser(name, email);
        System.out.println("User added successfully.");
    }

    private static void listUsers() {
        List<User> users = userDAO.userLists();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("Listing users:");
            System.out.println(String.format("%-5s %-20s %-30s", "ID", "Name", "Email"));
            System.out.println("--------------------------------------------------------");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    private static void addOrder() {
        int userId = getValidIntegerInput("Enter the user ID for the order: ");
        String date = getValidStringInput("Enter the order date (YYYY-MM-DD HH:MM:SS): ");
        double amount = getValidDoubleInput("Enter the order amount: ");
        OrderDAO.addOrder(userId, date, amount);
        System.out.println("Order added successfully.");
    }

    private static void listOrders() {
        List<Order> orders = OrderDAO.listOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            System.out.println("Listing orders:");
            System.out.println(String.format("%-10s %-5s %-20s %-10s", "Order ID", "User ID", "Order Date", "Amount"));
            System.out.println("-------------------------------------------------------------------");
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }


    // Validation

    private static int getValidIntegerInput(String prompt) {
        int input = -1;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                if (input < 1 || input > 5) {
                    throw new InputMismatchException("Input out of range");
                }
                scanner.nextLine(); // Clear the newline
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number (1-5).");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        return input;
    }

    private static double getValidDoubleInput(String prompt) {
        double input = -1;
        while (true) {
            try {
                System.out.print(prompt);
                input = scanner.nextDouble();
                scanner.nextLine(); // Clear the newline
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        return input;
    }

    private static String getValidStringInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println("Input cannot be empty. Please try again.");
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }

}
