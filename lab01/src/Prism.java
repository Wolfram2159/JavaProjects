public class Prism implements Printable{
    Figure podstawa;
    double h;
    Prism(Figure f, double w){
        if(w>0){
            podstawa = f;
            h = w;
        }else{
            throw new IllegalArgumentException();
        }
    }
    double calculateVolume(){
        return podstawa.calculateArea()*h;
    }
    double calculateArea(){
        return 2*podstawa.calculateArea()+podstawa.calculatePerimeter()*h;
    }
    @Override
    public void print() {
        System.out.println("Wysokosc = " + h + " Pp = " + calculateArea() + " V = "+calculateVolume());
    }
}