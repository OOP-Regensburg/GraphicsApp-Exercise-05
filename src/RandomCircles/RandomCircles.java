package RandomCircles;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Point;

import java.util.Random;

public class RandomCircles extends GraphicsApp {

    /* Private Konstanten */
    private static final int CANVAS_HEIGHT = 800;
    private static final int CANVAS_WIDTH = 800;
    private static final int FRAME_RATE = 60;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;
    private static final int MAX_CIRCLES = 100;
    private static final int MIN_CIRCLE_WIDTH = 5;
    private static final int MAX_CIRCLE_WIDTH = 50;

    private static final Random rand = new Random();


    /*
     * Die initialize-Methode wird einmalig zum Start des Programms
     * aufgerufen.
     */

    @Override
    public void initialize() {
        setupCanvas();
    }

    /*
     * Die draw-Methode wird so lange wiederholt aufgerufen, bis das Programm
     * beendet wird.
     */

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        drawCircles();
    }

    private void setupCanvas() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        setFrameRate(FRAME_RATE);
    }

    private int getRandomBoundedInt(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private void drawCircles() {
        for (int i = 0; i < MAX_CIRCLES; i++) {
            drawCircle();
        }
    }

    /**
     * Zeichnet einen einzelnen Kreis mit Zufallswerten, erzeugt von getNextColor(), getNextDiameter() und getNextPoint()
     */
    private void drawCircle() {
        Color color = getNextColor();
        int diameter = getNextDiameter();
        Point center = getNextPoint(diameter);

        Circle circle = new Circle(center, diameter, color);
        circle.draw();
    }

    /**
     * Gibt eine zufällige Farbe für einen neuen Kreis zurück
     * @return Eine zufällige Farbe (Color), erzeugt von der getRandomColor()-Methode
     */
    private Color getNextColor() {
        return Colors.getRandomColor();
    }

    /**
     * Gibt einen zufälligen Durchmesser für einen Kreis zurück
     * @return Einen zufälligen Durchmesser (int),  erzeugt von der Random-Instanz mit
     * MIN_CIRCLE_WIDTH und MAX_CIRCLE_WIDTH als untere und obere Grenze
     */
    private int getNextDiameter() {
        return getRandomBoundedInt(MIN_CIRCLE_WIDTH, MAX_CIRCLE_WIDTH);
    }

    /**
     * Gibt einen zufälligen Mittelpunkt für einen Kreis zurück
     * @param diameter Durchmesser des Kreises, wird benötigt um den Kreis innerhalb der Zeichenfläche zu platzieren
     * @return Eine zufällige Position (Point) innerhalb der Zeichenfläche
     */
    private Point getNextPoint(int diameter) {
        int lowerX = diameter / 2;
        int upperX = CANVAS_WIDTH - (diameter / 2);
        int lowerY = diameter / 2;
        int upperY = CANVAS_HEIGHT - (diameter / 2);

        int x = getRandomBoundedInt(lowerX, upperX);
        int y = getRandomBoundedInt(lowerY, upperY);
        return new Point(x, y);
    }
}