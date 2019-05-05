package sample.threads;

import javafx.concurrent.Task;
import sample.callbacks.FindConnectionCallback;
import sample.callbacks.TextAreaCallback;
import sample.entities.Actor;
import sample.graph.GraphFactory;
import sample.moviesRepository.MoviesRepository;

public class CalculateThread extends Task<Void> {
    private FindConnectionCallback findConnectionCallback;
    private String start;
    private String stop;
    private TextAreaCallback textAreaCallback;
    private MoviesRepository repository;
    private Actor first;
    private Actor second;

    public CalculateThread(String startActor, String stopActor, TextAreaCallback textAreaCallback, FindConnectionCallback findConnectionCallback, MoviesRepository repository) {
        this.start = startActor;
        this.stop = stopActor;
        this.textAreaCallback = textAreaCallback;
        this.repository = repository;
        this.findConnectionCallback = findConnectionCallback;
        first = null;
        second = null;
    }

    public CalculateThread(Actor first, Actor second, TextAreaCallback textAreaCallback, FindConnectionCallback findConnectionCallback, MoviesRepository repository) {
        this.first = first;
        this.second = second;
        this.textAreaCallback = textAreaCallback;
        this.findConnectionCallback = findConnectionCallback;
        this.repository = repository;
    }

    @Override
    protected Void call() throws Exception {
        if (first == null && second == null) {
            first = repository.getActor(start);
            second = repository.getActor(stop);
        }
        GraphFactory finder = new GraphFactory(first, second, repository);
        finder.setTextAreaCallback(textAreaCallback);
        finder.setFindConnectionCallback(findConnectionCallback);
        finder.setProgressBarCallback((x, y) -> {
            updateProgress(x, y);
        });

        finder.makeGraph();
        return null;
    }
}
