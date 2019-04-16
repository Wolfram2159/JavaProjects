package sample;

import java.util.Random;

public class PointLoss {
    private double Ax;
    private double Bx;
    private double Cx;
    private double Dx;
    private double Ay;
    private double By;
    private double Cy;
    private double Dy;

    public PointLoss(double width, double height) {
       Ax = -8;
       Bx = 8;
       Ay = -8;
       By = 8;
       Cx = -width;
       Dx = width;
       Cy = -height;
       Dy = height;
    }
    public Point calculate(){
        Random rand = new Random();
        double i = -8 + (8 + 8) * rand.nextDouble();
        double j = -8 + (8 + 8) * rand.nextDouble();
        boolean isDraw = Equation.calc(i,j);
        int x = (int)((Dx-Cx)*(i-Ax)/(Bx-Ax)+Cx);
        int y = -(int)((Dy-Cy)*(j-Ay)/(By-Ay)+Cy);
        return new Point(x,y,isDraw);
    }
}
