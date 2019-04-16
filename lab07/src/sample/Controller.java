package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller {
    public Canvas canvas;
    public TextField textField;
    public Button start;
    public Button stop;
    public ProgressBar progressBar;
    public Label label;
    private CalcTask task=null;
    @FXML
    public void initialize() {
        label.setText("");
    }

    public void startClick(MouseEvent mouseEvent) {
        if(task!=null){
            task.cancel();
        }
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        task = new CalcTask(canvas, Integer.parseInt(textField.getText()));
        task.setOnSucceeded(e -> {
            label.setText("Integral = " + task.getValue());
        });

        progressBar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
    }
    public void stopClick(MouseEvent mouseEvent) {
        task.cancel();
    }
}
