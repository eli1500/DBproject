package app.ui;

import java.util.List;

public class VypisStatistikuHladania {
    private static final VypisStatistikuHladania INSTANCE = new VypisStatistikuHladania();

    public static VypisStatistikuHladania getInstance() { return INSTANCE; }

    private VypisStatistikuHladania() { }

    public void print(List<List<String>> pole) {
        if (pole == null) {
            throw new NullPointerException("Pole nemoze byt nullove");
        }
        System.out.println("---------------------------------------------");
        for(var pol : pole) {

            System.out.print("Rok mesiac:                              ");
            System.out.println(pol.get(0));
            System.out.print("Datum, kde existuje dopyt:              ");
            System.out.println(pol.get(1));
            System.out.print("Pocet hladanych produktov:               ");
            System.out.println(pol.get(2));
            System.out.print("Pocet hladanych neproduktov:             ");
            System.out.println(pol.get(3));
            System.out.print("Pomer neprodukt/produkt:                 ");
            System.out.println(pol.get(4));
            System.out.println("---------------------------------------");
        }


    }
}
