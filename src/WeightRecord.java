import java.time.LocalDate;


/**
 * Represents a weight entry with the date it was recorded.
 */
public class WeightRecord {
    private double weight;
    private LocalDate date;


    /**
     * Creates a weight record entry.
     * @param weight The weight value
     * @param date The date the weight was recorded
     */
    public WeightRecord(double weight, LocalDate date) {
        this.weight = weight;
        this.date = date;
    }


    // --- Getters and Setters ---
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }


    /**
     * Provides a toString summary of the goal status.
     */
    @Override
    public String toString() {
        return "WeightRecord{" +
                "weight=" + weight +
                ", date=" + date +
                '}';
    }
}
