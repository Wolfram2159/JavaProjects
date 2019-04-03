import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int cr=0;
        Triangle t = null;
        Circle k = null;
        Square s = null;
        Prism p = null;
        int myInt;
        do {
            System.out.println("Jaka figure chcesz stworzyć ?");
            System.out.println("1. Trojkat");
            System.out.println("2. Kwadrat");
            System.out.println("3. Kolo");
            System.out.println("4. Graniastoslup");
            Scanner scanner = new Scanner(System.in);
            myInt = scanner.nextInt();
            double a, b, c;
            switch (myInt) {
                case 1:
                    System.out.println("Podaj wymiary trojkata:");
                    a = scanner.nextDouble();
                    b = scanner.nextDouble();
                    c = scanner.nextDouble();
                    try {
                        t = new Triangle(a, b, c);
                        System.out.println("Stworzono trojkat");
                        cr = 1;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("Wystąpił błąd");
                    }
                    break;
                case 2:
                    System.out.println("Podaj dlugosc boku kwadratu:");
                    a = scanner.nextDouble();
                    try {
                        s = new Square(a);
                        System.out.println("Stworzono kwadrat");
                        cr = 2;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("Wystąpił błąd");
                    }
                    break;
                case 3:
                    System.out.println("Podaj promien kola:");
                    a = scanner.nextDouble();
                    try {
                        k = new Circle(a);
                        System.out.println("Stworzono kolo");
                        cr = 3;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        System.out.println("Wystąpił błąd");
                    }
                    break;
                case 4:
                    System.out.println("Podaj wysokosc graniastoslupa:");
                    double h = scanner.nextInt();
                    System.out.println("Jaka figura ma być podstawa ?");
                    System.out.println("1. Trojkat");
                    System.out.println("2. Kwadrat");
                    System.out.println("3. Kolo");
                    int myInt2 = scanner.nextInt();
                    cr = 4;
                    switch (myInt2) {
                        case 1:
                            System.out.println("Podaj wymiary trojkata:");
                            a = scanner.nextDouble();
                            b = scanner.nextDouble();
                            c = scanner.nextDouble();
                            try {
                                t = new Triangle(a, b, c);
                                System.out.println("Stworzono trojkat");
                                p = new Prism(t, h);
                                System.out.println("Stworzono graniastoslup");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                System.out.println("Wystąpił błąd");
                            }
                            break;
                        case 2:
                            System.out.println("Podaj dlugosc boku kwadratu:");
                            a = scanner.nextDouble();
                            try {
                                s = new Square(a);
                                System.out.println("Stworzono kwadrat");
                                p = new Prism(s, h);
                                System.out.println("Stworzono graniastoslup");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                System.out.println("Wystąpił błąd");
                            }
                            break;
                        case 3:
                            System.out.println("Podaj promien kola:");
                            a = scanner.nextDouble();
                            try {
                                k = new Circle(a);
                                System.out.println("Stworzono kolo");
                                p = new Prism(k, h);
                                System.out.println("Stworzono graniastoslup");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                System.out.println("Wystąpił błąd");
                            }
                            break;
                    }
                    break;
            }
            do {
                System.out.println("Co chcesz zrobic ?");
                System.out.println("1. Wyswietlic informacje o stworzonej figurze");
                System.out.println("2. Stworzyć nową figure");
                System.out.println("3. Zakonczyc program");
                myInt = scanner.nextInt();
                switch (myInt) {
                    case 1:
                        switch (cr) {
                            case 1:
                                t.print();
                                break;
                            case 2:
                                s.print();
                                break;
                            case 3:
                                k.print();
                                break;
                            case 4:
                                p.print();
                                break;
                        }
                        break;
                    case 2:
                        myInt = 9;
                        break;
                    case 3:
                        myInt = 10;
                        break;
                }
            }while(myInt < 9);
        }while(myInt!=10);
    }
}