package geometry;

import java.util.List;

/**
 * The type Line.
 *
 * @author Roee Zider.
 */
public class Line {
    private Point start, end;
    private double x1, y1, x2, y2;

    /**
     * constructor.
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     * Instantiates a new Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Length double.
     *
     * @return the length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Middle point.
     *
     * @return the point middle point of the line
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;

        return new Point(midX, midY);
    }

    /**
     * Start point.
     *
     * @return start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Is intersecting boolean.
     *
     * @param other the other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {

        if (this.intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * Slope double.
     *
     * @return the slope.
     */
    public double slope() {
        //we want to check if we divide 0.
        double subX = (this.start.getX() - this.end.getX());
        if (subX == 0) {
            return -1;
        }
        return ((this.start.getY() - this.end.getY()) / subX);
    }

    /**
     * Intersection with point.
     *
     * @param other the other line
     * @return // Returns the intersection point if the lines intersect, // and null otherwise.
     */
    public Point intersectionWith(Line other) {
        //this lines are equals
        if (this.start.equals(this.end) && other.start.equals(other.end)) {
            if (this.equals(other)) {
                return this.end;
            } else {
                return null;
            }
            //both lines are parallel to y-axis
        } else if ((this.slope() == -1) && (other.slope() == -1)) {
            return null;
            //this line is parallel to the y axis
        } else if ((this.slope() == -1)) {
            return this.intersectionWithParallel(other);
            //other line is parallel to the y axis
        } else if (other.slope() == -1) {
            return other.intersectionWithParallel(this);
            //this line is a dot
        } else if (this.start.equals(this.end)) {
            return this.intersectionWithDot(other);
            //other line is a dot
        } else if (other.start.equals(other.end)) {
            return other.intersectionWithDot(this);
        }

        return this.pointOfIntersection(other);
    }

    /**
     * Intersection with dot point.
     *
     * @param other the other
     * @return the point of intersection
     */
    public Point intersectionWithDot(Line other) {
        //if other is parallel to y
        if (other.slope() == 0) {
            if (other.xInRange(this.start.getX()) && this.start.getY() == other.start.getY()) {
                return this.start;
            }
            return null;
            //check if the dot in range
        } else if (other.xInRange(this.start.getX()) && other.yInRange(this.start.getY())) {
            return this.start;
        }
        return null;
    }

    /**
     * Intersection with parallel point.
     *
     * @param other the other
     * @return the point of intersection
     */
    public Point intersectionWithParallel(Line other) {
        //if other is a dot
        if (other.start.equals(other.end)) {
            //check if other is in range
            if ((this.start.getX() == other.start.getX()) && this.yInRange(other.start.getY())) {
                return other.start;
            }
            return null;
        }
        //equation of linear  line
        double y = other.slope() * (this.start.getX() - other.start.getX()) + other.start.getY();
        //check if the (y) in range
        if (other.yInRange(y) && this.yInRange(y)) {
            return new Point(this.start.getX(), y);
        }
        return null;
    }


    /**
     * Point of intersection point.
     *
     * @param other the other
     * @return the point of intersection
     */
    public Point pointOfIntersection(Line other) {
        double m1 = this.slope();
        double m2 = other.slope();
        //if the lines are parallel
        if (m1 == m2) {
            return null;
        }
        //point of intersection
        double xStart = this.start.getX();
        double yStart = this.start.getY();
        double xEnd = other.start.getX();
        double yEnd = other.start.getY();

        double x = ((m1 * xStart) - (m2 * xEnd) - yStart + yEnd) / (m1 - m2);
        double y = (m1 * x) - (m1 * xStart) + yStart;

        //check if the point in range of the lines
        if (this.xInRange(x) && other.xInRange(x)) {
            return new Point(x, y);
        }
        return null;
    }

    /**
     * In range boolean.
     *
     * @param coordinate the coordinate
     * @return true if the y in range, false otherwise
     */
    public boolean yInRange(double coordinate) {
        if (this.minY() <= coordinate && coordinate <= this.maxY()) {
            return true;
        }
        return false;
    }

    /**
     * X in range boolean.
     *
     * @param coordinate the coordinate
     * @return true if the x in range, false otherwise
     */
    public boolean xInRange(double coordinate) {
        if (this.minX() <= coordinate && coordinate <= this.maxX()) {
            return true;
        }
        return false;
    }

    /**
     * Min x double.
     *
     * @return the min x of a line
     */
    public double minX() {
        if (this.start.getX() < this.end.getX()) {
            return this.start.getX();
        }
        return this.end.getX();
    }

    /**
     * Max x double.
     *
     * @return the max x of a line
     */
    public double maxX() {
        if (this.start.getX() > this.end.getX()) {
            return this.start.getX();
        }
        return this.end.getX();
    }

    /**
     * Min y double.
     *
     * @return the min y of a line
     */
    public double minY() {
        if (this.start.getY() < this.end.getY()) {
            return this.start.getY();
        }
        return this.end.getY();
    }

    /**
     * Max y double.
     *
     * @return the max y of a line
     */
    public double maxY() {
        if (this.start.getY() > this.end.getY()) {
            return this.start.getY();
        }
        return this.end.getY();
    }


    /**
     * Equals boolean.
     *
     * @param other the other
     * @return return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if ((this.start.getX() == other.start.getX()) && (this.end.getY() == other.end.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the point
     */
// If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> listPoints = rect.intersectionPoints(this);
        //if there isn't point
        if (listPoints.isEmpty()) {
            return null;
        }
        //for only 1 point
        int length = listPoints.size();
        Point point = listPoints.get(0);
        if (length == 1) {
            return point;
        }
        //return the closest intersection
        for (int i = 0; i < length - 1; i++) {
            if (this.start.distance(listPoints.get(i + 1)) <= this.start.distance(point)) {
                point = listPoints.get(i + 1);
            }
        }
        return point;
    }
}
