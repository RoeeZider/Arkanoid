package listeners;

import game.GameLevel;
import objects.Ball;
import objects.Block;
import objects.score.Counter;

/**
 * The type Ball remover.
 *
 * @author Roee Zider The type Ball remover.
 */
public class BallRemover implements HitListener {
    //members
    private GameLevel game;
    private Counter remainingBalls;


    /**
     * Instantiates a new Ball remover.
     *
     * @param game           the game
     * @param remainingBalls the remaining balls
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
