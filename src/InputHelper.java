import java.util.Scanner;

/**
 * Utility class for validating user input in the console.
 * Prevents crashes from invalid or empty input.
 */
public class InputHelper {
    /**
     * Prompts for and returns a positive integer.
     */
    public static int getPositiveIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (value > 0) return value;
                else System.out.println("Please enter a positive integer.");
            } else {
                System.out.println("Please enter a valid integer.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }

    /**
     * Prompts for and returns a positive double.
     */
    public static double getPositiveDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                if (value > 0) return value;
                else System.out.println("Please enter a positive number.");
            } else {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }

    /**
     * Prompts for and returns a non-empty, non-numeric string.
     */
    public static String getNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty() && !input.matches("\\d+")) {
                return input;
            } else {
                System.out.println("Please enter a valid (non-empty, non-numeric) text.");
            }
        }
    }
}

