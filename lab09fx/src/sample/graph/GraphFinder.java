package sample.graph;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;

import java.util.List;

import sample.entities.Actor;
import sample.entities.Movie;

public class GraphFinder {
    private Graph<Actor, Movie> g;
    private Actor firstActor;
    private Actor secondActor;

    public GraphFinder(Graph<Actor, Movie> g, Actor firstActor, Actor secondActor) {
        this.g = g;
        this.firstActor = firstActor;
        this.secondActor = secondActor;
    }
    public void findPath(){
        BellmanFordShortestPath<Actor, Movie> bfsp = new BellmanFordShortestPath<>(g);
        GraphPath<Actor, Movie> shortestPath = bfsp.getPath(firstActor, secondActor);
        List<Movie> edges = shortestPath.getEdgeList();
        List<Actor> actors = shortestPath.getVertexList();
        for (int v = 0; v < actors.size(); ++v) {
            if (v == actors.size() - 1)
                System.out.print(actors.get(v));
            else
                System.out.print(actors.get(v) + " -> " + edges.get(v).toString() + " -> ");
        }
    }
}
