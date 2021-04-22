package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String data = getInputFromAFile();
        int numberOfCorrectParenthesis = getNumberOfCorrectParenthesis(data);
        writeDataToAFile(numberOfCorrectParenthesis);

    }

    public static String getInputFromAFile() {
        StringBuilder data = new StringBuilder();
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    public static void writeDataToAFile(int numberOfCorrectParenthesis) {
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            myWriter.write(String.valueOf(numberOfCorrectParenthesis));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static int getNumberOfCorrectParenthesis(String parenthesis) {
        if (parenthesis.length() < 2 || !parenthesis.contains("(") || !parenthesis.contains(")")) {
            return 0;
        }
        int numberOfCorrectParenthesis = 0;
        int leftPointer = 0;
        while (leftPointer < parenthesis.length()) {
            if (parenthesis.charAt(leftPointer) != '(') {
                leftPointer++;
                continue;
            }
            int rightPointer = parenthesis.length() - 1;
            while (true) {
                if (parenthesis.charAt(rightPointer) == ')') {
                    if (rightPointer - leftPointer > 2 && isParenthesisExistent(leftPointer, rightPointer, parenthesis)) {
                        numberOfCorrectParenthesis++;
                    } else if (rightPointer - leftPointer == 1) {
                        numberOfCorrectParenthesis++;
                    }
                }
                rightPointer--;
                if (rightPointer == leftPointer) {
                    leftPointer++;
                    break;
                }
            }
        }
        return numberOfCorrectParenthesis;
    }

    private static boolean isParenthesisExistent(int leftParenthesis, int rightParenthesis, String parenthesis) {
        if (!parenthesis.contains("(") && !parenthesis.contains(")")) {
            return false;
        }
        int firstPointer = leftParenthesis + 1;
        int secondPointer = rightParenthesis - 1;
        while (firstPointer < secondPointer) {
            while (parenthesis.charAt(firstPointer) == '(') {
                if (parenthesis.charAt(secondPointer) == ')') {
                    return true;
                }
                secondPointer--;
                if (firstPointer == secondPointer) {
                    break;
                }
            }
            firstPointer++;
            secondPointer = rightParenthesis - 1;
        }
        return false;
    }
}