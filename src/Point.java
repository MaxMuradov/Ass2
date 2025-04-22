/**
 * class Point.
 */
public class Point {
    /**
     * x coordinate.
     */
    private final double x;
    /**
     * y coordinate.
     */
    private final double y;

    /**
     * constructor.
     * @param x1 x coord
     * @param y1 y coord
     */
    public Point(final double x1, final double y1) {
        this.x = x1;
        this.y = y1;
    }

    /**
     * distance between points calc method.
     * @param other 2-nd point
     * @return distance in double
     */
    public double distance(final Point other) {
        return Math.sqrt(Math.pow(other.getX() - this.x, 2) + Math.pow(other.getY() - this.y, 2));
    }

    /**
     * check if point is on the same coords.
     * @param other 2-nd point
     * @return true if equals
     */
    public boolean equals(final Point other) {
        return (Math.abs(this.x - other.getX()) < 1e-9 && Math.abs(this.y - other.getY()) < 1e-9);
    }

    /**
     * check if point is on the segment.
     * @param other segment
     * @return true if point on the segment
     */
    public boolean onTheLine(final Line other) {
        return this.x <= Math.max(other.start().getX(), other.end().getX())
                && this.x >= Math.min(other.start().getX(), other.end().getX())
                && this.y <= Math.max(other.start().getY(), other.end().getY())
                && this.y >= Math.min(other.start().getY(), other.end().getY());
    }

    /**
     * determines what is the orientation of point to start/end of segment.
     * @param other segment
     * @return 0 if collinear, 1 if clockwise, -1 otherwise
     */
    public int orientation(final Line other) {
        double orientation = ((other.start().getY() - this.y) * (other.end().getX() - other.start().getX()))
                - ((other.start().getX() - this.x) * (other.end().getY() - other.start().getY()));

        if (orientation == 0) {
            return 0;
        } else {
            return orientation > 0 ? 1 : -1;
        }
    }

    /**
     * getter of x coord.
     * @return x coord of point
     */
    public double getX() {
        return this.x;
    }

    /**
     * getter of y coord.
     * @return y coord of point
     */
    public double getY() {
        return this.y;
    }
}

