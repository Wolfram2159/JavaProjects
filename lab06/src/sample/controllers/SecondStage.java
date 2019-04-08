package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SecondStage {

    public ChoiceBox choiceBox;
    public Button btn_close;

    @FXML
    public void initialize() {
        System.out.println("second");
        choiceBox.getItems().add("Low");
        choiceBox.getItems().add("Medium");
        choiceBox.getItems().add("High");
    }

    public void onClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) btn_close.getScene().getWindow();
        stage.close();
    }
}
