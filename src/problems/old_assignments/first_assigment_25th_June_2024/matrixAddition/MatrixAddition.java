package problems.old_assignments.first_assigment_25th_June_2024.matrixAddition;

import java.util.Scanner;

    // Custom Exception
    class MatrixSizeMismatchException extends Exception {
        public MatrixSizeMismatchException(String message) {
            super(message);
        }
    }

    public class MatrixAddition {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Step 1: Input the size of the matrices
            System.out.print("Enter the number of rows for the first matrix: ");
            int rows1 = scanner.nextInt();
            System.out.print("Enter the number of columns for the first matrix: ");
            int cols1 = scanner.nextInt();

            System.out.print("Enter the number of rows for the second matrix: ");
            int rows2 = scanner.nextInt();
            System.out.print("Enter the number of columns for the second matrix: ");
            int cols2 = scanner.nextInt();

            try {
                // Step 1: Input the matrices
                if (rows1 != rows2 || cols1 != cols2) {
                    throw new MatrixSizeMismatchException("Matrices are not the same size.");
                }

                int[][] matrix1 = inputMatrix(scanner, rows1, cols1, "first");
                int[][] matrix2 = inputMatrix(scanner, rows1, cols1, "second");

                // Step 2: Add the matrices
                int[][] resultMatrix = addMatrices(matrix1, matrix2);

                // Step 3: Output the resulting matrix
                System.out.println("The resulting matrix is:");
                printMatrix(resultMatrix);
            } catch (MatrixSizeMismatchException e) {
                System.out.println(e.getMessage());
            }

            scanner.close();
        }

        // Method to input Abc matrix
        public static int[][] inputMatrix(Scanner scanner, int rows, int cols, String matrixName) {
            int[][] matrix = new int[rows][cols];
            System.out.println("Enter the elements of the " + matrixName + " matrix :");
            System.out.println("Enter the " + cols + " element in each rows. ");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }
            return matrix;
        }

        // Method to add two matrices
        public static int[][] addMatrices(int[][] matrix1, int[][] matrix2) {
            int rows = matrix1.length;
            int cols = matrix1[0].length;
            int[][] resultMatrix = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    resultMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }
            return resultMatrix;
        }

        // Method to print Abc matrix
        public static void printMatrix(int[][] matrix) {
            for (int[] row : matrix) {
                for (int elem : row) {
                    System.out.print(elem + " ");
                }
                System.out.println();
            }
        }
    }

