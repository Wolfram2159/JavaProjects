public class Square extends Figure implements Printable {
    double a;
    Square(double x){
        if(x>0) {
            a = x;
        }else{
            throw new IllegalArgumentException();
        }
    }
    @Override
    double calculateArea() {
        return (a*a);
    }
    @Override
    double calculatePerimeter() {
        return (4*a);
    }
    @Override
    public void print() {
        System.out.println("Bok a="+a);
        System.out.println("Pole = "+calculateArea()+" Obwod = "+calculatePerimeter());
    }
}
