package game.levels;

import game.LevelInformation;
import geometry.Velocity;
import objects.Block;
import objects.sprite.Sprite;

import java.awt.Color;
import java.util.List;

/**
 * The type Level.
 */
public class Level implements LevelInformation {
    private String levelName;
    private Sprite background;
    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private List<Block> blocks;

    /**
     * Instantiates a new Level.
     *
     * @param levelName   the level name
     * @param background  the background
     * @param velocities  the velocities
     * @param paddleSpeed the paddle speed
     * @param paddleWidth the paddle width
     * @param blocks      the blocks
     */
    public Level(String levelName,
                 Sprite background,
                 List<Velocity> velocities,
                 int paddleSpeed,
                 int paddleWidth, List<Block> blocks) {
        this.levelName = levelName;
        this.background = background;
        this.velocities = velocities;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.blocks = blocks;
        this.blocks = blocks;
    }

    @Override
    public int numberOfBalls() {
        return this.velocities.size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }

    @Override
    public Color getColor(int index) {
        //default!!!! maybe to delete
        return Color.BLACK;
    }
}
