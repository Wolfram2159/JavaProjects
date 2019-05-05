package sample.threads;

import java.util.List;

import javafx.concurrent.Task;
import sample.entities.Actor;
import sample.moviesRepository.MoviesRepository;

public class SearchActors extends Task<List<Actor>> {
    private MoviesRepository repository;
    private String name;

    public SearchActors(MoviesRepository repository, String name) {
        this.repository = repository;
        this.name = name;
    }

    @Override
    protected List<Actor> call() throws Exception {
        return repository.searchActor(name);
    }
}
