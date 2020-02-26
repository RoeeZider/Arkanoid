package objects.sprite;

import biuoop.DrawSurface;

/**
 * The interface Sprite.
 *
 * @author Roee Zider The interface Sprite.
 */
public interface Sprite {
    /**
     * Draw on.
     *
     * @param d the surface
     */
// draw the sprite to the screen
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     */
// notify the sprite that time has passed
    void timePassed();
}