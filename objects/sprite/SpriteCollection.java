package objects.sprite;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 *
 * @author Roee Zider The type Sprite collection.
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Add sprite.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Notify all time passed.
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        List<Sprite> list = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : list) {
            s.timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param d the surface
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
