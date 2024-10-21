import java.util.ArrayList;

public class AFLGame {
    private Team teamA;
    private Team teamB;
    private RandomNumberGenerator random;
    private Validation validator;

    public AFLGame() {
        random = new RandomNumberGenerator();
        validator = new Validation();
    }

    public void startGame() {
        // Initialize teams
        FileIO fileIO = new FileIO();
        ArrayList<Player> teamAPlayers = fileIO.readTeamFile("teamA.txt");
        ArrayList<Player> teamBPlayers = fileIO.readTeamFile("teamB.txt");

        teamA = new Team("Team A", teamAPlayers);
        teamB = new Team("Team B", teamBPlayers);

        // Validate teams
        if (!validator.validateTeamSize(teamA) || !validator.validateTeamSize(teamB) ||
                !validator.validatePlayerPositions(teamA) || !validator.validatePlayerPositions(teamB)) {
            System.out.println("Invalid team configuration. Game cannot start.");
            return;
        }

        // Get star players count
        Input input = new Input();
        int starPlayersCount = input.getStarPlayersCount();
        selectStarPlayers(teamA, starPlayersCount);
        selectStarPlayers(teamB, starPlayersCount);

        // Play all quarters
        for (int quarter = 1; quarter <= 4; quarter++) {
            System.out.println("\nQuarter " + quarter + " starting...");
            playQuarter();

            if (!validator.canContinueGame(teamA)) {
                System.out.println("\nTeam A has forfeited due to injuries!");
                break;
            }
            if (!validator.canContinueGame(teamB)) {
                System.out.println("\nTeam B has forfeited due to injuries!");
                break;
            }

            printQuarterScore(quarter);
        }

        // Generate final report
        GameReport report = new GameReport(teamA, teamB);
        report.generateFinalReport();

        // Update team files
        fileIO.writeTeamFile("teamAUpdated.txt", teamA);
        fileIO.writeTeamFile("teamBUpdated.txt", teamB);
    }

    private void selectStarPlayers(Team team, int count) {
        ArrayList<Player> availablePlayers = new ArrayList<>(team.getPlayers());
        for (int i = 0; i < count; i++) {
            if (availablePlayers.isEmpty()) break;
            int index = random.generateRandomNumber(0, availablePlayers.size() - 1);
            Player player = availablePlayers.remove(index);
            player.setStarPlayer(true);
        }
    }

    private void playQuarter() {
        Team firstPossession = (random.generateRandomNumber(1, 2) == 1) ? teamA : teamB;
        Player currentPlayer = firstPossession.getRandomPlayer("Midfielder");

        int eventsPerQuarter = 80;
        for (int event = 0; event < eventsPerQuarter; event++) {
            if (currentPlayer == null) break;

            // Check for injury
            if (random.generateRandomNumber(1, 100) <= 2) {
                handleInjury(currentPlayer);
            }

            // Check for report
            if (random.generateRandomNumber(1, 100) <= 1) {
                handleReport(currentPlayer);
            }

            // Process player action
            String outcome = processPlayerAction(currentPlayer);
            currentPlayer = getNextPlayer(outcome, currentPlayer);
        }
    }

    private String processPlayerAction(Player player) {
        player.addKick();
        String outcome = "";

        if (player instanceof Forwards) {
            outcome = ((Forwards) player).performAction(player.isStarPlayer());
        } else if (player instanceof Midfielders) {
            outcome = ((Midfielders) player).performAction(player.isStarPlayer());
        } else if (player instanceof Defender) {
            outcome = ((Defender) player).performAction(player.isStarPlayer());
        }

        handleOutcome(outcome, player);
        return outcome;
    }

    private void handleOutcome(String outcome, Player player) {
        Team currentTeam = (teamA.getPlayers().contains(player)) ? teamA : teamB;

        switch (outcome) {
            case "Goal":
                currentTeam.addGoal();
                player.addGoal();
                break;
            case "Behind":
                currentTeam.addBehind();
                player.addBehind();
                break;
            case "PassToForward":
            case "PassToMidfield":
                player.addEffectiveDisposal();
                break;
        }
    }

    private Player getNextPlayer(String outcome, Player currentPlayer) {
        Team currentTeam = (teamA.getPlayers().contains(currentPlayer)) ? teamA : teamB;
        Team opposingTeam = (currentTeam == teamA) ? teamB : teamA;

        return switch (outcome) {
            case "Goal" -> getRandomMidfielder(random.generateRandomNumber(1, 2) == 1 ? teamA : teamB);
            case "Behind" -> opposingTeam.getRandomPlayer("Defender");
            case "PassToForward" -> currentTeam.getRandomPlayer("Forward");
            case "PassToMidfield" -> currentTeam.getRandomPlayer("Midfielder");
            case "TurnoverForward" -> opposingTeam.getRandomPlayer("Forward");
            case "TurnoverMidfielder" -> opposingTeam.getRandomPlayer("Midfielder");
            case "TurnoverDefender" -> opposingTeam.getRandomPlayer("Defender");
            default -> null;
        };
    }

    private void handleInjury(Player player) {
        Team team = (teamA.getPlayers().contains(player)) ? teamA : teamB;
        Player replacement = team.getRandomPlayer("Reserve");
        if (replacement != null) {
            replacement.setPosition(player.getPosition());
            team.addInjuredPlayer(player);
        }
    }

    private void handleReport(Player player) {
        Team team = (teamA.getPlayers().contains(player)) ? teamA : teamB;
        team.addReportedPlayer(player);
    }

    private Player getRandomMidfielder(Team team) {
        return team.getRandomPlayer("Midfielder");
    }

    private void printQuarterScore(int quarter) {
        System.out.println("\nEnd of Quarter " + quarter + ":");
        System.out.println(teamA.getTeamName() + ": " + teamA.getTeamScore());
        System.out.println(teamB.getTeamName() + ": " + teamB.getTeamScore());
    }

    public static void main(String[] args) {
        AFLGame game = new AFLGame();
        game.startGame();
    }
}