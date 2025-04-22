/**
 * Interface for shapes.
 * Not super useful but for practice.
 */
public interface Shape {
    /**
     * Getter for Velocity.
     * @return velocity
     */
    Velocity getVelocity();

    /**
     * Setter for Velocity.
     * @param v velocity
     */
    void setVelocity(Velocity v);

    /**
     * Setter for Velocity.
     * @param dx x-velocity
     * @param dy y-velocity
     */
    void setVelocity(double dx, double dy);
    /**
     * Getter of center point by x.
     * @return x-coord
     */
    double getCenterX();

    /**
     * Getter of center point by y.
     * @return y-coord
     */
    double getCenterY();
    /**
     * Getter for Size.
     * @return size
     */
    int getSize();
}
