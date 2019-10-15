package RandomBouncingBalls;

import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Ellipse;

import java.util.Random;

public class RandomBall {
    private static final int MIN_DIAMETER = 50;
    private static final int MAX_DIAMETER = 100;

    private static final Random rand = new Random();
    private Circle ball;
    private float dx;
    private float dy;

    public RandomBall(int canvasWidth, int canvasHeight, float minVelocity,
                      float maxVelocity) {
        initSpeed(minVelocity, maxVelocity);
        initBall(canvasWidth, canvasHeight);
    }

    private void initSpeed(float minSpeed, float maxSpeed) {
        float differenceSpeed = maxSpeed - minSpeed;
        dx = rand.nextFloat() * differenceSpeed + minSpeed;
        dy = rand.nextFloat() * differenceSpeed + minSpeed;
    }

    private void initBall(int canvasWidth, int canvasHeight) {
        int diameter = getRandomBoundedInt(MIN_DIAMETER, MIN_DIAMETER);
        int lowerXLimit = diameter / 2;
        int upperXLimit = canvasWidth - (diameter / 2);
        int lowerYLimit = diameter / 2;
        int upperYLimit = canvasHeight - (diameter / 2);

        int xPos = getRandomBoundedInt(lowerXLimit, upperXLimit);
        int yPos = getRandomBoundedInt(lowerYLimit, upperYLimit);
        Color color = Colors.getRandomColor();

        ball = new Circle(xPos, yPos, diameter, color);
    }

    private int getRandomBoundedInt(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    public void update() {
        ball.move(dx, dy);
    }

    public void draw() {
        ball.draw();
    }

    /**
     * Checks if the ball is colliding with on of the inner walls of a bounding
     * box, given by the two parameters. Ball and bounding box are using the
     * same coordinate system.
     *
     * @param boundingBoxWidth
     * @param boundingBoxHeight
     */
    public void checkWallCollision(int boundingBoxWidth, int boundingBoxHeight) {

        if (ball.getRightBorder() + Math.abs(dx) >= boundingBoxWidth
                || ball.getLeftBorder() - Math.abs(dx) <= 0) {
            handleVerticalBounce();
        }

        if (ball.getBottomBorder() + Math.abs(dy) >= boundingBoxHeight
                || ball.getTopBorder() - Math.abs(dy) <= 0) {
            handleHorizontalBounce();
        }

    }

    /**
     * Handles the case, that the ball collides with the left or right wall
     */
    private void handleVerticalBounce() {
        changeColor();
        dx *= -1;
    }

    /**
     * Handles the case, that the ball collides with the top or bottom wall
     */
    private void handleHorizontalBounce() {
        changeColor();
        dy *= -1;
    }

    /**
     * Changes the color of the circle to a random value
     */
    private void changeColor() {
        Color color = Colors.getRandomColor();
        ball.setColor(color);
    }
}
