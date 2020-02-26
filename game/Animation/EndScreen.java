package game.Animation;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Roee Zider.
 * The type End screen.
 */
public class EndScreen implements Animation {
    private Boolean win;
    private int score;
    private Boolean stop;

    /**
     * Instantiates a new End screen.
     *
     * @param score the score
     * @param isWin the is win
     */
    public EndScreen(int score, Boolean isWin) {
        this.score = score;
        this.win = isWin;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.gray.brighter());
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        //for wining the game
        if (win) {
            d.setColor(Color.YELLOW);
            d.fillOval(210, 210, 200, 200);

            // draw Eyes
            d.setColor(Color.BLACK);
            d.fillOval(255, 265, 30, 30);
            d.fillOval(335, 265, 30, 30);

            // draw Mouth
            d.fillOval(250, 310, 120, 60);

            // adding smile
            d.setColor(Color.YELLOW);
            d.fillRectangle(250, 310, 120, 30);
            d.fillOval(250, 320, 120, 40);
            //write that the player win
            d.setColor(Color.BLUE);
            d.drawText(200, 100, "You win!", 50);
            d.drawText(200, 550, "Your score is: " + this.score, 50);

        } else {
            //for lose

            d.setColor(Color.YELLOW);
            d.fillOval(250, 150, 350, 320);

            d.setColor(Color.BLACK);
            d.fillOval(300, 220, 90, 100);
            d.fillOval(450, 220, 90, 100);

            d.setColor(Color.WHITE);
            d.drawOval(380, 320, 90, 100);

            d.setColor(Color.GREEN);
            d.setColor(Color.RED);
            d.drawText(200, 100, "Game over", 50);
            d.drawText(200, 550, "Your score is: " + this.score, 50);

        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
