package objects.collision;

import geometry.Point;

/**
 * The type Collision info.
 *
 * @author Roee Zider. The type Collision info.
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor of collisionInfo.
     *
     * @param collisionPoint  - a point
     * @param collisionObject - a Collidable
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return a point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     *
     * @return a Collidable
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}