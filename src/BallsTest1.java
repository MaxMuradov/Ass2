import biuoop.GUI;
import biuoop.DrawSurface;

/**
 * Class ballsTest is draw couple balls.
 */
public class BallsTest1 {

    /**
     * simple program to draw couple balls.
     * @param args means nothing here
     */
    public static void main(String[] args) {
       GUI gui = new GUI("Balls Test 1", 900, 600);
        DrawSurface d = gui.getDrawSurface();

        Ball b1 = new Ball(100, 100, 30, java.awt.Color.RED, 1);
        Ball b2 = new Ball(100, 150, 10, java.awt.Color.BLUE, 1);
        Ball b3 = new Ball(80, 249, 50, java.awt.Color.GREEN, 1);

        b1.drawOn(d);
        b2.drawOn(d);
        b3.drawOn(d);

        gui.show(d);
    }
}
