/**
 * Represents a fitness goal.
 * Handles tracking of current progress and completion status.
 */
public class FitnessGoal {
    private String goalType;        
    private double targetValue;     
    private double currentValue;    
    private boolean completed;      
    private GoalDirection direction; 


    /**
     * Constructs a FitnessGoal.
     * @param goalType      Description of the goal
     * @param targetValue   Target value to reach
     * @param direction     Direction of the goal (LOSS or GAIN)
     */
    public FitnessGoal(String goalType, double targetValue, GoalDirection direction) {
        this.goalType = goalType;
        this.targetValue = targetValue;
        this.direction = direction;
        this.currentValue = 0;
        this.completed = false;
    }


    // --- Getters and Setters ---
    public String getGoalType() { return goalType; }
    public void setGoalType(String goalType) { this.goalType = goalType; }

    public double getTargetValue() { return targetValue; }
    public void setTargetValue(double targetValue) { this.targetValue = targetValue; }

    public double getCurrentValue() { return currentValue; }
    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
        checkProgress(); 
    }

    public boolean isCompleted() { return completed; }

    public GoalDirection getDirection() { return direction; }
    public void setDirection(GoalDirection direction) { this.direction = direction; }


    /**
     * Checks and updates the completion status based on goal direction.
     * - LOSS: Completed if current <= target
     * - GAIN: Completed if current >= target
     */
    public void checkProgress() {
        if (direction == GoalDirection.LOSS) {
            completed = currentValue <= targetValue;
        } else if (direction == GoalDirection.GAIN) {
            completed = currentValue >= targetValue;
        }
    }


    /**
     * Provides a toString summary of the goal status.
     */
    @Override
    public String toString() {
        return "Goal: " + goalType + " (" + (direction == GoalDirection.LOSS ? "Loss" : "Gain") + ")\n" +
                "Target: " + targetValue +
                "\nCurrent: " + currentValue +
                "\nStatus: " + (completed ? "Completed!" : "In progress");
    }
}
