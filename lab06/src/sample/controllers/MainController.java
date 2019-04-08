package sample.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.tools.ListElement;

public class MainController {

    public ListView to_do_list;
    public ListView in_progress_list;
    public ListView done_list;
    private static ListView to_do;
    private static final String DEFAULT_CONTROL_INNER_BACKGROUND = "derive(-fx-base,80%)";
    private static final String HIGHLIGHTED_CONTROL_INNER_BACKGROUND = "derive(palegreen, 50%)";

    static class ColorRectCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Rectangle rect = new Rectangle(100, 20);
            if (item != null) {
                rect.setFill(Color.web(item));
                setGraphic(rect);
            }
        }
    }

    @FXML
    public void initialize() {
        to_do = to_do_list;
        to_do.setCellFactory(new Callback<ListView<ListElement>, ListCell<ListElement>>() {
            @Override
            public ListCell<ListElement> call(ListView<ListElement> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(ListElement item, boolean empty) {
                        super.updateItem(item, empty);
                        System.out.println(item.getPriority());
                        /*switch(item.getPriority()){
                            case Low:
                                setStyle("-fx-background-color: #00FF00");
                                break;
                            case Medium:
                                setStyle("-fx-background-color: #FFFF00");
                                break;
                            case High:
                                setStyle("-fx-background-color: #FF0000");
                                break;
                        }*/
                        setText(item.toString());


                    }
                };
            }
        });
    }

    public static ListView getTo_do() {
        return to_do;
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
        //to_do_list.getItems().add("xdxd");
    }

    public void onClick(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/second_stage.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Adding task");
            stage.setScene(new Scene(root, 600, 400));
            SecondController secondController = fxmlLoader.getController();
            //secondController.tranfer("xdxdxd");
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
