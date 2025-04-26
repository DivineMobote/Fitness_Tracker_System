import java.util.Random;


/**
 * Provides motivational encouragement messages.
 * Helps keep the user positive and motivated after logging workouts.
 */ 
public class EncouragementMessage {
    private static final String[] MESSAGES = {
        "You're doing amazing! Keep pushing, stay strong and trust the process.",
        "Every rep gets you closer to your goal. Don't give up!",
        "Progress takes time—keep moving forward!",
        "Believe in yourself, you've got this!",
        "Small steps lead to big results. Stay consistent!"
    };


    /**
     * Returns a random motivational message from the set.
     * @return A randomly encouragement string
     */
    public static String getMotivationalMessage() {
        Random rand = new Random();
        int idx = rand.nextInt(MESSAGES.length);
        return MESSAGES[idx];
    }
}
