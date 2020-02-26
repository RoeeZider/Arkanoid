package game.levels;

import game.LevelInformation;
import geometry.Point;
import geometry.Velocity;
import objects.Block;
import objects.sprite.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Roee Zider.
 * The type Level 3.
 */
public class Level3 implements LevelInformation {
    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 800;

    /**
     * The constant BLOCK_WIDTH.
     */
    public static final int BLOCK_WIDTH = 55;
    /**
     * The constant BLOCK_HEIGHT.
     */
    public static final int BLOCK_HEIGHT = 25;
    /**
     * The constant Y_LOCATION.
     */
    public static final int Y_LOCATION = 100;
    public static final int SPEED = 5;
    public static final int ANGLE = 30;
    public static final int NUM_BALLS = 2;
    public static final int PADDLE_SPEED = 7;
    public static final int PADDLE_WIDTH = 120;
    public static final int NUM_ROWS = 6;
    public static final int NUM_BLOCKS = 40;

    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vList = new ArrayList<>(numberOfBalls());
        vList.add(Velocity.fromAngleAndSpeed(ANGLE, SPEED));
        vList.add(Velocity.fromAngleAndSpeed(-ANGLE, SPEED));
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Background3();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        Color color;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 1; j < (2 * NUM_ROWS) - i; j++) {
                color = getColor(i);
                Block block = new Block(new geometry.Rectangle(new Point(WIDTH - BLOCK_HEIGHT - (BLOCK_WIDTH * j),
                        Y_LOCATION + (BLOCK_HEIGHT * i)), BLOCK_WIDTH, BLOCK_HEIGHT), color);

                block.setPointsOfHit(1);
                blocks.add(block);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_BLOCKS;
    }

    @Override
    public Color getColor(int index) {
        if (index == 0) {
            return new Color(255, 0, 153);
        }
        if (index == 1) {
            return new Color(0, 220, 255);
        }
        if (index == 2) {
            return new Color(255, 255, 153);
        }
        if (index == 3) {
            return new Color(0, 255, 153);
        }
        if (index == 4) {
            return new Color(255, 102, 102);
        }
        return Color.WHITE;
    }
}
