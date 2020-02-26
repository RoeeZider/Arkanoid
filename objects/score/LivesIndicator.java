package objects.score;

import biuoop.DrawSurface;
import objects.sprite.Sprite;

import java.awt.Color;

/**
 * The type Lives indicator.
 *
 * @author Roee Zider The type Lives indicator.
 */
public class LivesIndicator implements Sprite {
    // member
    private Counter lives;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param lives the lives
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.darkGray);
        d.drawText(150, 20, "Lives left: " + this.lives.getValue(), 12);
    }

    @Override
    public void timePassed() {

    }
}
