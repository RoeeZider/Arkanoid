package geometry;

import java.util.ArrayList;

/**
 * The type Rectangle.
 *
 * @author Roee Zider
 */
public class Rectangle {
    //Members
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * constructor of a rectangle with location and width, height.
     *
     * @param upperLeft - upper left point
     * @param width     - width's rectangle
     * @param height    - height's rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }


    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }


    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }


    /**
     * Gets upper left.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }


    /**
     * Bottom left point.
     *
     * @return the point of bottom left
     */
    public Point bottomLeft() {
        return this.leftLine().end();
    }


    /**
     * Bottom right point.
     *
     * @return the point of bottom right
     */
    public Point bottomRight() {
        return this.botLine().end();
    }


    /**
     * Upper right point.
     *
     * @return the point of upper right
     */
    public Point upperRight() {
        return this.rightLine().start();
    }

    /**
     * Top line line.
     *
     * @return the top line
     */
    public Line topLine() {
        Point p = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        return new Line(this.upperLeft, p);
    }

    /**
     * Left line line.
     *
     * @return the left line
     */
    public Line leftLine() {
        Point p = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        return new Line(this.upperLeft, p);
    }


    /**
     * Bot line line.
     *
     * @return the bottom line
     */
    public Line botLine() {
        Point p1 = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point p2 = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return new Line(p1, p2);
    }

    /**
     * Returns the right line of the rectangle.
     *
     * @return right line
     */
    public Line rightLine() {
        Point p1 = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point p2 = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return new Line(p1, p2);
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line - a line
     * @return - List of intersection points with the line
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        java.util.List<Point> lst = new ArrayList<Point>();
        Point intersection;

        //check intersection with all sides of rectangle
        intersection = (this.rightLine().intersectionWith(line));
        if ((intersection != null) && (!lst.contains(intersection))) {
            lst.add(intersection);
        }
        intersection = (this.leftLine().intersectionWith(line));
        if ((intersection != null) && (!lst.contains(intersection))) {
            lst.add(intersection);
        }
        intersection = (this.topLine().intersectionWith(line));
        if ((intersection != null) && (!lst.contains(intersection))) {
            lst.add(intersection);
        }
        intersection = (this.botLine().intersectionWith(line));
        if ((intersection != null) && (!lst.contains(intersection))) {
            lst.add(intersection);
        }

        return lst;
    }
}
