package sample.threads;

import javafx.concurrent.Task;
import sample.callbacks.FindConnectionCallback;
import sample.callbacks.ProgressBarCallback;
import sample.callbacks.TextAreaCallback;
import sample.entities.Actor;
import sample.graph.GraphFactory;
import sample.moviesRepository.MoviesRepository;

public class CalculateThread extends Task<Void> {
    private FindConnectionCallback findConnectionCallback;
    private Actor start;
    private Actor stop;
    private TextAreaCallback textAreaCallback;
    private MoviesRepository repository;
    public CalculateThread(Actor startActor, Actor stopActor, TextAreaCallback textAreaCallback, FindConnectionCallback findConnectionCallback, MoviesRepository repository) {
        this.start = startActor;
        this.stop = stopActor;
        this.textAreaCallback = textAreaCallback;
        this.repository = repository;
        this.findConnectionCallback = findConnectionCallback;
    }

    @Override
    protected Void call() throws Exception {
        GraphFactory finder = new GraphFactory(start, stop, repository);
        finder.setTextAreaCallback(textAreaCallback);
        finder.setFindConnectionCallback(findConnectionCallback);
        finder.setProgressBarCallback((x,y) -> {
            updateProgress(x,y);
        });

        finder.makeGraph();
        return null;
    }
}
