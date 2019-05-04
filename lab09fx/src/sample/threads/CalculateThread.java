package sample.threads;

import javafx.concurrent.Task;
import sample.callbacks.ProgressBarCallback;
import sample.callbacks.TextAreaCallback;
import sample.entities.Actor;
import sample.graph.GraphFactory;
import sample.moviesRepository.MoviesRepository;

public class CalculateThread extends Task<Void> {
    Actor start;
    Actor stop;
    TextAreaCallback textAreaCallback;
    MoviesRepository repository;
    public CalculateThread(Actor startActor, Actor stopActor, TextAreaCallback textAreaCallback, MoviesRepository repository) {
        this.start = startActor;
        this.stop = stopActor;
        this.textAreaCallback = textAreaCallback;
        this.repository = repository;
    }

    @Override
    protected Void call() throws Exception {
        GraphFactory finder = new GraphFactory(start, stop, repository);
        finder.setTextAreaCallback(textAreaCallback);
        finder.setProgressBarCallback((x,y) -> {
            updateProgress(x,y);
        });
        finder.makeGraph();
        return null;
    }
}
