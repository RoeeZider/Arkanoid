package objects;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import objects.collision.CollisionInfo;
import objects.sprite.Sprite;
import java.awt.Color;

/**
 * The type Ball.
 *
 * @author Roee Zider. The type Ball.
 */
public class Ball implements Sprite {
    /**
     * The constant INITIALIZE.
     */
    public static final int NUM_VELOCITY = 2;

    //Members
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity = new Velocity(NUM_VELOCITY, NUM_VELOCITY);
    private int left;
    private int right;
    private int top;
    private int bottom;
    private int x, y;
    private GameEnvironment game;

    /**
     * constructor
     * Instantiates a new Ball.
     *
     * @param center the center
     * @param r      the r
     * @param color  the color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * //constructor
     * Instantiates a new Ball.
     *
     * @param x     the x
     * @param y     the y
     * @param r     the r
     * @param color the color
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
        this.center = new Point((double) x, (double) y);
    }

    /**
     * //constructor
     * Instantiates a new Ball.
     *
     * @param center the center
     * @param r      the r
     * @param color  the color
     * @param left   the left
     * @param right  the right
     * @param top    the top
     * @param bottom the bottom
     */
    public Ball(Point center, int r, java.awt.Color color, int left, int right,
                int top, int bottom) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    /**
     * Sets game environment.
     *
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.game = gameEnvironment;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw on.
     * draw the ball on the given DrawSurface
     *
     * @param surface the surface
     */

    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.getSize());
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.getSize());
    }

    /**
     * Sets velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Move one step.
     */
    public void moveOneStep() {

        Line trajectory = new Line(this.center, this.velocity.applyToPoint(this.center));
        CollisionInfo collisionInfo = this.game.getClosestCollision(trajectory);

        Point futureCenter;
        //if we don't get collision
        if (collisionInfo == null) {
            futureCenter = this.getVelocity().applyToPoint(this.center);

        } else {
            double colX = collisionInfo.collisionPoint().getX();
            double colY = collisionInfo.collisionPoint().getY();
            double upperLeftX = collisionInfo.collisionObject().getCollisionRectangle().getUpperLeft().getX();
            double upperLeftY = collisionInfo.collisionObject().getCollisionRectangle().getUpperLeft().getY();
            double radius = this.r;

            //calculate the next stop
            if (colX > upperLeftX && colX < upperLeftX
                    + collisionInfo.collisionObject().getCollisionRectangle().getWidth()) {
                if (colY == upperLeftY) {
                    colY = (colY - radius);
                } else {
                    colY = (colY + radius);
                }
            } else {
                if (colX == upperLeftX) {
                    colX = (colX - radius);
                } else {
                    colX = (colX + radius);
                }
            }
            futureCenter = new Point(colX, colY);

            Velocity v = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.velocity);
            this.setVelocity(v);

        }
        this.center = futureCenter;
    }

    /**
     * Is exits the border boolean.
     *
     * @param limit the limit
     * @param ball  the ball
     * @return true if the ball is exits the border
     */
    public boolean isExitsTheBorder(double limit, double ball) {
        if (limit > ball) {
            return true;
        }
        return false;
    }

    /**
     * time Passed.
     * Doing the steps in game
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Add to game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param g the game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}

