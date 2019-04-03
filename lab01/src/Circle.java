public class Circle extends Figure implements Printable {
    double r;
    Circle(double x){
        if(x>0){
            r=x;
        }else{
            throw new IllegalArgumentException();
        }
    }
    @Override
    double calculateArea() {
        return (Math.PI*r*r);
    }
    @Override
    double calculatePerimeter() {
        return (2*Math.PI*r);
    }
    @Override
    public void print() {
        System.out.println("Promień r="+r);
        System.out.println("Pole = "+calculateArea()+" Obwod = "+calculatePerimeter());
    }
}