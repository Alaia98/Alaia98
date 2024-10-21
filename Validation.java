public class Validation {
    public boolean validateStarPlayersCount(int count) {
        return count >= 0 && count <= 8;
    }

    public boolean validateTeamSize(Team team) {
        return team != null && team.getPlayers().size() == 22;
    }

    public boolean validatePlayerPositions(Team team) {
        int forwards = 0;
        int midfielders = 0;
        int defenders = 0;
        int reserves = 0;

        for (Player player : team.getPlayers()) {
            switch (player.getPosition().toLowerCase()) {
                case "forward":
                    forwards++;
                    break;
                case "midfielder":
                    midfielders++;
                    break;
                case "defender":
                    defenders++;
                    break;
                case "reserve":
                    reserves++;
                    break;
            }
        }

        return forwards == 5 && midfielders == 8 && defenders == 5 && reserves == 4;
    }

    public boolean canContinueGame(Team team) {
        return team.getInjuredPlayers().size() <= 4;
    }

    public boolean isValidGameEvent(String eventOutcome) {
        String[] validOutcomes = {"Goal", "Behind", "PassToForward", "PassToMidfield",
                "TurnoverForward", "TurnoverMidfielder", "TurnoverDefender"};
        for (String outcome : validOutcomes) {
            if (outcome.equals(eventOutcome)) return true;
        }
        return false;
    }
}