package geometry;

/**
 * The type Point.
 *
 * @author Roee Zider. The type Point.
 */
public class Point {

    private double x;
    private double y;

    /**
     * constructor
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other the other
     * @return return the distance of this point to the other point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2)
                + Math.pow(this.y - other.getY(), 2));
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return eturn true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }
}