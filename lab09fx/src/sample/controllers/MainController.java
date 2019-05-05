package sample.controllers;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.moviesRepository.ApiMoviesRepository;
import sample.moviesRepository.MoviesRepository;
import sample.threads.CalculateThread;

public class MainController {

    public TextField firstActor;
    public TextField secondActor;
    public TextArea textArea;
    public ProgressBar progressBar;

    @FXML
    public void initialize() {
        textArea.setStyle(".scroll-bar:vertical");
    }

    public void searchByName(ActionEvent actionEvent) {
        textArea.setText("");
        MoviesRepository repository = new ApiMoviesRepository();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layouts/search_actor.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Search Actors");
            stage.setScene(new Scene(root, 600, 400));
            SearchActorController secondController = fxmlLoader.getController();
            secondController.setRepository(repository);
            secondController.setSearchActorsCallback((first, second) -> {
                CalculateThread thread = new CalculateThread(first, second, (text) -> {
                    Platform.runLater(() -> textArea.setText(textArea.getText() + text + "\n"));
                }, (vertices, edges) ->
                        Platform.runLater(() -> {
                            try {
                                FXMLLoader fxmlLoaders = new FXMLLoader(getClass().getResource("../layouts/graph_view.fxml"));
                                Parent roots = fxmlLoaders.load();
                                Stage stages = new Stage();
                                stages.setTitle("Graph Visualization");
                                stages.setScene(new Scene(roots, 600, 600));
                                GraphViewController secondControllers = fxmlLoaders.getController();
                                secondControllers.transfer(vertices, edges);
                                stages.show();
                            } catch (IOException ex) {
                                System.out.println("IOException");
                            }
                        }), repository);
                progressBar.progressProperty().bind(thread.progressProperty());
                new Thread(thread).start();
            });
            stage.show();
        } catch (IOException ex) {
            System.out.println("IOException");
        }
        /*String startActor = "nm9465752";
        String stopActor = "nm0000216";*/
    }

    public void searchById(ActionEvent actionEvent) {
        textArea.setText("");
        MoviesRepository repository = new ApiMoviesRepository();
        String start = firstActor.getText();
        String stop = secondActor.getText();
        CalculateThread thread = new CalculateThread(start, stop, (text) -> {
             Platform.runLater(() -> textArea.setText(textArea.getText() + text + "\n"));
        }, (vertices, edges) ->
                Platform.runLater(() -> {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layouts/graph_view.fxml"));
                        Parent root = fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Graph Visualization");
                        stage.setScene(new Scene(root, 600, 600));
                        GraphViewController secondController = fxmlLoader.getController();
                        secondController.transfer(vertices, edges);
                        stage.show();
                    } catch (IOException ex) {
                        System.out.println("IOException");
                    }
                }), repository);
        progressBar.progressProperty().bind(thread.progressProperty());
        new Thread(thread).start();
    }
    /*
    Results for 3 people connection
    String startActor = "nm9465752";
    String stopActor = "nm0000216";
    Actor{id='nm9465752', name='Justyna Brylka'} -> Movie{id='tt7710232', title='Oda do mlodosci'} ->
    Actor{id='nm1065333', name='Dariusz Gnatowski'} -> Movie{id='tt0110505', title='Miasto prywatne'} ->
    Actor{id='nm0309168', name='Piotr Gasowski'} -> Movie{id='tt9314974', title='Episode #13.5'} ->
    Actor{id='nm0180650', name='Sharon Corr'} -> Movie{id='tt0651317', title='Episode #1.1'} ->
    Actor{id='nm0000216', name='Arnold Schwarzenegger'}
    */
}
