package game.Animation;

import biuoop.DrawSurface;

/**
 * @author Roee Zider.
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d the drawsurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     *
     * @return the true/false
     */
    boolean shouldStop();
}
