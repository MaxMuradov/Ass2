import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * MultipleBouncingBallsAnimation class that animates multipleballs with border check.
 */
public class MultipleBouncingBallsAnimation {

    private static final int OUTSIDE_TYPE = 1;
    public static final int WIDTH_GUI = 900;
    public static final int HEIGHT_GUI = 600;
    private GUI gui = new GUI("GUUUUUIII", WIDTH_GUI, HEIGHT_GUI);
    private Ball[] myBalls;
    private Color[] colors = {Color.BLACK, Color.RED, Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA,
            Color.PINK, Color.WHITE, Color.ORANGE, Color.LIGHT_GRAY, Color.YELLOW};
    private Random rand = new Random();

    /**
     * Class Constructor.
     * @param args user input
     */
    public MultipleBouncingBallsAnimation(String[] args) {
        int[] radius = parseArgsToInt(args);
        myBalls = new Ball[radius.length];

        for (int i = 0; i < radius.length; ++i) {
            int r = radius[i];
            double x = rand.nextDouble(gui.getDrawSurface().getWidth() - 2 * r) + r;
            double y = rand.nextDouble(gui.getDrawSurface().getHeight() - 2 * r) + r;
            Color color = colors[i % colors.length];
            Velocity v = new Velocity(Math.sqrt(50.0 / r), Math.sqrt(50.0 / r));
            myBalls[i] = new Ball(new Point(x, y), r, color, OUTSIDE_TYPE);  // start as "outside"
            myBalls[i].setVelocity(v);
        }
    }

    /**
     * transfer from string to int array.
     * @param args string
     * @return int array
     */
    public static int[] parseArgsToInt(String[] args) {
        int[] numbers = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            numbers[i] = Integer.parseInt(args[i]);
        }
        return numbers;
    }

    /**
     * Main animation function.
     */
    public void drawAnimationOfMyBalls() {
        Sleeper sleeper = new Sleeper();

        while (true) {
            DrawSurface d = gui.getDrawSurface();

            for (Ball myBall : myBalls) {
                myBall.moveOneStep(d);
                myBall.drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * Start func (main).
     * @param args user input
     */
    public static void main(String[] args) {
        MultipleBouncingBallsAnimation e = new MultipleBouncingBallsAnimation(args);
        e.drawAnimationOfMyBalls();
    }
}
