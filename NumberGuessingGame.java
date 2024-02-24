import java.util.Random;
import java.util.Scanner;


public class NumberGuessingGame
{

    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100 (inclusive)

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100. Can you guess it?");

        // Ask for number of attempts
        int maxAttempts = 0;
        while (maxAttempts <= 0) {
            System.out.print("How many attempts do you want (minimum 1): ");
            maxAttempts = scanner.nextInt();
        }

        // Optional difficulty selection
        System.out.println("Choose difficulty (easy, medium, hard): ");
        String difficulty = scanner.nextLine().toLowerCase(); // Case-insensitive

        boolean gameOver = false;
        int guessCount = 0;
        int score = 0;

        while (!gameOver && guessCount < maxAttempts) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();

            guessCount++;

            if (guess < 1 || guess > 100) {
                System.out.println("Invalid guess. Please enter a number between 1 and 100.");
                continue;
            }

            if (guess == randomNumber) {
                gameOver = true;
                System.out.println("Congratulations! You guessed the number in " + guessCount + " tries.");

                // Calculate score based on attempts and difficulty
                int baseScore = 100;
                int bonus = 0;
                switch (difficulty) {
                    case "easy":
                        bonus = 20;
                        break;
                    case "medium":
                        bonus = 10;
                        break;
                    case "hard":
                        bonus = 5;
                        break;
                }
                score = baseScore + bonus - guessCount * 5; // Penalty for more attempts

                System.out.println("Your score is: " + score);
            } else if (guess < randomNumber) {
                System.out.println("Your guess is too low. Try again!");

                // Provide hints based on difficulty
                if (difficulty.equals("easy") || difficulty.equals("medium")) {
                    System.out.println("Hint: The number is higher than " + guess);
                }
            } else {
                System.out.println("Your guess is too high. Try again!");

                // Provide hints based on difficulty
                if (difficulty.equals("easy") || difficulty.equals("medium")) {
                    System.out.println("Hint: The number is lower than " + guess);
                }
            }
        }

        if (!gameOver) {
            System.out.println("You ran out of attempts. The number was: " + randomNumber);
        }

        // Ask to play again
        System.out.print("Do you want to play again? (y/n): ");
        String answer = scanner.nextLine().toLowerCase();
        if (answer.equals("y")) {
            main(args); // Restart the game
        }

        scanner.close();
    }
}