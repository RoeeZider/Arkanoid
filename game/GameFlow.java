package game;

import biuoop.KeyboardSensor;
import game.Animation.AnimationRunner;
import game.Animation.EndScreen;
import game.Animation.HighScoresAnimation;
import game.Animation.KeyPressStoppableAnimation;
import game.levels.LevelNameIndicator;
import objects.score.HighScoresTable;
import objects.score.Counter;
import objects.score.LivesIndicator;
import objects.score.ScoreIndicator;
import objects.score.ScoreInfo;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Game flow.
 *
 * @author Roee Zider. The type Game flow.
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter lives;
    private Counter remainingBlocks;
    private HighScoresTable highScoresTable;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar              the ar
     * @param ks              the ks
     * @param lives           the lives
     * @param highScoresTable the high scores table
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, int lives, HighScoresTable highScoresTable) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
        this.lives = new Counter(lives);
        this.remainingBlocks = new Counter();
        this.highScoresTable = highScoresTable;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     * @throws IOException the io exception
     */
    public void runLevels(List<LevelInformation> levels) throws IOException {
        File f = new File("highscores");
        highScoresTable.load(f);
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.score, this.lives, this.animationRunner, this.remainingBlocks);

            level.initialize();

            //all the indicators
            ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
            level.addSprite(scoreIndicator);
            LivesIndicator livesIndicator = new LivesIndicator(this.lives);
            level.addSprite(livesIndicator);
            LevelNameIndicator levelNameIndicator = new LevelNameIndicator(levelInfo.levelName());
            level.addSprite(levelNameIndicator);

            //the game is being
            while (this.remainingBlocks.getValue() != 0 && this.lives.getValue() != 0) {
                level.playOneTurn();
            }

            // no more lives
            if (this.lives.getValue() == 0) {

                endOfGame(false);
                return;
            }

        }
        endOfGame(true);
    }

    /**
     * end of game.
     *
     * @param isWin boolean.
     */
    private void endOfGame(boolean isWin) {
        if (this.highScoresTable.getRank(this.score.getValue()) != 0) {
            this.highScoresTable.add(new ScoreInfo(this.animationRunner.dialogWindow("Name", "What is your name?", ""),
                    this.score.getValue()));
        }
        try {
            this.highScoresTable.save(new File("highscores"));
        } catch (Exception e) {
            System.out.println("ERROR at save the file");
        }

        this.animationRunner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, this.keyboardSensor.SPACE_KEY,
                new EndScreen(this.score.getValue(), isWin)));
        this.animationRunner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, this.keyboardSensor.SPACE_KEY,
                new HighScoresAnimation(this.highScoresTable)));
    }
}
