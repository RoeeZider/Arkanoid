package game.levels;

import biuoop.DrawSurface;
import objects.sprite.Sprite;
import java.awt.Color;

/**
 * @author Roee Zider.
 * The type Background 4.
 */
public class Background4 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(39, 177, 246));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        d.setColor(Color.white);
        d.fillRectangle(0, 0, d.getWidth(), 25);
        d.setColor(Color.black);
        d.drawRectangle(0, 0, d.getWidth(), 25);

        d.setColor(Color.white);
        for (int i = 0; i <= 10; i++) {
            d.drawLine(120 + (i * 8), 400, 100 + (i * 8), 600);
        }
        d.setColor(new Color(200, 200, 200));
        d.fillCircle(120, 400, 20);
        d.fillCircle(142, 420, 28);
        d.setColor(new Color(220, 220, 220));
        d.fillCircle(150, 389, 23);
        d.setColor(new Color(230, 230, 230));
        d.fillCircle(185, 400, 25);
        d.fillCircle(165, 420, 20);

        for (int i = 0; i <= 10; i++) {
            d.drawLine(540 + (i * 8), 540, 520 + (i * 8), 600);
        }
        d.setColor(new Color(210, 210, 210));
        d.fillCircle(540, 520, 20);
        d.fillCircle(562, 540, 28);
        d.setColor(new Color(200, 200, 200));
        d.fillCircle(567, 528, 23);
        d.setColor(new Color(230, 230, 230));
        d.fillCircle(605, 535, 25);
        d.fillCircle(595, 547, 20);
    }

    @Override
    public void timePassed() {

    }
}
