package objects;

import geometry.Line;
import geometry.Point;
import objects.collision.Collidable;
import objects.collision.CollisionInfo;

import java.util.ArrayList;

/**
 * The type Game environment.
 *
 * @author Roee Zider The type Game environment.
 */
public class GameEnvironment {

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    private ArrayList<Collidable> collidables;

    /**
     * Gets collidables.
     *
     * @return the collidables
     */
    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }


    // add the given collidable to the environment.

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
// Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidables.isEmpty()) {
            return null;
        }
        Point collisionPoint = null;
        Collidable collisionObject = null;

        for (int i = 0; i < this.collidables.size() && collisionPoint == null; i++) {
            collisionPoint = trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle());
            collisionObject = this.collidables.get(i);
        }
        //the line doesn't collide with any of the collidables
        if (collisionPoint == null) {
            return null;
        }
        //the closet collision
        for (Collidable c : collidables) {
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null && p.distance(trajectory.start()) <= collisionPoint.distance(trajectory.start())) {
                collisionPoint = p;
                collisionObject = c;
            }
        }
        //Returns the information of the closest object
        return new CollisionInfo(collisionPoint, collisionObject);
    }

}