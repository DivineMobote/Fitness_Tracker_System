/**
 * Fitness Tracker Application
 * Author: Divine Mobote
 * Student ID: 801239652
 * Course: ITCS-3112-001
 * Date: 2025-24-04
*/

import java.util.Scanner;

/**
 * Entry point for the Fitness Tracker application.
 * Handles user profile setup and launches the main menu.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Try to load user profile from file
        User user = DataManager.loadUser();

        if (user == null) {
            // If no user profile found, prompt to create one
            System.out.println("Welcome! Let's set up your profile.");
            String username = InputHelper.getNonEmptyString(scanner, "Enter your name: ");
            int age = InputHelper.getPositiveIntInput(scanner, "Enter your age: ");
            double height = InputHelper.getPositiveDoubleInput(scanner, "Enter your height (in cm): ");
            double startingWeight = InputHelper.getPositiveDoubleInput(scanner, "Enter your starting weight (in lbs): ");

            user = new User(username, age, height, startingWeight);
            DataManager.saveUser(user);
            System.out.println("\nProfile saved! Welcome, " + user.getUsername() + "!\n");
        } else {
            System.out.println("\nWelcome back, " + user.getUsername() + "!\n");
        }

        // Launch the fitness tracker menu
        FitnessTracker tracker = new FitnessTracker(user);
        tracker.showMenu();

        scanner.close();
    }
}
