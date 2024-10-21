import java.util.ArrayList;

public class GameReport {
    private Team teamA;
    private Team teamB;
    private String winner;
    private boolean isDraw;

    public GameReport(Team teamA, Team teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.determineResult();
    }

    private void determineResult() {
        if (teamA.getTeamScore() > teamB.getTeamScore()) {
            winner = teamA.getTeamName();
            isDraw = false;
        } else if (teamB.getTeamScore() > teamA.getTeamScore()) {
            winner = teamB.getTeamName();
            isDraw = false;
        } else {
            isDraw = true;
        }
    }

    public void generateFinalReport() {
        System.out.println("\n=== FINAL GAME REPORT ===\n");

        // Game Result
        if (isDraw) {
            System.out.println("Game Result: Draw");
        } else {
            System.out.println("Winner: " + winner);
        }

        // Team Scores
        printTeamScore(teamA);
        printTeamScore(teamB);

        // Best Players
        printBestPlayers(teamA);
        printBestPlayers(teamB);

        // Player Statistics
        System.out.println("\n=== PLAYER STATISTICS ===");
        printTeamStatistics(teamA);
        printTeamStatistics(teamB);

        // Injured Players
        printInjuredPlayers();

        // Reported Players
        printReportedPlayers();
    }

    private void printTeamScore(Team team) {
        System.out.println("\n" + team.getTeamName() + ":");
        System.out.println("Goals: " + team.getTotalGoals());
        System.out.println("Behinds: " + team.getTotalBehinds());
        System.out.println("Total Points: " + team.getTeamScore());
    }

    private void printBestPlayers(Team team) {
        System.out.println("\n" + team.getTeamName() + " Best Players:");
        Player topScorer = team.getHighestGoalScorer();
        Player mostKicks = team.getMostKicks();

        System.out.println("Highest Goal Scorer: " + topScorer.getName() +
                " (" + topScorer.getGoals() + " goals)");
        System.out.println("Most Kicks: " + mostKicks.getName() +
                " (" + mostKicks.getKicks() + " kicks)");
    }

    private void printTeamStatistics(Team team) {
        System.out.println("\n" + team.getTeamName() + " Player Statistics:");
        System.out.println("Name\t\tPosition\tKicks\tGoals\tBehinds\tEffective %\tStatus");
        System.out.println("--------------------------------------------------------------------");

        for (Player player : team.getPlayers()) {
            String status = "";
            if (player.isInjured()) status += "INJURED ";
            if (player.isReported()) status += "REPORTED";
            if (status.isEmpty()) status = "ACTIVE";

            System.out.printf("%-15s %-10s %5d %7d %8d %10.1f %15s%n",
                    player.getName(),
                    player.getPosition(),
                    player.getKicks(),
                    player.getGoals(),
                    player.getBehinds(),
                    player.getEffectiveDisposalPercentage(),
                    status);
        }
    }

    private void printInjuredPlayers() {
        System.out.println("\n=== INJURED PLAYERS ===");
        printPlayerList(teamA.getInjuredPlayers(), teamA.getTeamName());
        printPlayerList(teamB.getInjuredPlayers(), teamB.getTeamName());
    }

    private void printReportedPlayers() {
        System.out.println("\n=== REPORTED PLAYERS ===");
        printPlayerList(teamA.getReportedPlayers(), teamA.getTeamName());
        printPlayerList(teamB.getReportedPlayers(), teamB.getTeamName());
    }

    private void printPlayerList(ArrayList<Player> players, String teamName) {
        if (!players.isEmpty()) {
            System.out.println(teamName + ":");
            for (Player player : players) {
                System.out.println("- " + player.getName() + " (" + player.getPosition() + ")");
            }
        }
    }
}