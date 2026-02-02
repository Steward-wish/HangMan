package HangMan;


// Main class to coordinate the Hangman game.

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
        boolean conditon = true;
        while (conditon) {
            if (game.isGameOver()) {
                ;// show current state
                conditon = false;
            }// process guess
            else{ui.displayGameState(game); // show current state
                char guess = ui.getGuessFromUser(); // get user input
                game.guessLetter(Character.toUpperCase(guess));
        }
            // Display final result
        ui.displayResult(game);
    }
}
}