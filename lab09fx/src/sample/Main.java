package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
    //todo:<04:10:22> "chopeY": MoviesRepository
    //todo:<04:10:41> "chopeY": ApiMoviesRepository
    //todo:<04:11:07> "chopeY": ApiMoviesRepository implements MoviesRepository
    //todo:<04:11:52> "chopeY": MockMoviesRepository implements MoviesRepository
    //todo:<04:14:05> "chopeY": FileMoviesRepository implements MoviesRepository
    //todo:<04:14:37> "chopeY": DatabaseMoviesRepository implements MoviesRepository
    //todo:<04:15:34> "chopeY": MoviesRepostiory repository
    //todo:<04:16:09> "chopeY": repository.getMovieInfo(id);

    public static void main(String[] args) {
        launch(args);
    }
}
