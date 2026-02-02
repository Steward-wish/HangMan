package HangMan;

import java.util.ArrayList;

//Handles the rules and state of the Hangman game.
public class GameLogic {
    private static final int MAX_INCORRECT_GUESSES = 6;
    private final String hiddenWord;          // the word to guess
    private String displayWord;               // underscores + revealed letters
    private final ArrayList<Character> guessedLetters; // track guesses
    private int incorrectGuesses;             // count wrong guesses

    public GameLogic(String word) {
        this.hiddenWord = word;
        this.displayWord = "_".repeat(word.length()); // start with underscores
        this.guessedLetters = new ArrayList<>();
        this.incorrectGuesses = 0;
    }
//     Process a guessed letter.
//     Returns true if correct, false if incorrect.

    public boolean guessLetter(char guess) {
        // Prevent duplicate guesses
        if (guessedLetters.contains(guess)) {
            return false;
        }
        guessedLetters.add(guess);

        boolean correct = false;
        StringBuilder newDisplay = new StringBuilder(displayWord);

        // Reveal all positions of the guessed letter
        for (int i = 0; i < hiddenWord.length(); i++) {
            if (hiddenWord.charAt(i) == guess) {
                newDisplay.setCharAt(i, guess);
                correct = true;
            }
        }

        displayWord = newDisplay.toString();

        // Count incorrect guess
        if (!correct) {
            incorrectGuesses++;
        }
        return correct;
    }

    public String getDisplayWord() {
        return displayWord;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    public ArrayList<Character> getGuessedLetters() {
        return guessedLetters;
    }

    public int getIncorrectGuesses() {
        return incorrectGuesses;
    }

    public int getMaxIncorrectGuesses() {
        return MAX_INCORRECT_GUESSES;
    }

    public boolean isGameOver() {
        return isWon() || isLost();
    }

    public boolean isWon() {
        return displayWord.equals(hiddenWord);
    }

    public boolean isLost() {
        return incorrectGuesses >= MAX_INCORRECT_GUESSES;
    }
}