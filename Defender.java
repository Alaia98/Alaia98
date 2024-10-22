public class Defender extends Player {
    public Defender(String name, int seasonGoals) {
        super(name, "Defender", seasonGoals);
    }

    public String performAction(boolean isStarPlayer) {
        RandomNumberGenerator random = new RandomNumberGenerator();
        int probability = random.generateRandomNumber(1, 100);

        if (isStarPlayer) {
            if (probability <= 95) return "PassToMidfield";
            return "TurnoverForward";
        } else {
            if (probability <= 80) return "PassToMidfield";
            return "TurnoverForward";
        }
    }
}