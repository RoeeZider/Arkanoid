package game.levels;

import biuoop.DrawSurface;
import objects.sprite.Sprite;
import java.awt.Color;

/**
 * @author Roee Zider
 * The type Background 1.
 */
public class Background1 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.white);
        d.fillRectangle(0, 0, d.getWidth(), 25);
        d.setColor(Color.black);
        d.drawRectangle(0, 0, d.getWidth(), 25);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 181, 60);
        d.drawCircle(400, 181, 90);
        d.drawCircle(400, 181, 120);
        d.drawLine(400, 50, 400, 160);
        d.drawLine(400, 210, 400, 320);
        d.drawLine(265, 185, 375, 185);
        d.drawLine(425, 185, 535, 185);
    }

    @Override
    public void timePassed() {

    }
}
