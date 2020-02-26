package game.Animation;

import biuoop.KeyboardSensor;
import game.GameFlow;
import game.LevelInformation;
import objects.score.HighScoresTable;
import java.io.IOException;
import java.util.List;

/**
 * The type Start new game.
 */
public class StartNewGame implements Task<Void> {
    private List<LevelInformation> listLevels;
    private KeyboardSensor ks;
    private AnimationRunner ar;
    private int lives;
    private HighScoresTable scores;

    /**
     * Instantiates a new Start new game.
     *
     * @param listLevels the list levels
     * @param ks         the ks
     * @param ar         the ar
     * @param lives      the lives
     * @param scores     the scores
     */
    public StartNewGame(List<LevelInformation> listLevels,
                        KeyboardSensor ks,
                        AnimationRunner ar,
                        int lives,
                        HighScoresTable scores) {
        this.listLevels = listLevels;
        this.ks = ks;
        this.ar = ar;
        this.lives = lives;
        this.scores = scores;
    }

    @Override
    public Void run() {
        GameFlow gameFlow = new GameFlow(this.ar, this.ks,
                this.lives, this.scores);
        try {
            gameFlow.runLevels(listLevels);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
