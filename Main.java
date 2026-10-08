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

            // Read full line first → catches empty Enter
            String inputLine = scanner.nextLine().trim();

            // CHECK 1: Empty input ONLY — user pressed Enter with nothing else
            if (inputLine.isEmpty()) {
                System.out.println("❌ Please enter a valid whole number.\n");
                continue;
            }

            // Use a SECOND Scanner on the line → InputMismatchException works
            Scanner lineScanner = new Scanner(inputLine);

            // CHECK 2: Everything else — enters try/catch
            try {
                limit = lineScanner.nextInt();

                if (limit < 1 || limit > 10) {
                    System.out.println(
                        "⚠ Invalid number of attempts. Choose between 1-10.\n"
                    );
                    limit = 0;
                }
            } catch (InputMismatchException e) {
                // InputMismatchException - for non-empty, non-numeric input
                System.out.println("❌ Please enter a valid whole number.\n");
                limit = 0;
            } finally {
                lineScanner.close(); // Clean up
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

            // Read full line first → catches empty Enter
            String inputLine = scanner.nextLine().trim();

            // CHECK 1: Empty input → no exception needed, just prompt again
            if (inputLine.isEmpty()) {
                System.out.println("❌ Please enter a valid number.\n");
                continue; // No attempt lost
            }

            // Use a SECOND Scanner on the line → InputMismatchException works
            Scanner lineScanner = new Scanner(inputLine);

            try {
                int userGuess = lineScanner.nextInt();

                if (userGuess < 1 || userGuess > 100) {
                    System.out.println(
                        "Please enter a number between 1 and 100.\n"
                    );
                    continue; // No attempt lost
                }

                attempts++; // Only valid numbers count

                if (userGuess == targetNumber) {
                    guessedCorrectly = true;
                    int points = maxAttempts - attempts + 1;
                    System.out.printf(
                        "✅ Correct! You got it in %d attempt(s)!\n",
                        attempts
                    );
                    System.out.printf(
                        "You earned %d points. Total score: %d\n",
                        points,
                        runningTotal + points
                    );
                    return points;
                } else if (userGuess > targetNumber) {
                    System.out.println("⬇ Too high! Try lower.\n");
                } else {
                    System.out.println("⬆ Too low! Try higher\n");
                }
            } catch (InputMismatchException e) {
                // InputMismatchException - for non-empty, non-numeric input
                System.out.println(
                    "❌ Invalid Input! Please enter a valid number.\n"
                );
            } finally {
                lineScanner.close();
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
