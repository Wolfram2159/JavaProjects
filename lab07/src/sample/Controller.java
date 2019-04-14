package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Controller {
    public Canvas canvas;

    @FXML
    public void initialize() {
        System.out.println("second");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillRect(0,0,100,100);
    }
}
