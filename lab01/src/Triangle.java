public class Triangle extends Figure implements Printable {
    double a;
    double b;
    double c;
    Triangle(double x, double y, double z){
        if(x+y>z&&x+z>y&&y+z>x&&x>0&&y>0&&z>0) {
            a = x;
            b = y;
            c = z;
        }else {
            throw new IllegalArgumentException();
        }
        }
    @Override
    double calculateArea() {
        double p = calculatePerimeter();
        return (Math.sqrt(0.5*p*(0.5*p-a)*(0.5*p-b)*(0.5*p-c)));
    }
    @Override
    double calculatePerimeter() {
        return (a+b+c);
    }
    @Override
    public void print() {
        System.out.println("Bok a="+a+" Bok b="+b+" Bok c="+c);
        System.out.println("Pole = "+calculateArea()+" Obwod = "+calculatePerimeter());
    }
}
