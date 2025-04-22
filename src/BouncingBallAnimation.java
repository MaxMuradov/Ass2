import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * Class BouncingBall that animates single ball.
 */
public class BouncingBallAnimation {

    private static final int BALL_RADIUS = 10;
    private static final int OUTSIDE_TYPE = 1; //Out of bounds for frames, here no use.
    public static final int WIDTH_GUI = 900;
    public static final int HEIGHT_GUI = 600;
    private int[] num;

    /**
     * Constructor.
     * @param args string input from user
     */
    BouncingBallAnimation(String[] args) {
        this.num = parseArgsToInt(args);
    }

    /**
     * Converts string to int array.
     * @param args
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
     * Main drawing function.
     */
    public void drawAnimation() {
        GUI gui = new GUI("Bouncing Ball Animation", WIDTH_GUI, HEIGHT_GUI);
        Sleeper sleeper = new Sleeper();
        // Ball is always located in GUI bounds, even if user tries to put the ball outbound.
        if (num[0] >= WIDTH_GUI - BALL_RADIUS) {
            num[0] = WIDTH_GUI - BALL_RADIUS - 1;
        } else if (num[0] <= BALL_RADIUS) {
            num[0] = BALL_RADIUS + 1;
        }
        if (num[1] >= HEIGHT_GUI - BALL_RADIUS) {
            num[1] = HEIGHT_GUI - BALL_RADIUS - 1;
        } else if (num[1] <= BALL_RADIUS) {
            num[1] = BALL_RADIUS + 1;
        }
        Ball ball = new Ball(num[0], num[1], BALL_RADIUS, java.awt.Color.BLACK, OUTSIDE_TYPE);

        // Set velocity from angle and speed
        Velocity velocity = Velocity.fromAngleAndSpeed(num[2], num[3]);
        ball.setVelocity(velocity);

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            ball.moveOneStep(d);
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * Main that creates a ball and starts the animation.
     * @param args x y angle speed
     */
    public static void main(String[] args) {
        BouncingBallAnimation animation = new BouncingBallAnimation(args);
        animation.drawAnimation();
    }
}

