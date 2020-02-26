package game.Animation;

import biuoop.DrawSurface;
import objects.score.HighScoresTable;
import java.awt.Color;

/**
 * @author Roee Zider.
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the high scores table
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(249, 255, 132));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(100, 100, "Rank", 50);
        d.drawText(300, 100, "Name", 50);
        d.drawText(550, 100, "Score", 50);
        d.setColor(new Color(100, 20, 40));
        for (int i = 0; i < scores.size(); i++) {
            d.drawText(100, 200 + (50 * i), Integer.toString(i + 1), 25);
            d.drawText(300, 200 + (50 * i), scores.getHighScores().get(i).getName(), 25);
            d.drawText(550, 200 + (50 * i), Integer.toString(scores.getHighScores().get(i).getScore()), 25);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}