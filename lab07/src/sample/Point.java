package sample;

public class Point {
    private int x;
    private int y;
    private boolean isDraw;
    public Point(int x, int y, boolean isDraw) {
        this.x = x;
        this.y = y;
        this.isDraw = isDraw;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public boolean getIsDraw(){
        return isDraw;
    }
}
