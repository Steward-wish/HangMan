package HangMan;
import java.util.Random;


// Loads words for Hangman game.

public class WordLoader {
    private static final String[] WORDS = {
            "HAIYAA", "JAVA", "WAHAHA", "WALLAWE", "HANGMAN"
    };
    private final Random random = new Random();

    public String selectRandomWord() {
        return WORDS[random.nextInt(WORDS.length)];
    }
}