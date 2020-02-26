package game.levels;

import biuoop.DrawSurface;
import objects.sprite.Sprite;

import java.awt.Color;
import java.awt.Image;

/**
 * The type Background.
 */
public class Background implements Sprite {

    private Image image;
    private Color color;

    /**
     * Instantiates a new Background.
     *
     * @param color the color
     */
    public Background(Color color) {
        this.color = color;
        this.image = null;
    }

    /**
     * Instantiates a new Background.
     *
     * @param image the image
     */
    public Background(Image image) {
        this.color = null;
        this.image = image;
    }


    @Override
    public void drawOn(DrawSurface d) {
        if (this.image == null) {
            d.setColor(this.color);
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
            d.setColor(Color.white);
            d.fillRectangle(0, 0, d.getWidth(), 25);
        } else {
            d.drawImage(0, 0, this.image);
        }
        d.setColor(Color.white);
        d.fillRectangle(0, 0, d.getWidth(), 25);
    }

    @Override
    public void timePassed() {

    }
}
