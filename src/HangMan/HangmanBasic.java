package HangMan;

// Purpose: Main entry point. Coordinates Hangman game flow.
// Connects to: WordLoader (word source), GameLogic (rules), GameUI (interaction).
public class HangmanBasic {
    public static void main(String[] args) {
        WordLoader loader = new WordLoader(); // load random word
        String wordToGuess = loader.selectRandomWord();

        GameLogic game = new GameLogic(wordToGuess); // rules engine
        GameUI ui = new GameUI(); // user interface

        ui.displayWelcome(); // show welcome

        // Purpose: Main game loop controlling play until game ends.
        while (!game.isGameOver()) {
            ui.displayGameState(game); // show current state
            char guess = ui.getGuessFromUser(); // get user input
            game.guessLetter(Character.toUpperCase(guess));
        }
        ui.displayResult(game); // show final result
    }
}
