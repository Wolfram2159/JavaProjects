package sample.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainController {

    @FXML
    public void initialize() {
        System.out.println("first");
    }
    public void onClose(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void onAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("Author: Jakub Podsiadło");
        alert.show();
    }

    public void onClick(MouseEvent mouseEvent) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/second_stage.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Adding task");
            stage.setScene(new Scene(root, 600,400));
            stage.show();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
