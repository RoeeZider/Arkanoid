package game.levels;

import biuoop.DrawSurface;
import objects.sprite.Sprite;

import java.awt.Color;

/**
 * @author Roee Zider.
 * The type Level name indicator.
 */
public class LevelNameIndicator implements Sprite {

    private String name;

    /**
     * Instantiates a new Level name indicator.
     *
     * @param name the name
     */
    public LevelNameIndicator(String name) {
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(550, 20, "Level Name: " + this.name, 12);
    }

    @Override
    public void timePassed() {

    }
}

