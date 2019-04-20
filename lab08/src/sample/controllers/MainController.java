package sample.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.tools.ListElement;


public class MainController {

    public ListView to_do_list;
    public ListView in_progress_list;
    public ListView done_list;
    public int index_to_do;
    public int index_in_progress;
    public int index_done;
    @FXML
    public void initialize() {
        to_do_list.setCellFactory((Callback<ListView<ListElement>, ListCell<ListElement>>) param -> new ListCell<>() {
            @Override
            protected void updateItem(ListElement item, boolean empty) {
                super.updateItem(item, empty);
                ContextMenu contextMenu = new ContextMenu();
                if (item == null) {
                    setText(null);
                    setStyle("-fx-background-color: #ffffff");
                } else {
                    switch (item.getPriority()) {
                        case Low:
                            setStyle("-fx-background-color: #00FF00");
                            break;
                        case Medium:
                            setStyle("-fx-background-color: #FFFF00");
                            break;
                        case High:
                            setStyle("-fx-background-color: #FF0000");
                            break;
                    }
                    setText(item.toString());
                    index_to_do = item.getIndex();
                    setTooltip(new Tooltip(item.getText()));
                    MenuItem edit = new MenuItem("Edit");
                    edit.setOnAction(action -> {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/second_stage.fxml"));
                            Parent root = fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Editing task");
                            stage.setScene(new Scene(root, 600, 400));
                            SecondController secondController = fxmlLoader.getController();
                            secondController.setIndex(item.getIndex());
                            secondController.transfer(item);
                            secondController.setAddTaskCallback((listElement,index) -> {
                                to_do_list.getItems().remove(index);
                                to_do_list.getItems().add(index, listElement);
                            });
                            stage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    MenuItem delete = new MenuItem("Delete");
                    delete.setOnAction(action -> {
                        to_do_list.getItems().remove(item.getIndex());
                    });
                    contextMenu.getItems().addAll(edit,delete);
                    setContextMenu(contextMenu);
                }
            }
        });
        in_progress_list.setCellFactory((Callback<ListView<ListElement>, ListCell<ListElement>>) param -> new ListCell<>() {
            @Override
            protected void updateItem(ListElement item, boolean empty) {
                super.updateItem(item, empty);
                ContextMenu contextMenu = new ContextMenu();
                if (item == null) {
                    setText(null);
                    setStyle("-fx-background-color: #ffffff");
                } else {
                    switch (item.getPriority()) {
                        case Low:
                            setStyle("-fx-background-color: #00FF00");
                            break;
                        case Medium:
                            setStyle("-fx-background-color: #FFFF00");
                            break;
                        case High:
                            setStyle("-fx-background-color: #FF0000");
                            break;
                    }
                    setText(item.toString());
                    index_in_progress = item.getIndex();
                    setTooltip(new Tooltip(item.getText()));
                    MenuItem edit = new MenuItem("Edit");
                    edit.setOnAction(action -> {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/second_stage.fxml"));
                            Parent root = fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Editing task");
                            stage.setScene(new Scene(root, 600, 400));
                            SecondController secondController = fxmlLoader.getController();
                            secondController.setIndex(item.getIndex());
                            secondController.setAddTaskCallback((listElement,index) -> {
                                in_progress_list.getItems().remove(index);
                                in_progress_list.getItems().add(index, listElement);
                            });
                            stage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    MenuItem delete = new MenuItem("Delete");
                    delete.setOnAction(action -> {
                        in_progress_list.getItems().remove(item.getIndex());
                    });
                    contextMenu.getItems().addAll(edit,delete);
                    setContextMenu(contextMenu);
                }
            }
        });
        done_list.setCellFactory((Callback<ListView<ListElement>, ListCell<ListElement>>) param -> new ListCell<>() {
            @Override
            protected void updateItem(ListElement item, boolean empty) {
                super.updateItem(item, empty);
                ContextMenu contextMenu = new ContextMenu();
                if (item == null) {
                    setText(null);
                    setStyle("-fx-background-color: #ffffff");
                } else {
                    switch (item.getPriority()) {
                        case Low:
                            setStyle("-fx-background-color: #00FF00");
                            break;
                        case Medium:
                            setStyle("-fx-background-color: #FFFF00");
                            break;
                        case High:
                            setStyle("-fx-background-color: #FF0000");
                            break;
                    }
                    setText(item.toString());
                    index_done = item.getIndex();
                    setTooltip(new Tooltip(item.getText()));
                    MenuItem edit = new MenuItem("Edit");
                    edit.setOnAction(action -> {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/second_stage.fxml"));
                            Parent root = fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Editing task");
                            stage.setScene(new Scene(root, 600, 400));
                            SecondController secondController = fxmlLoader.getController();
                            secondController.setIndex(item.getIndex());
                            secondController.setAddTaskCallback((listElement,index) -> {
                                done_list.getItems().remove(index);
                                done_list.getItems().add(index, listElement);
                            });
                            stage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    MenuItem delete = new MenuItem("Delete");
                    delete.setOnAction(action -> {
                        done_list.getItems().remove(item.getIndex());
                    });
                    contextMenu.getItems().addAll(edit,delete);
                    setContextMenu(contextMenu);
                }
            }
        });
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/second_stage.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Adding task");
            stage.setScene(new Scene(root, 600, 400));
            SecondController secondController = fxmlLoader.getController();
            secondController.setIndex(to_do_list.getItems().size());
            secondController.setAddTaskCallback((listElement,index) -> {
                to_do_list.getItems().add(index,listElement);
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void to_do_right(MouseEvent mouseEvent) {
        ListElement listElement = (ListElement) to_do_list.getItems().get(index_to_do);
        to_do_list.getItems().remove(index_to_do);
        listElement.setIndex(in_progress_list.getItems().size());
        in_progress_list.getItems().add(in_progress_list.getItems().size(),listElement);
    }

    public void in_progress_left(MouseEvent mouseEvent) {
        ListElement listElement = (ListElement) in_progress_list.getItems().get(index_in_progress);
        in_progress_list.getItems().remove(index_in_progress);
        listElement.setIndex(to_do_list.getItems().size());
        to_do_list.getItems().add(to_do_list.getItems().size(),listElement);
    }

    public void in_progress_right(MouseEvent mouseEvent) {
        ListElement listElement = (ListElement) in_progress_list.getItems().get(index_in_progress);
        in_progress_list.getItems().remove(index_in_progress);
        listElement.setIndex(done_list.getItems().size());
        done_list.getItems().add(done_list.getItems().size(),listElement);
    }

    public void done_left(MouseEvent mouseEvent) {
        ListElement listElement = (ListElement) done_list.getItems().get(index_done);
        done_list.getItems().remove(index_done);
        listElement.setIndex(in_progress_list.getItems().size());
        in_progress_list.getItems().add(in_progress_list.getItems().size(),listElement);
    }

    public void onSave(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file to save list");
        File file = fileChooser.showOpenDialog(to_do_list.getScene().getWindow());

    }

    public void onOpen(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("xd");
        File file = fileChooser.showSaveDialog(to_do_list.getScene().getWindow());
        System.out.println(file.getAbsolutePath());
        try {
            System.out.println("chujaszek");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            List<ListElement> listElementList = to_do_list.getItems();
            for (ListElement listElement : listElementList) {
                outputStream.writeObject(listElement);
                System.out.println(listElement.getIndex());
            }
            outputStream.close();
            fileOutputStream.close();
            System.out.println("saved");
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void onImport(ActionEvent actionEvent) {

    }

    public void onExport(ActionEvent actionEvent) {

    }
}
