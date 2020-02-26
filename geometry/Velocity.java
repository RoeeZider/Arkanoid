package geometry;

/**
 * The type Velocity.
 *
 * @author Roee Zider. The type Velocity. Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * Instantiates a new Velocity.
     * constructor
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Apply to point point.
     * Take a point with position (x,y)
     *
     * @param p the p
     * @return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * This method return the dx of the velocity.
     *
     * @return dx. dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * From angle and speed velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx, dy;
        dx = speed * Math.sin(Math.toRadians(angle));
        dy = speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, -dy);
    }
}
