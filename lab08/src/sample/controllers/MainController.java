package sample.controllers;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Date;
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
import sample.tools.BelongsEnum;
import sample.tools.ListElement;
import sample.tools.PriorityEnum;


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
                            secondController.setAddTaskCallback((listElement, index) -> {
                                to_do_list.getItems().remove(index);
                                listElement.setBelongs(BelongsEnum.toDo);
                                to_do_list.getItems().add(index, listElement);
                            });
                            stage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    MenuItem delete = new MenuItem("Delete");
                    delete.setOnAction(action -> {
                        if ((item.getIndex() + 1) != to_do_list.getItems().size()) {
                            for (int i = item.getIndex() + 1; i < to_do_list.getItems().size(); i++) {
                                ListElement listElement = (ListElement) to_do_list.getItems().get(i);
                                listElement.setIndex(i-1);
                            }
                        }
                        to_do_list.getItems().remove(item.getIndex());
                    });
                    contextMenu.getItems().addAll(edit, delete);
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
                            secondController.setAddTaskCallback((listElement, index) -> {
                                in_progress_list.getItems().remove(index);
                                listElement.setBelongs(BelongsEnum.inProgress);
                                in_progress_list.getItems().add(index, listElement);
                            });
                            stage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    MenuItem delete = new MenuItem("Delete");
                    delete.setOnAction(action -> {
                        if ((item.getIndex() + 1) != in_progress_list.getItems().size()) {
                            for (int i = item.getIndex() + 1; i < in_progress_list.getItems().size(); i++) {
                                ListElement listElement = (ListElement) in_progress_list.getItems().get(i);
                                listElement.setIndex(i-1);
                            }
                        }
                        in_progress_list.getItems().remove(item.getIndex());
                    });
                    contextMenu.getItems().addAll(edit, delete);
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
                            secondController.setAddTaskCallback((listElement, index) -> {
                                done_list.getItems().remove(index);
                                listElement.setBelongs(BelongsEnum.done);
                                done_list.getItems().add(index, listElement);
                            });
                            stage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    MenuItem delete = new MenuItem("Delete");
                    delete.setOnAction(action -> {
                        if ((item.getIndex() + 1) != done_list.getItems().size()) {
                            for (int i = item.getIndex() + 1; i < done_list.getItems().size(); i++) {
                                ListElement listElement = (ListElement) done_list.getItems().get(i);
                                listElement.setIndex(i-1);
                            }
                        }
                        done_list.getItems().remove(item.getIndex());
                    });
                    contextMenu.getItems().addAll(edit, delete);
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
            secondController.setAddTaskCallback((listElement, index) -> {
                listElement.setBelongs(BelongsEnum.toDo);
                to_do_list.getItems().add(index, listElement);
            });
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void to_do_right(MouseEvent mouseEvent) {
        ListElement listElement = (ListElement) to_do_list.getItems().get(index_to_do);
        if ((listElement.getIndex() + 1) != to_do_list.getItems().size()) {
            for (int i = listElement.getIndex() + 1; i < to_do_list.getItems().size(); i++) {
                ListElement element = (ListElement) to_do_list.getItems().get(i);
                element.setIndex(i-1);
            }
        }
        to_do_list.getItems().remove(index_to_do);
        listElement.setIndex(in_progress_list.getItems().size());
        listElement.setBelongs(BelongsEnum.inProgress);
        in_progress_list.getItems().add(in_progress_list.getItems().size(), listElement);
    }

    public void in_progress_left(MouseEvent mouseEvent) {
        ListElement listElement = (ListElement) in_progress_list.getItems().get(index_in_progress);
        if ((listElement.getIndex() + 1) != in_progress_list.getItems().size()) {
            for (int i = listElement.getIndex() + 1; i < in_progress_list.getItems().size(); i++) {
                ListElement element = (ListElement) in_progress_list.getItems().get(i);
                element.setIndex(i-1);
            }
        }
        in_progress_list.getItems().remove(index_in_progress);
        listElement.setIndex(to_do_list.getItems().size());
        listElement.setBelongs(BelongsEnum.toDo);
        to_do_list.getItems().add(to_do_list.getItems().size(), listElement);
    }

    public void in_progress_right(MouseEvent mouseEvent) {
        ListElement listElement = (ListElement) in_progress_list.getItems().get(index_in_progress);
        if ((listElement.getIndex() + 1) != in_progress_list.getItems().size()) {
            for (int i = listElement.getIndex() + 1; i < in_progress_list.getItems().size(); i++) {
                ListElement element = (ListElement) in_progress_list.getItems().get(i);
                element.setIndex(i-1);
            }
        }
        in_progress_list.getItems().remove(index_in_progress);
        listElement.setIndex(done_list.getItems().size());
        listElement.setBelongs(BelongsEnum.done);
        done_list.getItems().add(done_list.getItems().size(), listElement);
    }

    public void done_left(MouseEvent mouseEvent) {
        ListElement listElement = (ListElement) done_list.getItems().get(index_done);
        if ((listElement.getIndex() + 1) != done_list.getItems().size()) {
            for (int i = listElement.getIndex() + 1; i < done_list.getItems().size(); i++) {
                ListElement element = (ListElement) done_list.getItems().get(i);
                element.setIndex(i-1);
            }
        }
        done_list.getItems().remove(index_done);
        listElement.setIndex(in_progress_list.getItems().size());
        listElement.setBelongs(BelongsEnum.inProgress);
        in_progress_list.getItems().add(in_progress_list.getItems().size(), listElement);
    }

    public void onSave(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose file to save");
        File file = fileChooser.showSaveDialog(to_do_list.getScene().getWindow());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            for (ListElement listElement : (List<ListElement>) to_do_list.getItems()) {
                outputStream.writeObject(listElement);
                System.out.println(listElement.getBelongs());
            }
            for (ListElement listElement : (List<ListElement>) in_progress_list.getItems()) {
                outputStream.writeObject(listElement);
                System.out.println(listElement.getBelongs());
            }
            for (ListElement listElement : (List<ListElement>) done_list.getItems()) {
                outputStream.writeObject(listElement);
                System.out.println(listElement.getBelongs());
            }
            outputStream.close();
            fileOutputStream.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saving status");
            alert.setContentText("Saved lists");
            alert.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void onOpen(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose file to open");
        File file = fileChooser.showOpenDialog(to_do_list.getScene().getWindow());
        ObjectInputStream inputStream = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            inputStream = new ObjectInputStream(fileInputStream);
            ListElement listElement = null;
            to_do_list.getItems().clear();
            in_progress_list.getItems().clear();
            done_list.getItems().clear();
            while ((listElement = (ListElement) inputStream.readObject()) != null) {
                switch (listElement.getBelongs()){
                    case toDo:
                        to_do_list.getItems().add(listElement.getIndex(),listElement);
                        break;
                    case inProgress:
                        in_progress_list.getItems().add(listElement.getIndex(),listElement);
                        break;
                    case done:
                        done_list.getItems().add(listElement.getIndex(),listElement);
                        break;
                }
            }
        } catch (EOFException ex) {
            System.out.println("koniec pliku");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                inputStream.close();
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onImport(ActionEvent actionEvent) {

    }

    public void onExport(ActionEvent actionEvent) {

    }
}
