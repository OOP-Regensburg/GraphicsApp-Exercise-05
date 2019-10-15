package RandomCircles;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;

public class RandomCircles extends GraphicsApp {

    /* Private constants */
    private static final int CANVAS_HEIGHT = 800;
    private static final int CANVAS_WIDTH = 800;
    private static final int FRAME_RATE = 60;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;

    /*
     * This method is called once when the program is started.
     */

    @Override
    public void initialize() {
        setupCanvas();
    }

    /*
     * This method is called repeatedly while the program is running.
     */

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
    }

    private void setupCanvas() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        setFrameRate(FRAME_RATE);
    }
}