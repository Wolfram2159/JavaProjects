import java.util.Comparator;

public class Item implements Comparable<Item> {
    String nazwa;
    ItemCondition stan;
    double masa;
    Integer ilosc = 0;

    Item(String n, ItemCondition ic, double m, Integer i) {
        nazwa = n;
        stan = ic;
        masa = m;
        ilosc = i;
    }

    public void print() {
        System.out.println("Nazwa: " + nazwa + " Stan: " + stan + " Masa: " + masa + " Ilość: " + ilosc);
    }

    @Override
    public int compareTo(Item o) {
        if (this.nazwa == o.nazwa) {
            return 0;
        } else {
            return 1;
        }
    }

    public String getNazwa() {
        return nazwa;
    }

    public ItemCondition getStan() {
        return stan;
    }

    public double getMasa() {
        return masa;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setStan(ItemCondition stan) {
        this.stan = stan;
    }

    public void setMasa(double masa) {
        this.masa = masa;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }
}
