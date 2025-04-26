import java.util.List;


/**
 * User's overall fitness progress
 * including calories burned, weight changes, and goal status.
 */
public class ProgressSummary {
    private double totalCaloriesBurned;
    private double startingWeight;
    private double currentWeight;
    private double weightChange;
    private String goalStatus;


    /**
     * Constructs a summary for workouts, weights, the active goal, and user profile.
     */
    public ProgressSummary(List<Workout> workouts, List<WeightRecord> weights, FitnessGoal goal, User user) {
        calculateCalories(workouts);
        calculateWeightChange(weights, user);
        calculateGoalStatus(goal);
    }


    /**
     * Calculates total calories burned from all recorded workouts.
     */
    private void calculateCalories(List<Workout> workouts) {
        totalCaloriesBurned = 0;
        if (workouts != null) {
            for (Workout w : workouts) {
                totalCaloriesBurned += w.getCaloriesBurned();
            }
        }
    }


    /**
     * Determines weight change from starting weight to most recent weight.
     */
    private void calculateWeightChange(List<WeightRecord> weights, User user) {
        if (user != null) {
            startingWeight = user.getStartingWeight();
        }
        if (weights != null && !weights.isEmpty()) {
            currentWeight = weights.get(weights.size() - 1).getWeight();
            weightChange = currentWeight - startingWeight;
        } else {
            currentWeight = startingWeight;
            weightChange = 0;
        }
    }


    /**
     * Displays a status message based on whether a goal exists and is completed.
     */
    private void calculateGoalStatus(FitnessGoal goal) {
        if (goal == null) {
            goalStatus = "No goal set.";
        } else if (goal.isCompleted()) {
            goalStatus = "Goal completed!";
        } else {
            goalStatus = "In progress: " + goal.getCurrentValue() + " / " + goal.getTargetValue();
        }
    }


    /**
     * Provides a toString summary of the goal status.
     */
    @Override
    public String toString() {
        return "Progress Summary:\n" +
                "Total Calories Burned: " + totalCaloriesBurned + "\n" +
                "Weight Change: " + startingWeight + " -> " + currentWeight +
                " (" + (weightChange >= 0 ? "+" : "") + weightChange + ")\n" +
                "Goal Status: " + goalStatus;
    }
}
