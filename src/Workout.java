import java.time.LocalDate;


/**
 * Represents a logged workout session.
 */
public class Workout {
    private String type;
    private double duration;
    private double caloriesBurned;
    private LocalDate date;


    /**
     * Creates a workout entry.
     * @param type Type of workout (e.g., Running, Weightlifting, walking)
     * @param duration Duration of workout in minutes
     * @param caloriesBurned Calories burned during workout
     * @param date Date workout was performed
     */
    public Workout(String type, double duration, double caloriesBurned, LocalDate date) {
        this.type = type;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.date = date;
    }

   
    // --- Getters and Setters ---
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getDuration() { return duration; }
    public void setDuration(double duration) { this.duration = duration; }

    public double getCaloriesBurned() { return caloriesBurned; }
    public void setCaloriesBurned(double caloriesBurned) { this.caloriesBurned = caloriesBurned; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }


    /**
     * Provides a toString summary of the goal status.
     */
    @Override
    public String toString() {
        return "Workout{" +
                "type='" + type + '\'' +
                ", duration=" + duration +
                ", caloriesBurned=" + caloriesBurned +
                ", date=" + date +
                '}';
    }
}
