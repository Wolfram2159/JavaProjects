import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        Item a = new Item("Jabłka", ItemCondition.NEW,10, 2 );
        Item b = new Item("Brzoskwinie", ItemCondition.USED,3, 4 );
        Item c = new Item("Gruszki", ItemCondition.NEW,2, 1 );
        Item d = new Item("Kiwi", ItemCondition.REFURBISHED,1, 5 );
        a.print();
        FulfillmentCenter mag = new FulfillmentCenter("mag1", 250.0);
        mag.addProduct(a);
        mag.addProduct(b);
        mag.addProduct(c);
        mag.addProduct(c);
        mag.getProduct(c);
        mag.addProduct(d);
        mag.searchPartial("ab");
        List<Item> lista = new ArrayList<>(mag.sortByAmount());
        for (Item item : lista) {
            System.out.println("Nazwa: " + item.getNazwa() + " Ilosc: "+item.getIlosc());
        }
        mag.summary();
        mag.getProduct(a);
        FulfillmentCenterContainer mag_cont = new FulfillmentCenterContainer();
        mag_cont.addCenter(mag.getNazwa_mag(),mag.getLista(),mag.getMax());
        mag_cont.summary();

    }
}
