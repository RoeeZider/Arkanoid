package objects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import objects.collision.Collidable;
import objects.sprite.Sprite;

import java.awt.Color;

/**
 * The type Paddle.
 *
 * @author Roee Zider The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 800;
    /**
     * The constant Y_PUDDLE.
     */
    public static final int Y_PUDDLE = 550;
    /**
     * The constant HEIGHT_PUDDLE.
     */
    public static final int HEIGHT_PUDDLE = 25;


    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private Color color;
    private double speed;
    private double width;

    /**
     * Instantiates a new Paddle.
     *
     * @param speed    the speed
     * @param width    the width
     * @param keyboard the keyboard
     */
// public Paddle(GUI gui) {
    //   this.rect = new Rectangle(new Point(X_PUDDLE, Y_PUDDLE), WIDTH_PUDDLE, HEIGHT_PUDDLE);
    // this.color = Color.ORANGE;
    //this.keyboard = gui.getKeyboardSensor();
    //this.speed = SPEED;
    //}
    public Paddle(double speed, double width, KeyboardSensor keyboard) {
        this.width = width;
        this.rect = new Rectangle(new Point((WIDTH - getWidth()) / 2, Y_PUDDLE), width, HEIGHT_PUDDLE);
        this.color = Color.ORANGE;
        this.keyboard = keyboard;
        this.speed = speed;
    }


    /**
     * Move left.
     */
    public void moveLeft() {
        if (rect.getUpperLeft().getX() >= 25 + speed) {
            Point newUpperLeft = new Point(rect.getUpperLeft().getX() - this.speed, rect.getUpperLeft().getY());
            this.rect = new Rectangle(newUpperLeft, rect.getWidth(), rect.getHeight());
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (rect.getUpperLeft().getX() + getWidth() + this.speed <= 775) {
            Point newUpperLeft = new Point(rect.getUpperLeft().getX() + this.speed, rect.getUpperLeft().getY());
            this.rect = new Rectangle(newUpperLeft, rect.getWidth(), rect.getHeight());
        }
    }

    /**
     * time passed.
     * moving the paddle according to the keyboard
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * draw on.
     *
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        // Painting rectangle perimeter
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    /**
     * getCollisionRectangle.
     *
     * @return this Reactangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Hit.
     *
     * @param hitter          the ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity
     */

    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        int x = (int) (collisionPoint.getX() - this.rect.getUpperLeft().getX());
        //divide the paddle to 5 parts
        //part1 will get the size of each part
        int part1 = (int) this.getWidth() / 5;
        if (x >= 0 && x < part1) {
            return Velocity.fromAngleAndSpeed(-60, currentVelocity.getSpeed());
        } else if (x >= part1 && x < 2 * part1) {
            return Velocity.fromAngleAndSpeed(-30, currentVelocity.getSpeed());
        } else if (x >= 2 * part1 && x < 3 * part1) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (x >= 3 * part1 && x < 4 * part1) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        } else if (x >= 4 * part1 && x <= 5 * part1) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }
        return currentVelocity;
    }


    /**
     * Add to game.
     *
     * @param g the game
     */
// Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }
}
