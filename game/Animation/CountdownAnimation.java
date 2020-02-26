package game.Animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;

import objects.sprite.SpriteCollection;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from of seconds
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(Color.WHITE.darker());
        //for draw "go"
        if (this.countFrom == 0) {
            d.drawText(280, 350, "GO", 150);
            this.stop = true;
            //for draw the seconds
        } else {
            d.drawText(350, 350, Integer.toString(this.countFrom), 150);
        }
        this.countFrom--;

    }

    @Override
    public boolean shouldStop() {
        Sleeper sleep = new Sleeper();
        sleep.sleepFor((long) (1000 / numOfSeconds));
        return this.stop;
    }
}
