package HangMan;

/**
 * Main class to coordinate the Hangman game.
 */
public class HangmanBasic {
    public static void main(String[] args) {
        // Load a random word
        WordLoader loader = new WordLoader();
        String wordToGuess = loader.selectRandomWord();

        // Initialize game logic and UI
        GameLogic game = new GameLogic(wordToGuess);
        GameUI ui = new GameUI();

        // Display welcome message
        ui.displayWelcome();

        // Main game loop
        while (!game.isGameOver()) {
            ui.displayGameState(game); // show current state
            char guess = ui.getGuessFromUser(); // get user input
            game.guessLetter(Character.toUpperCase(guess)); // process guess
        }

        // Display final result
        ui.displayResult(game);
    }
}