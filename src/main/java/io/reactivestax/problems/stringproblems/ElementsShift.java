package io.reactivestax.problems.stringproblems;

import java.util.Scanner;

public class ElementsShift {


    public void shiftElementsInArray(int[] elementsArray, int position) {

        while (position-- != 0) {
            int elementAtIndex0 = elementsArray[0];
            for (int i = 0; i < elementsArray.length; i++) {
                if (i == elementsArray.length - 1) {
                    elementsArray[0] = elementAtIndex0;
                } else {
                    int element = elementsArray[i + 1];
                    elementsArray[i + 1] = elementAtIndex0;
                    elementAtIndex0 = element;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter size of array - ");
        int input = scanner.nextInt();
        int[] array = new int[input];
        System.out.println("Enter elements in array");

        for (int i = 0; i < input; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.println("Enter number of positions you want to shift: ");
        int inputPosition = scanner.nextInt();

        ElementsShift elementsShift = new ElementsShift();
        elementsShift.shiftElementsInArray(array, inputPosition);

        for (int i = 0; i < input; i++) {
            System.out.println(array[i]);
        }
    }

}

