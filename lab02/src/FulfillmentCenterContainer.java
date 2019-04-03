import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FulfillmentCenterContainer {
    Map<String, FulfillmentCenter> magazyny = new HashMap<String, FulfillmentCenter>();

    void addCenter(String s, List<Item> l, double d) {
        FulfillmentCenter a = new FulfillmentCenter(s,d);
        a.setLista(l);
        magazyny.put(s, a);
    }

    void removeCenter(String s) {
        magazyny.remove(s);
    }

    List<FulfillmentCenter> findEmpty() {
        List<FulfillmentCenter> list = new ArrayList<>();
        for (FulfillmentCenter value : magazyny.values()) {
            if (value.getLista().isEmpty()) {
                list.add(value);
            }
        }
        return list;
    }

    void summary() {

        for (FulfillmentCenter value : magazyny.values()) {
            System.out.println("Nazwa: " + value.getNazwa_mag() + " Reserved: " + (value.reservedSpace() / value.getMax()) * 100.0 + "%");
        }
    }
}
