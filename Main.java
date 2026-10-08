/**
 * Number Guessing Game — DecodeLabs Project 1
 */

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        boolean playAgain;
        int totalScore = 0;
        int rounds = 0;
        int MAX_ATTEMPTS = 0;

        System.out.println("=== NUMBER GUESSING GAME ===");
        System.out.println("Welcome! I'll pick a number between 1 and 100.");
        System.out.println("Try to guess it!\n");
        do {
            System.out.print("How many attempts per round (1-10): ");
            try {
                MAX_ATTEMPTS = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer

                if (MAX_ATTEMPTS < 1 || MAX_ATTEMPTS > 10) {
                    System.out.println(
                        "⚠ Invalid number of attempts. Choose between 1-10.\n"
                    );
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Please enter a valid whole number.\n");
                scanner.nextLine(); // Clear bad input
                MAX_ATTEMPTS = 0; // Force loop to repeat
            }
        } while (MAX_ATTEMPTS < 1 || MAX_ATTEMPTS > 10);

        System.out.println(
            "✅ Great! You have " + MAX_ATTEMPTS + " attempts per round.\n"
        );

        // Outer loop for multiple rounds
        do {
            rounds++;
            int targetNumber = random.nextInt(1, 101);
            int attempts = 0;
            boolean guessedCorrectly = false;

            // Inner loop for guessing attempts
            while (attempts < MAX_ATTEMPTS && !guessedCorrectly) {
                System.out.print("Enter a number (1-100): ");

                try {
                    int userGuess = scanner.nextInt();
                    scanner.nextLine(); // Clear the buffer

                    if (userGuess < 1 || userGuess > 100) {
                        System.out.println(
                            "Please enter a number between 1 and 100.\n"
                        );
                        continue;
                    }

                    attempts++; // Increment attempts after a valid guess

                    if (userGuess == targetNumber) {
                        guessedCorrectly = true;
                        int points = MAX_ATTEMPTS - attempts + 1; // Fewer tries == more points
                        totalScore += points;
                        System.out.printf(
                            "✅ Correct! You got in %d attempt(s)!\n",
                            attempts
                        );
                        System.out.printf(
                            "You earned %d points. Total score: %d\n",
                            points,
                            totalScore
                        );
                    } else if (userGuess > targetNumber) {
                        System.out.println("⬇ Too high! Try lower.\n");
                    } else if (userGuess < targetNumber) {
                        System.out.println("⬆ Too low! Try higher\n");
                    }
                } catch (InputMismatchException e) {
                    System.out.println(
                        "❌ Invalid Input! Please enter a valid number.\n"
                    );
                    scanner.nextLine(); // Clear the buffer
                }
            }

            if (!guessedCorrectly) {
                System.out.printf(
                    "💔 You've used all your %d attempts.\n",
                    MAX_ATTEMPTS
                );
                System.out.printf("The target number was: %d\n", targetNumber);
            }

            // Replay prompt
            System.out.print("\nPlay again? (Y/N): ");
            String response = scanner.nextLine().trim();
            playAgain = response.equalsIgnoreCase("Y");
            System.out.println();
        } while (playAgain);

        System.out.println("=== GAME OVER ===");
        System.out.printf("Final score: % 4d\n", totalScore);
        System.out.printf("# rounds: % 7d\n", rounds);
        System.out.println("Thank you for playing!");

        scanner.close();
    }
}
