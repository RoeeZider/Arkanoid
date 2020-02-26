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
 * The type Level 2.
 */
public class Level2 implements LevelInformation {
    public static final int Y_LOCATION = 300;
    public static final int BLOCK_HEIGHT = 25;
    public static final int BLOCK_WIDTH = 50;
    public static final int ANGLE = 20;
    public static final int NUM_BALLS = 10;
    public static final int PADDLE_SPEED = 4;
    public static final int PADDLE_WIDTH = 500;
    public static final int NUM_ROWS = 14;
    public static final int NUM_BLOCKS = 15;
    public static final int SPEED = 5;

    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vList = new ArrayList<>(numberOfBalls());
        for (int i = 0; i < numberOfBalls(); i++) {
            if (i <= 4) {
                vList.add(Velocity.fromAngleAndSpeed(ANGLE + (i * (10)), SPEED));
            } else {
                vList.add(Velocity.fromAngleAndSpeed(ANGLE - ((i - 1) * (10)), SPEED));
            }
        }
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
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Background2();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>(1);
        Color color;
        for (int i = 0; i <= NUM_ROWS; i++) {
            color = getColor(i);
            Block block = new Block(new Rectangle(new Point(BLOCK_HEIGHT + (BLOCK_WIDTH * i),
                    Y_LOCATION), BLOCK_WIDTH, BLOCK_HEIGHT), color);
            block.setPointsOfHit(1);
            blocks.add(block);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_BLOCKS;
    }

    @Override
    public Color getColor(int index) {
        if (index <= 1) {
            return new Color(78, 255, 143);
        }
        if (index == 2 || index == 3) {
            return new Color(255, 164, 208);
        }
        if (index == 4 || index == 5) {
            return new Color(51, 204, 255);
        }
        if (index >= 6 && index <= 8) {
            return new Color(78, 255, 143);
        }
        if (index == 9 || index == 10) {
            return new Color(255, 164, 208);
        }
        if (index == 11 || index == 12) {
            return new Color(51, 204, 255);
        }
        return new Color(78, 255, 143);
    }
}
