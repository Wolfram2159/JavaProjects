package sample.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import sample.Main;
import sample.tools.ListElement;
import sample.tools.PriorityEnum;

public class SecondController {

    public ChoiceBox choiceBox;
    public Button btn_close;
    public TextField textField;
    public DatePicker datePicker;
    public TextArea textArea;

    @FXML
    public void initialize() {
        System.out.println("second");
        choiceBox.getItems().add(PriorityEnum.Low);
        choiceBox.getItems().add(PriorityEnum.Medium);
        choiceBox.getItems().add(PriorityEnum.High);
    }

    public void tranfer(String xd) {
        System.out.println(xd);
        textArea.setText(xd);
    }

    public void onClick(MouseEvent mouseEvent) {
        String description = textField.getText();
        PriorityEnum priority = (PriorityEnum) choiceBox.getValue();
        //System.out.println(priority);
        String date = datePicker.getValue().toString();
        String text = textArea.getText();
        ListElement listElement = new ListElement(description,priority,date,text);
        MainController.getTo_do().getItems().add(listElement);

        Stage stage = (Stage) btn_close.getScene().getWindow();
        stage.close();
    }
}
