package sample;

import javafx.event.ActionEvent;
import sample.graph.GraphFactory;
import sample.moviesRepository.ApiMoviesRepository;
import sample.moviesRepository.MoviesRepository;

public class Controller {

    public void onClick(ActionEvent actionEvent) {
        MoviesRepository repository = new ApiMoviesRepository();
        String startActor = "nm9465752";
        String stopActor = "nm0000216";

        GraphFactory finder = new GraphFactory(startActor, stopActor, repository);
        finder.makeGraph();
    }
    /*
    Results for 3 people connection
    Actor{id='nm9465752', name='Justyna Brylka'} -> Movie{id='tt7710232', title='Oda do mlodosci'} -> Actor{id='nm1065333', name='Dariusz Gnatowski'} -> Movie{id='tt0110505', title='Miasto prywatne'} -> Actor{id='nm0309168', name='Piotr Gasowski'} -> Movie{id='tt9314974', title='Episode #13.5'} -> Actor{id='nm0180650', name='Sharon Corr'} -> Movie{id='tt0651317', title='Episode #1.1'} -> Actor{id='nm0000216', name='Arnold Schwarzenegger'}
    1:35 - 2:54
     */
}
