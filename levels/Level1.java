package game.levels;

import game.LevelInformation;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import objects.Block;
import objects.sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roee Zider.
 * The type Level 1.
 */
public class Level1 implements LevelInformation {
    public static final int Y_LOCATION = 170;
    public static final int X_LOCATION = 385;
    public static final int BLOCK_HEIGHT = 28;
    public static final int BLOCK_WIDTH = 28;
    public static final int NUM_BALLS = 1;
    public static final int PADDLE_SPEED = 5;
    public static final int PADDLE_WIDTH = 100;
    public static final int NUM_BLOCKS = 1;

    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vList = new ArrayList<>(numberOfBalls());
        vList.add(new Velocity(0, -5));
        return vList;
    }

    @Override
    public int paddleSpeed() {
        return PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Background1();
    }

    @Override
    public List<Block> blocks() {
        List<Block> b = new ArrayList<Block>();
        Block block = new Block(new Rectangle(new Point(X_LOCATION, Y_LOCATION), BLOCK_WIDTH, BLOCK_HEIGHT),
                this.getColor(0));
        block.setPointsOfHit(1);
        b.add(block);
        return b;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_BLOCKS;
    }

    @Override
    public Color getColor(int index) {
        return Color.red;
    }
}
