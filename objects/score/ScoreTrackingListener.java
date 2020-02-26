package objects.score;

import listeners.HitListener;
import objects.Ball;
import objects.Block;


/**
 * The type Score tracking listener.
 *
 * @author Roee Zider The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {

    /**
     * The constant HIT_BLOCK_SCORE.
     */
    public static final int HIT_BLOCK_SCORE = 5;
    /**
     * The constant REMOVE_BLOCK_SCORE.
     */
    public static final int REMOVE_BLOCK_SCORE = 10;
    //member
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitEvent.
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit = the block
     * @param hitter   = the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(HIT_BLOCK_SCORE);
        if (beingHit.getPointsHit() == 0) {
            this.currentScore.increase(REMOVE_BLOCK_SCORE);

        }
    }
}