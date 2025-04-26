import java.util.List;
import java.util.Scanner;

/**
 * Main controller for the Fitness Tracker app.
 * Handles workouts, weights, fitness goals (loss/gain), user profile, and user input.
 */
public class FitnessTracker {
    private User user;
    private List<Workout> workouts;
    private List<WeightRecord> weights;
    private FitnessGoal goal;

    public FitnessTracker(User user) {
        this.user = user;
        this.workouts = DataManager.loadWorkouts();
        this.weights = DataManager.loadWeights();
        this.goal = DataManager.loadGoal();
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Fitness Tracker Main Menu ---");
            System.out.println("1. Log Workout");
            System.out.println("2. Record Weight");
            System.out.println("3. Set/View Fitness Goal");
            System.out.println("4. View Progress Summary");
            System.out.println("5. View/Edit Profile");
            System.out.println("6. Exit");
            int choice = InputHelper.getPositiveIntInput(scanner, "Choose an option: ");

            switch (choice) {
                case 1:
                    logWorkout(scanner);
                    break;
                case 2:
                    recordWeight(scanner);
                    break;
                case 3:
                    setOrViewGoal(scanner);
                    break;
                case 4:
                    viewProgress();
                    break;
                case 5:
                    viewOrEditProfile(scanner);
                    break;
                case 6:
                    running = false;
                    saveAll();
                    System.out.println("Goodbye! Stay motivated!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }


    /**
     * Logs a new workout session.
     */
    private void logWorkout(Scanner scanner) {
        String type = InputHelper.getNonEmptyString(scanner, "Workout type (e.g., Running): ");
        double duration = InputHelper.getPositiveDoubleInput(scanner, "Duration (minutes): ");
        double calories = InputHelper.getPositiveDoubleInput(scanner, "Calories burned: ");

        Workout workout = new Workout(type, duration, calories, java.time.LocalDate.now());
        workouts.add(workout);
        DataManager.saveWorkouts(workouts);

        System.out.println("\n" + EncouragementMessage.getMotivationalMessage());
        System.out.println("Workout logged!");
    }


    /**
     * Records a new weight entry and updates goal progress.
     */
    private void recordWeight(Scanner scanner) {
        double weight = InputHelper.getPositiveDoubleInput(scanner, "Enter your current weight: ");
        WeightRecord record = new WeightRecord(weight, java.time.LocalDate.now());
        weights.add(record);
        DataManager.saveWeights(weights);


        if (goal != null) {
            goal.setCurrentValue(weight);
            DataManager.saveGoal(goal);
        }

        System.out.println("Weight recorded!");
    }


    /**
     * Sets or views the user's fitness goal with direction (loss/gain).
     */
    private void setOrViewGoal(Scanner scanner) {
        if (goal == null) {
            System.out.println("No goal set yet. Let's set one!");
            String goalType = InputHelper.getNonEmptyString(scanner, "Goal type (e.g., Target Weight): ");
            double target = InputHelper.getPositiveDoubleInput(scanner, "Target value: ");
            GoalDirection direction = promptGoalDirection(scanner);

            goal = new FitnessGoal(goalType, target, direction);
            DataManager.saveGoal(goal);
            System.out.println("Goal saved!");
        } else {
            // Show in a user-friendly format
            System.out.println("\nCurrent Goal:");
            System.out.println(goal);
            System.out.print("Do you want to set a new goal? (y/n): ");
            String resp = scanner.nextLine();
            if (resp.equalsIgnoreCase("y")) {
                String goalType = InputHelper.getNonEmptyString(scanner, "Goal type: ");
                double target = InputHelper.getPositiveDoubleInput(scanner, "Target value: ");
                GoalDirection direction = promptGoalDirection(scanner);
                goal = new FitnessGoal(goalType, target, direction);
                DataManager.saveGoal(goal);
                System.out.println("Goal updated!");
            }
        }
    }


    /**
     * Prompts user for goal direction (LOSS/GAIN).
     */
    private GoalDirection promptGoalDirection(Scanner scanner) {
        while (true) {
            System.out.print("Is this a weight loss or gain goal? (loss/gain): ");
            String dirInput = scanner.nextLine().trim().toLowerCase();
            if (dirInput.equals("loss")) return GoalDirection.LOSS;
            if (dirInput.equals("gain")) return GoalDirection.GAIN;
            System.out.println("Please enter 'loss' or 'gain'.");
        }
    }


    /**
     * Displays the user's progress summary, ensuring goal is up to date.
     */
    private void viewProgress() {
        // Double-check goal's current value is synced (backup, should already be updated in recordWeight)
        if (goal != null && weights != null && !weights.isEmpty()) {
            double latestWeight = weights.get(weights.size() - 1).getWeight();
            goal.setCurrentValue(latestWeight);
            DataManager.saveGoal(goal);
        }
        ProgressSummary summary = new ProgressSummary(workouts, weights, goal, user);
        System.out.println(summary);
    }


    /**
     * Displays and allows editing of the user's profile with input validation.
     */
    private void viewOrEditProfile(Scanner scanner) {
        System.out.println("\n--- Profile ---");
        System.out.println(user);
        System.out.print("Do you want to edit your profile? (y/n): ");
        String resp = scanner.nextLine();
        if (resp.equalsIgnoreCase("y")) {
            String username = InputHelper.getNonEmptyString(scanner, "Enter your name: ");
            int age = InputHelper.getPositiveIntInput(scanner, "Enter your age: ");
            double height = InputHelper.getPositiveDoubleInput(scanner, "Enter your height (in cm): ");
            double startingWeight = InputHelper.getPositiveDoubleInput(scanner, "Enter your starting weight: ");

            user.setUsername(username);
            user.setAge(age);
            user.setHeight(height);
            user.setStartingWeight(startingWeight);
            DataManager.saveUser(user);
            System.out.println("Profile updated!");
        }
    }


    /**
     * Saves all data to files.
     */
    private void saveAll() {
        DataManager.saveWorkouts(workouts);
        DataManager.saveWeights(weights);
        DataManager.saveGoal(goal);
        DataManager.saveUser(user);
    }
}