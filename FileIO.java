import java.io.*;
import java.util.*;

public class FileIO {
    public ArrayList<Player> readTeamFile(String filename) {
        ArrayList<Player> players = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String teamName = reader.readLine(); // Read team name
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String playerName = parts[0].trim();
                    String position = parts[1].trim();
                    int seasonGoals = Integer.parseInt(parts[2].trim());

                    Player player;
                    switch (position.toLowerCase()) {
                        case "forward":
                            player = new Forwards(playerName, seasonGoals);
                            break;
                        case "midfielder":
                            player = new Midfielders(playerName, seasonGoals);
                            break;
                        case "defender":
                            player = new Defender(playerName, seasonGoals);
                            break;
                        case "reserve":
                            player = new Reserve(playerName, seasonGoals);
                            break;
                        default:
                            continue;
                    }
                    players.add(player);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return players;
    }

    public void writeTeamFile(String filename, Team team) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(team.getTeamName());
            for (Player player : team.getPlayers()) {
                writer.println(String.format("%s,%s,%d",
                        player.getName(),
                        player.getPosition(),
                        player.getSeasonGoals()));
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}

