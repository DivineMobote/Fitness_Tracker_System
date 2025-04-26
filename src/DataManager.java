import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Handles saving and loading of fitness tracker data:
 * workouts, weight records, user profile, and fitness goals (with direction).
 */
public class DataManager {
    private static final String WORKOUT_FILE = "data/workouts.txt";
    private static final String WEIGHT_FILE = "data/weights.txt";
    private static final String GOAL_FILE = "data/goals.txt";
    private static final String USER_FILE = "data/user.txt";


    // --- Workouts ---
    public static void saveWorkouts(List<Workout> workouts) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(WORKOUT_FILE))) {
            for (Workout w : workouts) {
                pw.println(w.getType() + "," + w.getDuration() + "," + w.getCaloriesBurned() + "," + w.getDate());
            }
        } catch (IOException e) {
            System.out.println("Error saving workouts: " + e.getMessage());
        }
    }

    public static List<Workout> loadWorkouts() {
        List<Workout> workouts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(WORKOUT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    workouts.add(new Workout(
                        parts[0],
                        Double.parseDouble(parts[1]),
                        Double.parseDouble(parts[2]),
                        LocalDate.parse(parts[3])
                    ));
                }
            }
        } catch (IOException e) {
            // Ignore missing file, just return empty list
        }
        return workouts;
    }


    // --- Weight Records ---
    public static void saveWeights(List<WeightRecord> weights) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(WEIGHT_FILE))) {
            for (WeightRecord w : weights) {
                pw.println(w.getWeight() + "," + w.getDate());
            }
        } catch (IOException e) {
            System.out.println("Error saving weights: " + e.getMessage());
        }
    }

    public static List<WeightRecord> loadWeights() {
        List<WeightRecord> weights = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(WEIGHT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    weights.add(new WeightRecord(
                        Double.parseDouble(parts[0]),
                        LocalDate.parse(parts[1])
                    ));
                }
            }
        } catch (IOException e) {
            // Ignore missing file, just return empty list
        }
        return weights;
    }


    // --- Fitness Goal (with direction) ---
    public static void saveGoal(FitnessGoal goal) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(GOAL_FILE))) {
            pw.println(goal.getGoalType() + "," +
                       goal.getTargetValue() + "," +
                       goal.getCurrentValue() + "," +
                       goal.getDirection() + "," +
                       goal.isCompleted());
        } catch (IOException e) {
            System.out.println("Error saving goal: " + e.getMessage());
        }
    }

    public static FitnessGoal loadGoal() {
        try (BufferedReader br = new BufferedReader(new FileReader(GOAL_FILE))) {
            String line = br.readLine();
            if (line != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    FitnessGoal goal = new FitnessGoal(
                        parts[0],
                        Double.parseDouble(parts[1]),
                        GoalDirection.valueOf(parts[3])
                    );
                    goal.setCurrentValue(Double.parseDouble(parts[2]));
                    // completed status will update via setCurrentValue
                    return goal;
                }
            }
        } catch (IOException e) {
            // Ignore missing file, just return null
        }
        return null;
    }

    
    // --- User Profile ---
    public static void saveUser(User user) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(USER_FILE))) {
            pw.println(user.getUsername() + "," +
                       user.getAge() + "," +
                       user.getHeight() + "," +
                       user.getStartingWeight());
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public static User loadUser() {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line = br.readLine();
            if (line != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    return new User(
                        parts[0],
                        Integer.parseInt(parts[1]),
                        Double.parseDouble(parts[2]),
                        Double.parseDouble(parts[3])
                    );
                }
            }
        } catch (IOException e) {
            // Ignore missing file, just return null
        }
        return null;
    }
}