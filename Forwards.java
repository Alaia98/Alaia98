public class Forwards extends Player {
    public Forwards(String name, int seasonGoals) {
        super(name, "Forward", seasonGoals);
    }

    public String performAction(boolean isStarPlayer) {
        RandomNumberGenerator random = new RandomNumberGenerator();
        int probability = random.generateRandomNumber(1, 100);

        if (isStarPlayer) {
            if (probability <= 45) return "Goal";
            if (probability <= 85) return "Behind";
            if (probability <= 95) return "PassToForward";
            return "TurnoverDefender";
        } else {
            if (probability <= 30) return "Goal";
            if (probability <= 70) return "Behind";
            if (probability <= 90) return "PassToForward";
            return "TurnoverDefender";
        }
    }
}