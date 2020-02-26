package game.levels;

import biuoop.DrawSurface;
import objects.sprite.Sprite;
import java.awt.Color;

/**
 * @author Roee Zider.
 * The type Background 3.
 */
public class Background3 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(new Color(54, 194, 56));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.white);
        d.fillRectangle(0, 0, d.getWidth(), 25);

        d.setColor(Color.black);
        d.drawRectangle(0, 0, d.getWidth(), 25);
        d.setColor(Color.darkGray.brighter().brighter());
        d.fillRectangle(108, 220, 10, 200);
        d.setColor(Color.darkGray);
        d.fillRectangle(100, 400, 27, 50);
        d.setColor(Color.black);
        d.fillRectangle(60, 450, 100, 150);

        d.setColor(new Color(255, 220, 132));
        d.fillCircle(112, 220, 18);
        d.setColor(new Color(255, 88, 58));
        d.fillCircle(112, 220, 13);
        d.setColor(Color.white);
        d.fillCircle(112, 220, 5);

        d.setColor(Color.white);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(69 + (j * 18), 460 + (i * 29), 10, 24);
            }
        }
    }

    @Override
    public void timePassed() {

    }

}
