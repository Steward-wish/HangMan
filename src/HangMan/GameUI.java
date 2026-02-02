package HangMan;
import java.util.ArrayList;
import java.util.Scanner;

public class GameUI {
    private final Scanner scanner = new Scanner(System.in);

    public void displayWelcome() {
        System.out.println("===== Welcome to Hangman game! =====");
        System.out.println("------------------------------------");
    }

    public void displayGameState(GameLogic game) {
        drawHangman(game.getIncorrectGuesses());
        System.out.println("Word: " + game.getDisplayWord());
        System.out.println("Incorrect Guesses: " + game.getIncorrectGuesses() + "/" + game.getMaxIncorrectGuesses());
        System.out.println("Used Letters: " + formatGuessedLetters(game.getGuessedLetters()));
        System.out.println("------------------------------------");
    }

    public char getGuessFromUser() {
        System.out.print("Guess a letter: ");
        String input = scanner.nextLine().toUpperCase();

        while (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            System.out.print("In valid input. Guess a letter: ");
            input = scanner.nextLine().toUpperCase();
        }
        return input.charAt(0);
    }

    public void displayResult(GameLogic game) {
        System.out.println("===== Game Over! =====");
        if (game.isWon()) {
            System.out.println("Congratulations, you have WON!");
        } else {
            System.out.println("Sorry, you have LOST!");
            System.out.println("The word was: " + game.getHiddenWord());
            drawHangman(game.getIncorrectGuesses());
        }
    }

    private String formatGuessedLetters(ArrayList<Character> letters) {
        if (letters.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < letters.size(); i++) {
            sb.append(letters.get(i));
            if (i < letters.size() - 1) sb.append(", ");
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
}