/*
taz: 346975386
Name: Maxim Muradov
 */

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * Class MultipleFramesBouncingBallsAnimation is creating two squares and
 * balls array, then starts animation with checking of the collision.
 */
public class MultipleFramesBouncingBallsAnimation {

    public static final int NUM_SQUARES = 2;
    public static final int WIDTH_GUI = 900;
    public static final int HEIGHT_GUI = 600;
    private GUI gui = new GUI("GUUUUUIII", WIDTH_GUI, HEIGHT_GUI);
    private Square[] frames = new Square[NUM_SQUARES];
    private Color[] colors = {Color.BLACK, Color.RED, Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA,
            Color.PINK, Color.WHITE, Color.ORANGE, Color.LIGHT_GRAY, Color.YELLOW};
    private static Random rand = new Random();
    private Ball[] myBalls;

    /**
     * Constructor of the class.
     * @param args string from terminal input
     */
    MultipleFramesBouncingBallsAnimation(String[] args) {

        //I've planned to change numbers on constants, but it's messy.
        //grey
        Line l1 = new Line(new Point(50, 50), new Point(50, 500));
        Line l2 = new Line(new Point(50, 500), new Point(500, 500));
        Line l3 = new Line(new Point(500, 500), new Point(500, 50));
        Line l4 = new Line(new Point(500, 50), new Point(50, 50));

        frames[0] = new Square(l1, l2, l3, l4);
        //yellow
        Line ll1 = new Line(new Point(450, 450), new Point(450, 600));
        Line ll2 = new Line(new Point(450, 600), new Point(600, 600));
        Line ll3 = new Line(new Point(600, 600), new Point(600, 450));
        Line ll4 = new Line(new Point(600, 450), new Point(450, 450));

        frames[1] = new Square(ll1, ll2, ll3, ll4);


        int[] radius = parseArgsToInt(args);
        // I've made it so the balls are for sure bigger than 10 and smaller than 100 units.
        // 1) So check for collision check that will always work properly.
        // 2) So my algorithm for spawning will take less time, with my tests radius over 100 units
        // takes more than 5 sec to spawn because of limitations by GUI & Squares.
        for (int i = 0; i < radius.length; ++i) {
            radius[i] += 10;
            if (radius[i] > 100) {
                radius[i] = 100;
            }
        }
        this.myBalls = new Ball[radius.length];
        for (int i = 0; i < radius.length; ++i) {
            if (i % 2 == 0) {
                // spawn inside inner frame
                // check if matches description than spawns
                double x, y;
                int b; // stam name bc not very usefull variable
                do {
                    x = rand.nextDouble(450 - 1 - radius[i]) + 50 + 1 + radius[i];
                    y = rand.nextDouble(450 - 1 - radius[i]) + 50 + 1 + radius[i];
                    Ball temp = new Ball(x, y, radius[i], colors[i % colors.length], 1);
                    b = temp.inFrames(frames[0]);
                } while (b != 1);
               myBalls[i] = new Ball(new Point(x, y), radius[i], colors[i % colors.length], -1);
            } else {
                // spawn outside both frames
                // check if matches description than spawns
                double x, y;
                int b, c, d; // stam name bc not very usefull variable
                do {
                    x = rand.nextDouble(900 - radius[i]) + radius[i];
                    y = rand.nextDouble(600 - radius[i]) + radius[i];
                    Ball temp = new Ball(x, y, radius[i], colors[i % colors.length], 1);
                    b = temp.inFrames(frames[0]);
                    c = temp.inFrames(frames[1]);
                    d = temp.inBorder(this.gui.getDrawSurface());
                } while (b != -1 || c != -1 || d != 0);
                myBalls[i] = new Ball(x, y, radius[i], colors[i % colors.length], 1);
            }
            // Randomizing velocity angle, but condition for speed in correlation to the size remains.
            Velocity velocity;
            velocity = Velocity.fromAngleAndSpeed(rand.nextDouble(360.0) + 0.0,  Math.sqrt(50.0 / radius[i]));
            myBalls[i].setVelocity(velocity);
        }
    }

    /**
     * Draws background for gui.
     * @param d our drawsurface
     */
    public void drawBackground(DrawSurface d) {
        d.setColor(Color.GRAY);
        d.fillRectangle(50, 50, 450, 450);
        d.setColor(Color.YELLOW);
        d.fillRectangle(450, 450, 150, 150);
    }

    /**
     * Draws only YellowSquare so it will hide balls.
     * @param d our drawsurface
     */
    public void drawYellowSquare(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillRectangle(450, 450, 150, 150);
    }

    /**
     * Conversion from string to int array.
     * @param args string from terminal input
     * @return int array that consist of numbers from args
     */
    public static int[] parseArgsToInt(String[] args) {
        int[] numbers = new int[args.length]; // Создаём массив нужного размера

        for (int i = 0; i < args.length; i++) {
            numbers[i] = Integer.parseInt(args[i]); // Преобразуем строку в число
        }
        return numbers;
    }

    /**
     * Main drawing function.
     */
    public void drawAnimationOfMyFrameBalls() {
        Sleeper sleeper = new Sleeper();

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            this.drawBackground(d);
            for (Ball myBall : myBalls) {
                if (myBall.getIsOutside() == 1) { //outside
                    myBall.moveOneStepOutFrames(d, this.frames);
                } else { //inside
                    myBall.moveOneStepInFrames(this.frames[0]);
                }
                myBall.drawOn(d);
            }
            //overdraw Yellow square
            this.drawYellowSquare(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * Start func (main).
     * @param args user input
     */
    public static void main(String[] args) {
        MultipleFramesBouncingBallsAnimation e = new MultipleFramesBouncingBallsAnimation(args);
        e.drawAnimationOfMyFrameBalls();
    }
}
