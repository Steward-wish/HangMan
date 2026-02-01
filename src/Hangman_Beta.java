import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman_Beta {
    private static final int MAX_INCORRECT_GUESSES = 6;
    private static final String[] WORDS = {
            "JAVA", "PROGRAMMING", "OBJECT", "ORIENTED", "HANGMAN"
    };

    private final String hiddenWord;
    private String displayWord;
    private final ArrayList<Character> guessedLetters;
    private int incorrectGuesses;
    private final Scanner scanner = new Scanner(System.in);

    public Hangman_Beta() {
        // Pick random word
        Random random = new Random();
        hiddenWord = WORDS[random.nextInt(WORDS.length)];
        displayWord = "_".repeat(hiddenWord.length());
        guessedLetters = new ArrayList<>();
        incorrectGuesses = 0;
    }

    public void play() {
        System.out.println("===== Welcome to Hangman game! =====");
        System.out.println("------------------------------------");

        while (!isGameOver()) {
            displayGameState();
            char guess = getGuessFromUser();
            int result = guessLetter(Character.toUpperCase(guess));

            if (result == -1) {
                System.out.println("You already guessed that letter!");
            } else if (result == 1) {
                System.out.println("Good guess!");
            } else {
                System.out.println("Wrong guess!");
            }
        }

        System.out.println("===== Game Over! =====");
        if (isWon()) {
            System.out.println("Congratulations, you have WON!");
        } else {
            System.out.println("Sorry, you have LOST!");
            System.out.println("The word was: " + hiddenWord);
        }
    }

    private int guessLetter(char guess) {
        if (guessedLetters.contains(guess)) {
            return -1; // already guessed
        }
        guessedLetters.add(guess);

        boolean correct = false;
        StringBuilder newDisplay = new StringBuilder(displayWord);

        for (int i = 0; i < hiddenWord.length(); i++) {
            if (hiddenWord.charAt(i) == guess) {
                newDisplay.setCharAt(i, guess);
                correct = true;
            }
        }

        displayWord = newDisplay.toString();

        if (!correct) {
            incorrectGuesses++;
            return 0; // wrong guess
        }
        return 1; // correct guess
    }

    private void displayGameState() {
        drawHangman(incorrectGuesses);
        System.out.println("Word: " + displayWord);
        System.out.println("Incorrect Guesses: " + incorrectGuesses + "/" + MAX_INCORRECT_GUESSES);
        System.out.println("Used Letters: " + formatGuessedLetters());
        System.out.println("------------------------------------");
    }

    private char getGuessFromUser() {
        System.out.print("Guess a letter: ");
        String input = scanner.nextLine().toUpperCase();

        while (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            System.out.print("Invalid input. Guess a letter: ");
            input = scanner.nextLine().toUpperCase();
        }
        return input.charAt(0);
    }

    private String formatGuessedLetters() {
        if (guessedLetters.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < guessedLetters.size(); i++) {
            sb.append(guessedLetters.get(i));
            if (i < guessedLetters.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    private void drawHangman(int stage) {
        String[] hangmanStages = {
                "+---+\n|   |\n|\n|\n|\n|\n=======\n",
                "+---+\n|   |\n|   O\n|\n|\n|\n=======\n",
                "+---+\n|   |\n|   O\n|   |\n|\n|\n=======\n",
                "+---+\n|   |\n|   O\n|  /|\n|\n|\n=======\n",
                "+---+\n|   |\n|   O\n|  /|\\\n|\n|\n=======\n",
                "+---+\n|   |\n|   O\n|  /|\\\n|  /\n|\n=======\n",
                "+---+\n|   |\n|   O\n|  /|\\\n|  / \\\n|\n=======\n"
        };
        System.out.print(hangmanStages[stage]);
    }

    private boolean isGameOver() {
        return isWon() || isLost();
    }

    private boolean isWon() {
        return displayWord.equals(hiddenWord);
    }

    private boolean isLost() {
        return incorrectGuesses >= MAX_INCORRECT_GUESSES;
    }

    public static void main(String[] args) {
        new Hangman_Beta().play();
    }
}