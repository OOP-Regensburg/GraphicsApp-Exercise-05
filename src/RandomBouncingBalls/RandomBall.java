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

    private void initBall(float canvasWidth, float canvasHeight) {
        float diameter = getRandomBoundedInt(MIN_DIAMETER, MAX_DIAMETER);
        float lowerXLimit = diameter / 2;
        float upperXLimit = canvasWidth - (diameter / 2);
        float lowerYLimit = diameter / 2;
        float upperYLimit = canvasHeight - (diameter / 2);

        float xPos = getRandomBoundedFloat(lowerXLimit, upperXLimit);
        float yPos = getRandomBoundedFloat(lowerYLimit, upperYLimit);
        Color color = Colors.getRandomColor();

        ball = new Circle(xPos, yPos, diameter / 2, color);
    }

    private int getRandomBoundedInt(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private float getRandomBoundedFloat(float min, float max) {
        float delta = max - min;
        return rand.nextFloat() * delta + min;
    }

    public void update() {
        ball.move(dx, dy);
    }

    public void draw() {
        ball.draw();
    }

    /**
     * Überprüft, ob der Ball mit dem durch die Parameter gegebenen
     * Begrenzungsrahmen kollidiert. Der Ball und der Begrenzungsrahmen
     * nutzen das gleiche Koordinatensystem.
     *
     * @param boundingBoxWidth Breite des Begrenzungsrahmens
     * @param boundingBoxHeight Höhe des Begrenzungsrahmens
     */
    public void checkWallCollision(float boundingBoxWidth, float boundingBoxHeight) {

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
     * Behandelt den Fall, dass der Ball mit dem linken oder rechten Rand kollidiert
     */
    private void handleVerticalBounce() {
        changeColor();
        dx *= -1;
    }

    /**
     * Behandelt den Fall, dass der Ball mit dem oberen oder unteren Rand kollidiert
     */
    private void handleHorizontalBounce() {
        changeColor();
        dy *= -1;
    }

    /**
     * Verändert die Farbe des Balls zufällig
     */
    private void changeColor() {
        Color color = Colors.getRandomColor();
        ball.setColor(color);
    }
}
