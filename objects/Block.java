package objects;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;
import objects.collision.Collidable;
import objects.sprite.Sprite;
import geometry.Rectangle;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Block.
 *
 * @author Roee Zider The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rect;
    private java.awt.Color color;
    private int pointsHit;
    private List<HitListener> hitListeners;
    private boolean isBorder;
    private boolean hasStroke;
    private Map<Integer, Color> colorsHit;
    private Map<Integer, Image> imagesHit;


    /**
     * Gets points hit.
     *
     * @return the points hit
     */
    public int getPointsHit() {
        return pointsHit;
    }


    /**
     * Instantiates a new Block.
     *
     * @param rect  the rect
     * @param color the color
     */
    public Block(Rectangle rect, Color color) {
        //this.rect = rect;
        //this.color = color;
        //this.numOfHits = 0;
        //this.hitListeners = new ArrayList<>();
        this.rect = rect;
        this.hitListeners = new ArrayList<HitListener>();
        this.colorsHit = new TreeMap<>();
        this.imagesHit = new TreeMap<>();
        this.color = color;
        this.isBorder = true;
    }

    /**
     * Instantiates a new Block.
     *
     * @param rect      the rect
     * @param colorsHit the colors hit
     * @param imagesHit the images hit
     */
    public Block(Rectangle rect, Map<Integer, Color> colorsHit, Map<Integer, Image> imagesHit) {
        this.rect = rect;
        this.hitListeners = new ArrayList<HitListener>();
        this.colorsHit = new TreeMap<>();
        this.imagesHit = new TreeMap<>();
        this.isBorder = false;
        this.color = null;
        this.colorsHit = colorsHit;
        this.imagesHit = imagesHit;
        this.hasStroke = false;
    }

    /**
     * Sets stroke.
     *
     * @param strokeColor the stroke color
     */
    public void setStroke(Color strokeColor) {
        this.color = strokeColor;
        this.hasStroke = true;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return (int) this.rect.getWidth();
    }

    /**
     * getCollisionRectangle.
     *
     * @return the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Sets color.
     *
     * @param c the color
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Sets points of hit.
     *
     * @param hitNumber the hit number
     */
    public void setPointsOfHit(int hitNumber) {
        this.pointsHit = hitNumber;
    }

    /**
     * notifyHit.
     * make notification of hit
     *
     * @param hitter the ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * hit.
     *
     * @param hitter          the ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity after hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double newDx = 0, newDy = 0;

        //change the number on the block
        this.pointsHit--;
        this.notifyHit(hitter);

        if (collisionPoint.equals(this.rect.getUpperLeft())) {
            // UpperLeft corner
            newDx = Math.abs(currentVelocity.getDx()) * (-1);
            newDy = Math.abs(currentVelocity.getDy()) * (-1);

        } else if (collisionPoint.equals(this.rect.bottomLeft())) {
            // bottomLeft corner
            newDx = Math.abs(currentVelocity.getDx()) * (-1);
            newDy = Math.abs(currentVelocity.getDy());
        } else if (collisionPoint.equals(this.rect.bottomRight())) {
            // bottomRight corner
            newDx = Math.abs(currentVelocity.getDx());
            newDy = Math.abs(currentVelocity.getDy()) * (-1);
        } else if (collisionPoint.equals(this.rect.upperRight())) {
            // upperRight corner
            newDx = Math.abs(currentVelocity.getDx());
            newDy = Math.abs(currentVelocity.getDy());
        }
        //if don't hit the corners
        if (newDx == 0 && newDy == 0) {
            newDx = currentVelocity.getDx();
            newDy = currentVelocity.getDy();
            if ((this.rect.getUpperLeft().getY() == collisionPoint.getY())
                    || (this.rect.bottomLeft().getY() == collisionPoint.getY())) {
                newDy = -(currentVelocity.getDy());
            } else if ((this.rect.getUpperLeft().getX() == collisionPoint.getX())
                    || (this.rect.bottomRight().getX() == collisionPoint.getX())) {
                newDx = -(currentVelocity.getDx());
            }
        }

        return new Velocity(newDx, newDy);
    }

    /**
     * draw on.
     *
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        /* Painting rectangle area
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        // Painting rectangle perimeter
        d.setColor(Color.black);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
*/

        if (this.isBorder) {
            d.setColor(this.color);
            d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    (int) this.rect.getWidth(), (int)
                            this.rect.getHeight());
        } else {
            if (this.colorsHit.containsKey(pointsHit)) {
                d.setColor(this.colorsHit.get(pointsHit));
                d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                        (int) this.rect.getWidth(), (int) this.rect.getHeight());

            } else if (this.imagesHit.containsKey(pointsHit)) {
                d.drawImage((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY()
                        , this.imagesHit.get(pointsHit));
            } else {
                if (this.colorsHit.containsKey(1)) {

                    d.setColor(this.colorsHit.get(1));
                    d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                            (int) this.rect.getWidth(), (int) this.rect.getHeight());

                } else {
                    d.drawImage((int) this.rect.getUpperLeft().getX()
                            , (int) this.rect.getUpperLeft().getY(), this.imagesHit.get(1));
                }
            }
            if (hasStroke) {
                d.setColor(this.color);
                d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                        (int) this.rect.getWidth(), (int) this.rect.getHeight());
            }
        }


        //draw the hit points
        // Integer num = getPointsHit();
        // d.setColor(Color.white);
        //if (num <= 0) {
        //     d.drawText((int) (rect.getUpperLeft().getX() + rect.getWidth() / 2)
        //             , (int) (rect.getUpperLeft().getY() + rect.getHeight() / 2), "x", 13);
        //} else {
        //    d.drawText((int) (rect.getUpperLeft().getX() + rect.getWidth() / 2)
        //            , (int) (rect.getUpperLeft().getY() + rect.getHeight() / 2), num.toString(), 13);
        //}
    }

    /**
     * time passed.
     */
    public void timePassed() {
    }

    /**
     * Add to game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}
