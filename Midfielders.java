public class Midfielders extends Player {
    public Midfielders(String name, int seasonGoals) {
        super(name, "Midfielder", seasonGoals);
    }

    public String performAction(boolean isStarPlayer) {
        RandomNumberGenerator random = new RandomNumberGenerator();
        int probability = random.generateRandomNumber(1, 100);

        if (isStarPlayer) {
            if (probability <= 10) return "Goal";
            if (probability <= 20) return "Behind";
            if (probability <= 55) return "PassToForward";
            if (probability <= 90) return "PassToMidfield";
            return "TurnoverMidfielder";
        } else {
            if (probability <= 5) return "Goal";
            if (probability <= 15) return "Behind";
            if (probability <= 45) return "PassToForward";
            if (probability <= 75) return "PassToMidfield";
            return "TurnoverMidfielder";
        }
    }
}
