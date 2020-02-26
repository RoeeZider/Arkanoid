package game.levels;

import biuoop.DrawSurface;
import objects.sprite.Sprite;
import java.awt.Color;

/**
 * @author Roee Zider.
 * The type Background 2.
 */
public class Background2 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, d.getWidth(), 25);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        d.setColor(Color.black);
        d.drawRectangle(0, 0, d.getWidth(), 25);

        d.setColor(new Color(255, 240, 100));
        for (int i = 0; i <= 100; i++) {
            d.drawLine(220, 185, i * 10, 350);
        }
        d.setColor(new Color(255, 255, 200));
        d.fillCircle(220, 185, 80);
        d.setColor(new Color(255, 255, 100));
        d.fillCircle(220, 185, 70);
        d.setColor(new Color(255, 240, 100));
        d.fillCircle(220, 185, 60);

    }

    @Override
    public void timePassed() {

    }
}
