/**
 * Number Guessing Game
 */
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        boolean win = false;
        int target = random.nextInt(1, 101);

        while (!win) {
            System.out.print("Enter a number (1-100): ");

            try {
                int guess = scanner.nextInt();

                if (guess < 1 || guess > 100) {
                    System.out.println(
                        "Please enter a number between 1 and 100."
                    );
                } else if (guess == target) {
                    System.out.println("Correct!");
                    return;
                } else if (guess > target) {
                    System.out.println("Too High");
                } else if (guess < target) {
                    System.out.println("Too Low");
                }
            } catch (InputMismatchException e) {
                System.out.println(
                    "Invalid Input! Please enter a valid number."
                );
                scanner.nextLine(); // Clear the buffer
            }
        }

        scanner.close();
    }
}
