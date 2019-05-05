package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.callbacks.SearchActorsCallback;
import sample.entities.Actor;
import sample.moviesRepository.MoviesRepository;
import sample.threads.SearchActors;

public class SearchActorController {
    public ListView firstListView;
    public ListView secondListView;
    public TextField firstText;
    public TextField secondText;
    private SearchActorsCallback searchActorsCallback;
    private MoviesRepository repository;

    public void setSearchActorsCallback(SearchActorsCallback searchActorsCallback) {
        this.searchActorsCallback = searchActorsCallback;
    }

    public void setRepository(MoviesRepository repository) {
        this.repository = repository;
    }

    public void searchFirstActor(ActionEvent actionEvent) {
        firstListView.getItems().clear();
        SearchActors thread = new SearchActors(repository, firstText.getText());
        thread.setOnSucceeded(e -> {
            firstListView.getItems().addAll(thread.getValue());
        });
        new Thread(thread).start();
    }

    public void searchSecondActor(ActionEvent actionEvent) {
        secondListView.getItems().clear();
        SearchActors thread = new SearchActors(repository, secondText.getText());
        thread.setOnSucceeded(e -> {
            secondListView.getItems().addAll(thread.getValue());
        });
        new Thread(thread).start();
    }

    public void SearchActors(ActionEvent actionEvent) {
        Actor first = (Actor) firstListView.getSelectionModel().getSelectedItem();
        Actor second = (Actor) secondListView.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) firstText.getScene().getWindow();
        stage.close();
        searchActorsCallback.searchActors(first, second);
    }
}
