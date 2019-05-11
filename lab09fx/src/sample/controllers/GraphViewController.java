package sample.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.entities.Actor;
import sample.entities.Movie;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

public class GraphViewController {

    public ImageView imageView;

    public void transfer(List<Actor> vertices, List<Movie> edges) {
        Graph g = graph("graph");
        switch (vertices.size()) {
            case 2:
                g = graph("graph").with(
                        node(vertices.get(0).getName()).with(Color.GREEN).link(to(node(vertices.get(1).getName())).with(Label.of(edges.get(0).getTitle()))),
                        node(vertices.get(1).getName()).with(Color.RED)
                );
                break;
            case 3:
                g = graph("graph").with(
                        node(vertices.get(0).getName()).with(Color.GREEN).link(to(node(vertices.get(1).getName())).with(Label.of(edges.get(0).getTitle()))),
                        node(vertices.get(1).getName()).link(to(node(vertices.get(2).getName())).with(Label.of(edges.get(1).getTitle()))),
                        node(vertices.get(2).getName()).with(Color.RED)
                );
                break;
            case 4:
                g = graph("graph").with(
                        node(vertices.get(0).getName()).with(Color.GREEN).link(to(node(vertices.get(1).getName())).with(Label.of(edges.get(0).getTitle()))),
                        node(vertices.get(1).getName()).link(to(node(vertices.get(2).getName())).with(Label.of(edges.get(1).getTitle()))),
                        node(vertices.get(2).getName()).link(to(node(vertices.get(3).getName())).with(Label.of(edges.get(2).getTitle()))),
                        node(vertices.get(3).getName()).with(Color.RED)
                );
                break;
            case 5:
                g = graph("graph").with(
                        node(vertices.get(0).getName()).with(Color.GREEN).link(to(node(vertices.get(1).getName())).with(Label.of(edges.get(0).getTitle()))),
                        node(vertices.get(1).getName()).link(to(node(vertices.get(2).getName())).with(Label.of(edges.get(1).getTitle()))),
                        node(vertices.get(2).getName()).link(to(node(vertices.get(3).getName())).with(Label.of(edges.get(2).getTitle()))),
                        node(vertices.get(3).getName()).link(to(node(vertices.get(4).getName())).with(Label.of(edges.get(3).getTitle()))),
                        node(vertices.get(4).getName()).with(Color.RED)
                );
                break;
            case 6:
                g = graph("graph").with(
                        node(vertices.get(0).getName()).with(Color.GREEN).link(to(node(vertices.get(1).getName())).with(Label.of(edges.get(0).getTitle()))),
                        node(vertices.get(1).getName()).link(to(node(vertices.get(2).getName())).with(Label.of(edges.get(1).getTitle()))),
                        node(vertices.get(2).getName()).link(to(node(vertices.get(3).getName())).with(Label.of(edges.get(2).getTitle()))),
                        node(vertices.get(3).getName()).link(to(node(vertices.get(4).getName())).with(Label.of(edges.get(3).getTitle()))),
                        node(vertices.get(4).getName()).link(to(node(vertices.get(5).getName())).with(Label.of(edges.get(4).getTitle()))),
                        node(vertices.get(5).getName()).with(Color.RED)
                );
                break;
            case 7:
                g = graph("graph").with(
                        node(vertices.get(0).getName()).with(Color.GREEN).link(to(node(vertices.get(1).getName())).with(Label.of(edges.get(0).getTitle()))),
                        node(vertices.get(1).getName()).link(to(node(vertices.get(2).getName())).with(Label.of(edges.get(1).getTitle()))),
                        node(vertices.get(2).getName()).link(to(node(vertices.get(3).getName())).with(Label.of(edges.get(2).getTitle()))),
                        node(vertices.get(3).getName()).link(to(node(vertices.get(4).getName())).with(Label.of(edges.get(3).getTitle()))),
                        node(vertices.get(4).getName()).link(to(node(vertices.get(5).getName())).with(Label.of(edges.get(4).getTitle()))),
                        node(vertices.get(5).getName()).link(to(node(vertices.get(6).getName())).with(Label.of(edges.get(5).getTitle()))),
                        node(vertices.get(6).getName()).with(Color.RED)
                );
                break;
            case 8:
                g = graph("graph").with(
                        node(vertices.get(0).getName()).with(Color.GREEN).link(to(node(vertices.get(1).getName())).with(Label.of(edges.get(0).getTitle()))),
                        node(vertices.get(1).getName()).link(to(node(vertices.get(2).getName())).with(Label.of(edges.get(1).getTitle()))),
                        node(vertices.get(2).getName()).link(to(node(vertices.get(3).getName())).with(Label.of(edges.get(2).getTitle()))),
                        node(vertices.get(3).getName()).link(to(node(vertices.get(4).getName())).with(Label.of(edges.get(3).getTitle()))),
                        node(vertices.get(4).getName()).link(to(node(vertices.get(5).getName())).with(Label.of(edges.get(4).getTitle()))),
                        node(vertices.get(5).getName()).link(to(node(vertices.get(6).getName())).with(Label.of(edges.get(5).getTitle()))),
                        node(vertices.get(6).getName()).link(to(node(vertices.get(7).getName())).with(Label.of(edges.get(6).getTitle()))),
                        node(vertices.get(7).getName()).with(Color.RED)
                );
                break;
        }
        BufferedImage graf = Graphviz.fromGraph(g).height(600).width(600).render(Format.PNG).toImage();
        Image image = SwingFXUtils.toFXImage(graf, null);
        imageView.setImage(image);
        try {
            Graphviz.fromGraph(g).height(600).width(600).render(Format.PNG).toFile(new File("src/sample/graphExamples/ex" + vertices.size() + ".png"));
        }catch (IOException ex){
            System.out.println("graph to file ioexception");
        }
    }
}
