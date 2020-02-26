package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Animation.Animation;
import game.Animation.CountdownAnimation;
import game.Animation.AnimationRunner;
import game.Animation.KeyPressStoppableAnimation;
import game.Animation.PauseScreen;
import geometry.Point;
import geometry.Rectangle;
import listeners.BallRemover;
import listeners.BlockRemover;
import objects.Ball;
import objects.Block;
import objects.GameEnvironment;
import objects.Paddle;
import objects.collision.Collidable;
import objects.score.LivesIndicator;
import objects.score.ScoreIndicator;
import objects.score.ScoreTrackingListener;
import objects.sprite.Sprite;
import objects.sprite.SpriteCollection;
import objects.score.Counter;

import java.awt.Color;

/**
 * The type Game.
 *
 * @author Roee Zider. The type Game.
 */
public class GameLevel implements Animation {
    /**
     * The constant BLOCK_HEIGHT.
     */
    public static final int BLOCK_HEIGHT = 25;
    public static final int BLOCK_WIDTH = 50;
    /**
     * The constant X_BALL_LOCATION.
     */
    public static final int X_BALL_LOCATION = 400;
    /**
     * The constant Y_BALL_LOCATION.
     */
    public static final int Y_BALL_LOCATION = 500;
    /**
     * The constant RADIUS.
     */
    public static final int RADIUS = 5;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int START = 0;
    public static final int SCORE = 100;
    public static final int SECONDS = 2;
    public static final int COUNT_FROM = 3;

    //members
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private Counter numLives;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * Instantiates a new Game.
     *
     * @param level           the level
     * @param keyboard        the keyboard
     * @param score           the score
     * @param lives           the lives
     * @param animationRunner the animation runner
     * @param remainingBlocks the remaining blocks
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard, Counter score, Counter lives,
                     AnimationRunner animationRunner, Counter remainingBlocks) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = remainingBlocks;
        this.remainingBalls = new Counter();
        this.score = score;
        this.numLives = lives;
        this.runner = animationRunner;
        this.keyboard = keyboard;
        this.levelInformation = level;
        this.running = true;
    }

    /**
     * Add collidable.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize.
     * // Initialize a new game: create the Blocks and Ball (and Paddle)
     * // and add them to the game.
     */
    public void initialize() {
        //add the score and lives to sprite
        this.sprites.addSprite(new ScoreIndicator(score));
        this.sprites.addSprite(new LivesIndicator(numLives));
        this.sprites.addSprite(levelInformation.getBackground());

        ScoreTrackingListener stListener = new ScoreTrackingListener(score);

        for (Block block : levelInformation.blocks()) {
            //listeners
            block.addToGame(this);
            block.addHitListener(new BlockRemover(this, remainingBlocks));
            remainingBlocks.increase(1);
            block.addHitListener(stListener);
        }


        //blocks for the border
        //set "x" hit-point for the border
        Block block = new Block(new Rectangle(new Point(START, BLOCK_HEIGHT), WIDTH, BLOCK_HEIGHT), Color.lightGray);
        block.addToGame(this);
        block.setPointsOfHit(0);
        block = new Block(new Rectangle(new Point(START, BLOCK_WIDTH), BLOCK_HEIGHT
                , HEIGHT), Color.lightGray);
        block.addToGame(this);
        block.setPointsOfHit(0);
        block = new Block(new Rectangle(new Point(WIDTH - BLOCK_HEIGHT, BLOCK_WIDTH),
                BLOCK_HEIGHT, HEIGHT), Color.lightGray);
        block.addToGame(this);
        block.setPointsOfHit(0);

        Block deathRegion = new Block(new Rectangle(new Point(BLOCK_HEIGHT, HEIGHT + BLOCK_HEIGHT),
                WIDTH - BLOCK_WIDTH, BLOCK_HEIGHT), Color.lightGray);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(new BallRemover(this, remainingBalls));
        deathRegion.setPointsOfHit(-1);
    }

    /**
     * Create balls on top of paddle.
     */
    public void createBallsOnTopOfPaddle() {
        //balls for the game
        Ball[] balls = new Ball[levelInformation.numberOfBalls()];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(new Point(X_BALL_LOCATION + (10 * i), Y_BALL_LOCATION), RADIUS, Color.white);
            balls[i].setVelocity(levelInformation.initialBallVelocities().get(i).getDx(),
                    levelInformation.initialBallVelocities().get(i).getDy());
            balls[i].setGameEnvironment(environment);
            balls[i].addToGame(this);
            remainingBalls.increase(1);
        }

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous playOneTurn method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(
                    this.keyboard, this.keyboard.SPACE_KEY, new PauseScreen()));
        }

        if (remainingBalls.getValue() == 0) {
            this.running = false;
            this.numLives.decrease(1);
        }
        //if all blocks removed . extra 100 points
        if (remainingBlocks.getValue() == 0) {
            score.increase(SCORE);
            this.running = false;
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

    }

    /**
     * playOneTurn.
     * playOneTurn - create balls and paddles , for the game
     */
    public void playOneTurn() {
        //paddle for the game
        Paddle paddle = new Paddle(levelInformation.paddleSpeed(), levelInformation.paddleWidth(), keyboard);
        paddle.addToGame(this);
        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(SECONDS, COUNT_FROM, this.sprites));
        this.running = true;
        this.runner.run(this);

        paddle.removeFromGame(this);
    }

    /**
     * Run.
     * run the game
     */
    public void run() {
        while (this.numLives.getValue() != 0) {
            playOneTurn();
            numLives.decrease(1);
        }
    }

    /**
     * Remove collidable.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}