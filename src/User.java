/**
 * Stores user profile information for the fitness tracker.
 */
public class User {
    private String username;
    private int age;
    private double height;    
    private double startingWeight;


    /**
     * Creates a user profile.
     * @param username The user's name
     * @param age User's age
     * @param height User's height (cm or inches)
     * @param startingWeight User's starting weight (lbs or kg)
     */
    public User(String username, int age, double height, double startingWeight) {
        this.username = username;
        this.age = age;
        this.height = height;
        this.startingWeight = startingWeight;
    }

    
    // --- Getters and Setters --- 
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public double getStartingWeight() { return startingWeight; }
    public void setStartingWeight(double startingWeight) { this.startingWeight = startingWeight; }


    /**
     * Provides a toString summary of the goal status.
     */
    @Override
    public String toString() {
        return "User Profile:\n" +
                "Username: " + username + "\n" +
                        "Age: " + age + "\n" +
                "Height: " + height + "\n" +
                "Starting Weight: " + startingWeight;
    }
}
