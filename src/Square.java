/**
 * Class square.
 */
public class Square implements Shape {

    public static final int NUM_OF_EDGES = 4;
    private Point leftCorner;
    private double size;
    private Line[] lines = new Line[NUM_OF_EDGES];
    private Velocity v = new Velocity(0, 0);

    /**
     * Constructor of class Square.
     * @param l1 lines 1
     * @param l2 lines 2
     * @param l3 lines 3
     * @param l4 lines 4
     */
    public Square(Line l1, Line l2, Line l3, Line l4) {
        lines[0] = l1;
        lines[1] = l2;
        lines[2] = l3;
        lines[3] = l4;

        leftCorner = l1.intersectionWith(l4);
        size = l1.length();
    }

    /**
     * Getter for Size.
     * @return size
     */
    public int getSize() {
        return (int) size;
    }

    /**
     * Getter for x-coord of left corner.
     * @return x-coord
     */
    public double getX() {
        return leftCorner.getX();
    }

    /**
     * Getter for y-coord of left corner.
     * @return y-coord
     */
    public double getY() {
        return leftCorner.getY();
    }

    /**
     * Getter for velocity.
     * @return velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Setter for velocity.
     * @param v velocity
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Setter for velocity.
     * @param dx x-velocity
     * @param dy y-velocity
     */
    public void setVelocity(double dx, double dy) {
        this.v.setDx(dx);
        this.v.setDy(dy);
    }

    /**
     * Getter for center of square.
     * @return x- coord
     */
    public double getCenterX() {
        Point p = new Line(lines[1].start(), lines[1].end()).intersectionWith(new
                Line(lines[3].start(), lines[3].end()));
        return p.getX();
    }
    /**
     * Getter for center of square.
     * @return y- coord
     */
    public double getCenterY() {
        Point p = new Line(lines[1].start(), lines[1].end()).intersectionWith(new
                Line(lines[3].start(), lines[3].end()));
        return p.getY();
    }
}
