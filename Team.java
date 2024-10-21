import java.util.ArrayList;

public class Team {
    private String teamName;
    private ArrayList<Player> players;
    private int teamScore;
    private int totalGoals;
    private int totalBehinds;
    private ArrayList<Player> injuredPlayers;
    private ArrayList<Player> reportedPlayers;

    public Team(String teamName, ArrayList<Player> players) {
        this.teamName = teamName;
        this.players = players;
        this.teamScore = 0;
        this.totalGoals = 0;
        this.totalBehinds = 0;
        this.injuredPlayers = new ArrayList<>();
        this.reportedPlayers = new ArrayList<>();
    }

    public void addGoal() {
        totalGoals++;
        teamScore += 6;
    }

    public void addBehind() {
        totalBehinds++;
        teamScore += 1;
    }

    public void addInjuredPlayer(Player player) {
        if (!injuredPlayers.contains(player)) {
            injuredPlayers.add(player);
            player.setInjured(true);
        }
    }

    public void addReportedPlayer(Player player) {
        if (!reportedPlayers.contains(player)) {
            reportedPlayers.add(player);
            player.setReported(true);
        }
    }

    public Player getRandomPlayer(String position) {
        ArrayList<Player> availablePlayers = new ArrayList<>();
        for (Player p : players) {
            if (p.getPosition().equals(position) && !p.isInjured()) {
                availablePlayers.add(p);
            }
        }
        if (availablePlayers.isEmpty()) return null;

        RandomNumberGenerator random = new RandomNumberGenerator();
        int index = random.generateRandomNumber(0, availablePlayers.size() - 1);
        return availablePlayers.get(index);
    }

    // Getters
    public String getTeamName() { return teamName; }
    public ArrayList<Player> getPlayers() { return players; }
    public int getTeamScore() { return teamScore; }
    public int getTotalGoals() { return totalGoals; }
    public int getTotalBehinds() { return totalBehinds; }
    public ArrayList<Player> getInjuredPlayers() { return injuredPlayers; }
    public ArrayList<Player> getReportedPlayers() { return reportedPlayers; }

    public Player getHighestGoalScorer() {
        Player highestScorer = null;
        int maxGoals = -1;
        for (Player p : players) {
            if (p.getGoals() > maxGoals) {
                maxGoals = p.getGoals();
                highestScorer = p;
            }
        }
        return highestScorer;
    }

    public Player getMostKicks() {
        Player mostKicks = null;
        int maxKicks = -1;
        for (Player p : players) {
            if (p.getKicks() > maxKicks) {
                maxKicks = p.getKicks();
                mostKicks = p;
            }
        }
        return mostKicks;
    }
}