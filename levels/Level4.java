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
 * The type Level 4.
 */
public class Level4 implements LevelInformation {
    public static final int Y_LOCATION = 150;
    public static final int BLOCK_HEIGHT = 25;
    public static final int BLOCK_WIDTH = 50;
    public static final int ANGLE = 4;
    public static final int NUM_BALLS = 3;
    public static final int PADDLE_SPEED = 6;
    public static final int PADDLE_WIDTH = 100;
    public static final int NUM_ROWS = 7;
    public static final int NUM_BLOCKS = 105;

    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vList = new ArrayList<>(numberOfBalls());
        vList.add(new Velocity(-ANGLE, -2));
        vList.add(new Velocity(ANGLE, -2));
        vList.add(new Velocity((ANGLE - 3), -3));
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
        return "final four";
    }

    @Override
    public Sprite getBackground() {
        return new Background4();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        Color color;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < (2 * NUM_ROWS) + 1; j++) {
                color = getColor(i);
                Block block = new Block(new Rectangle(new Point(BLOCK_HEIGHT + (BLOCK_WIDTH * j),
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
            return new Color(0, 255, 255);
        }
        if (index == 1) {
            return new Color(139, 220, 154);
        }
        if (index == 2) {
            return new Color(255, 102, 113);
        }
        if (index == 3) {
            return new Color(255, 255, 153);
        }
        if (index == 4) {
            return new Color(255, 102, 0);
        }
        if (index == 5) {
            return new Color(150, 150, 200);
        }

        return new Color(250, 167, 203);
    }
}
