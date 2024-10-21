import java.util.Scanner;

public class Input {
    private Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    public int getStarPlayersCount() {
        while (true) {
            try {
                System.out.print("Enter the number of star players for each team (0-8): ");
                int count = scanner.nextInt();
                if (count >= 0 && count <= 8) {
                    return count;
                } else {
                    System.out.println("Please enter a number between 0 and 8.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

    // Add more input methods as needed
}