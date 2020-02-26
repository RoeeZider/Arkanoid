package objects.score;

import biuoop.DrawSurface;
import objects.sprite.Sprite;
import java.awt.Color;

/**
 * The type Score indicator.
 *
 * @author Roee Zider The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    //member
    private Counter scores;

    /**
     * Instantiates a new Score indicator.
     *
     * @param scores the scores
     */
    public ScoreIndicator(Counter scores) {
        this.scores = scores;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(360, 20, "Score: " + this.scores.getValue(), 12);
    }

    @Override
    public void timePassed() {

    }
}


