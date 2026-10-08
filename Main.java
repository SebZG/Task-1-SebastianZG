/**
 * Number Guessing Game — DecodeLabs Project 1
 */

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private static Random random;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        random = new Random();

        printWelcome();
        int maxAttempts = getNumOfAttempts();
        playGame(maxAttempts);

        scanner.close();
    }

    private static void printWelcome() {
        System.out.println("=== NUMBER GUESSING GAME ===");
        System.out.println("Welcome! I'll pick a number between 1 and 100.");
        System.out.println("Try to guess it!\n");
    }

    private static int getNumOfAttempts() {
        int limit = 0;
        do {
            System.out.print("How many attempts per round (1-10): ");
            try {
                limit = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer

                if (limit < 1 || limit > 10) {
                    System.out.println(
                        "⚠ Invalid number of attempts. Choose between 1-10.\n"
                    );
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Please enter a valid whole number.\n");
                scanner.nextLine(); // Clear bad input
                limit = 0; // Force loop to repeat
            }
        } while (limit < 1 || limit > 10);

        System.out.println(
            "✅ Great! You have " + limit + " attempts per round.\n"
        );
        return limit;
    }

    private static void playGame(int maxAttempts) {
        int totalScore = 0;
        int rounds = 0;
        boolean playAgain;

        // Outer loop for multiple rounds
        do {
            rounds++;

            int roundScore = playOneRound(maxAttempts, rounds, totalScore); // Play one round and get the score
            totalScore += roundScore;

            // Replay prompt
            System.out.print("\nPlay again? (Y/N): ");
            String response = scanner.nextLine().trim();
            playAgain = response.equalsIgnoreCase("Y");
            System.out.println();
        } while (playAgain);

        printGameOver(totalScore, rounds);
    }

    private static int playOneRound(
        int maxAttempts,
        int roundNum,
        int runningTotal
    ) {
        int targetNumber = random.nextInt(1, 101);
        int attempts = 0;
        boolean guessedCorrectly = false;

        System.out.println("--- Round " + roundNum + " ---");

        // Inner loop for guessing attempts
        while (attempts < maxAttempts && !guessedCorrectly) {
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
                    int points = maxAttempts - attempts + 1; // Fewer tries == more points
                    System.out.printf(
                        "✅ Correct! You got in %d attempt(s)!\n",
                        attempts
                    );
                    System.out.printf(
                        "You earned %d points. Total score: %d\n",
                        points,
                        runningTotal + points
                    );
                    return points; // Return points earned this round
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
                maxAttempts
            );
            System.out.printf("The target number was: %d\n", targetNumber);
        }

        return 0;
    }

    private static void printGameOver(int totalScore, int rounds) {
        System.out.println("=== GAME OVER ===");
        System.out.printf("Final score: % 4d\n", totalScore);
        System.out.printf("# rounds: % 7d\n", rounds);
        System.out.println("Thank you for playing!");
    }
}
