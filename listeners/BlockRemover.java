package listeners;

import game.GameLevel;
import objects.Ball;
import objects.Block;
import objects.score.Counter;

/**
 * The type Block remover.
 *
 * @author Roee Zider The type Block remover.
 */
public class BlockRemover implements HitListener {
    //members
    private GameLevel game;
    private Counter remainingBlocks;


    /**
     * Instantiates a new Block remover.
     *
     * @param game            the game
     * @param remainingBlocks the remaining blocks
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //for remove the block
        if (beingHit.getPointsHit() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
        }
    }
}
