import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FulfillmentCenter {
    String nazwa_mag;
    List<Item> lista = new ArrayList<Item>();
    double max;

    public FulfillmentCenter(String nazwa_mag, Double max) {
        this.nazwa_mag = nazwa_mag;
        this.max = max;
    }

    public List<Item> getLista() {
        return lista;
    }

    public String getNazwa_mag() {
        return nazwa_mag;
    }

    public double getMax() {
        return max;
    }

    public void setLista(List<Item> lista) {
        this.lista = lista;
    }

    double reservedSpace(){
        double masa = 0.0;
        for (int i = 0; i < lista.size(); i++) {
            masa += lista.get(i).getIlosc() * lista.get(i).getMasa();
        }
        return masa;
    }
    void addProduct(Item a) {
        boolean add = true;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).compareTo(a) == 0) {
                int start_ile = lista.get(i).getIlosc();
                int ile = start_ile + a.getIlosc();
                lista.get(i).setIlosc(ile);
                double masa = 0;
                for (int j = 0; j < lista.size(); j++) {
                    masa += lista.get(j).getIlosc() * lista.get(j).getMasa();
                }
                if (masa > max) {
                    lista.get(i).setIlosc(start_ile);
                    System.err.println("err");
                } else {
                    add = false;
                    break;
                }
            }
        }
        if (add) {
            lista.add(a);
        }
    }

    void getProduct(Item a) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).compareTo(a) == 0) {
                lista.get(i).setIlosc(lista.get(i).getIlosc() - 1);
                if (lista.get(i).getIlosc() == 0) {
                    lista.remove(i);
                }
            }
        }
    }

    void removeProduct(Item a) {
        lista.remove(a);
    }

    Item search(String s) {
        StringComparator sc = new StringComparator();
        for (int i = 0; i < lista.size(); i++) {
            if (sc.compare(s, lista.get(i).getNazwa()) == 0) {
                return lista.get(i);
            }
        }
        return null;
    }

    List<Item> searchPartial(String s) {
        List<Item> list = new ArrayList<Item>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNazwa().contains(s)) {
                list.add(lista.get(i));
            }
        }
        return list;
    }

    int countByCondition(ItemCondition stan) {
        int suma = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getStan() == stan) {
                suma += 1;
            }
        }
        return suma;
    }

    void summary() {
        for (int i = 0; i < lista.size(); i++) {
            lista.get(i).print();
        }
    }

    List<Item> sortByName() {
        Collections.sort(lista, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getNazwa().compareTo(o2.getNazwa());
            }
        });
        return lista;
    }

    List<Item> sortByAmount() {
        Collections.sort(lista, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return Integer.compare(o1.getIlosc(), o2.getIlosc());
            }
        });
        return lista;
    }

    Item max() {
        return Collections.max(lista, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return Integer.compare(o1.getIlosc(), o2.getIlosc());
            }
        });
    }

}