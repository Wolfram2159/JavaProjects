package sample;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class CalcTask extends Task<Double> {
    private Canvas canvas;
    private double points;
    private PointLoss pointLoss;
    private Point point;

    public CalcTask(Canvas canvas, int points) {
        this.canvas = canvas;
        this.points = points;
    }
    @Override
    protected Double call() throws Exception {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        pointLoss = new PointLoss(canvas.getWidth() / 2, canvas.getHeight() / 2);
        BufferedImage bi = new BufferedImage((int) canvas.getWidth(), (int) canvas.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        double g = 0;
        for (int k = 0; k < points; k++) {
            point = pointLoss.calculate();
            if (point.getIsDraw()) {
                if (isCancelled()) {
                    break;
                }
                g++;
                int x = point.getX();
                int y = point.getY();
                bi.setRGB(x + (int) canvas.getWidth() / 2, y + (int) canvas.getHeight() / 2, Color.YELLOW.getRGB());
            }
            updateProgress(k, points);
            if (k % 5000 == 0) {
                gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0, 0);
            }
        }
        Double calka = 256 * (g / points);
        return calka;
    }
}
