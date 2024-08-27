import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final int MAX_ATTEMPTS = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalRounds = 0;
        int totalScore = 0;

        boolean playAgain = true;

        while (playAgain) {
            int numberToGuess = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
            int attemptsLeft = MAX_ATTEMPTS;
            boolean hasGuessedCorrectly = false;

            System.out.println("Guess the number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");

            while (attemptsLeft > 0 && !hasGuessedCorrectly) {
                System.out.println("You have " + attemptsLeft + " attempts left.");
                System.out.print("Enter your guess: ");

                int userGuess = scanner.nextInt();

                if (userGuess < MIN_NUMBER || userGuess > MAX_NUMBER) {
                    System.out.println("Please guess a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
                    continue;
                }

                if (userGuess < numberToGuess) {
                    System.out.println("Too low!");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Congratulations! You guessed the number.");
                    hasGuessedCorrectly = true;
                }

                attemptsLeft--;
            }

            if (!hasGuessedCorrectly) {
                System.out.println("Sorry, you're out of attempts. The number was " + numberToGuess + ".");
            } else {
                totalRounds++;
                int score = MAX_ATTEMPTS - attemptsLeft + 1; // Score is based on attempts left
                totalScore += score;
                System.out.println("You scored " + score + " points this round.");
            }

            System.out.println("Total rounds played: " + totalRounds);
            System.out.println("Total score: " + totalScore);

            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next();
            if (response.equalsIgnoreCase("no")) {
                System.out.print("Do you want to know the number you were trying to guess? (yes/no): ");
                String revealResponse = scanner.next();
                if (revealResponse.equalsIgnoreCase("yes")) {
                    System.out.println("The number was " + numberToGuess + ".");
                }
                System.out.println("Well played! Thank you for playing.");
                playAgain = false;
            } else {
                playAgain = response.equalsIgnoreCase("yes");
            }
        }

        scanner.close();
    }
}
