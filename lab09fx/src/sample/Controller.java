package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.entities.Actor;
import sample.moviesRepository.ApiMoviesRepository;
import sample.moviesRepository.MoviesRepository;
import sample.threads.CalculateThread;

public class Controller {

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
        Actor start = repository.searchActor(firstActor.getText());
        Actor stop = repository.searchActor(secondActor.getText());
        firstActor.setText(start.getName());
        secondActor.setText(stop.getName());
        CalculateThread thread = new CalculateThread(start, stop, (text) -> {
            textArea.setText(textArea.getText() + text + "\n");
        }, repository);
        progressBar.progressProperty().bind(thread.progressProperty());
        new Thread(thread).start();
        /*String startActor = "nm9465752";
        String stopActor = "nm0000216";*/
    }

    public void searchById(ActionEvent actionEvent) {
        textArea.setText("");
        MoviesRepository repository = new ApiMoviesRepository();
        Actor start = repository.getActor(firstActor.getText());
        Actor stop = repository.getActor(secondActor.getText());
        firstActor.setText(start.getName());
        secondActor.setText(stop.getName());
        CalculateThread thread = new CalculateThread(start, stop, (text) -> {
            textArea.setText(textArea.getText() + text + "\n");
        }, repository);
        progressBar.progressProperty().bind(thread.progressProperty());
        new Thread(thread).start();
    }
    /*
    Results for 3 people connection
    String startActor = "nm9465752";
    String stopActor = "nm0000216";
    Actor{id='nm9465752', name='Justyna Brylka'} -> Movie{id='tt7710232', title='Oda do mlodosci'} -> Actor{id='nm1065333', name='Dariusz Gnatowski'} -> Movie{id='tt0110505', title='Miasto prywatne'} -> Actor{id='nm0309168', name='Piotr Gasowski'} -> Movie{id='tt9314974', title='Episode #13.5'} -> Actor{id='nm0180650', name='Sharon Corr'} -> Movie{id='tt0651317', title='Episode #1.1'} -> Actor{id='nm0000216', name='Arnold Schwarzenegger'}
    1:35 - 2:54, after repairs 4 minutes :D
    */
}
