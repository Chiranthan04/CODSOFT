import java.util.Random;
import java.util.Scanner;
public class RandomNumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int min = 1;
        int max = 100;
        int maxAttempts = 10;
        int totalScore = 0;
        boolean playAgain;

        do {
            int randomNumber = random.nextInt((max - min) + 1) + min;
            int attempts = 0;
            boolean correctGuess = false;

            System.out.println("Guess the number between " + min + " and " + max + ":");

            while (attempts < maxAttempts && !correctGuess) {
                System.out.print("Attempt " + (attempts + 1) + ": ");

                if (scanner.hasNextInt()) {
                    int userGuess = scanner.nextInt();
                    attempts++;

                    if (userGuess < randomNumber) {
                        System.out.println("Your guess is too low. Try again.");
                    } else if (userGuess > randomNumber) {
                        System.out.println("Your guess is too high. Try again.");
                    } else {
                        System.out.println("Congratulations! You guessed the correct number: " + randomNumber);
                        correctGuess = true;
                        totalScore += (maxAttempts - attempts + 1);
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next();
                }
            }

            if (!correctGuess) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
            }

            System.out.println("Your current score is: " + totalScore);

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thank you for playing! Your final score is: " + totalScore);

        scanner.close();
    }
}
