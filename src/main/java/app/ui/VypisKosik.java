package app.ui;

import app.rdg.Kosik;
import app.rdg.Produkt;

public class VypisKosik {
    private static final VypisKosik INSTANCE = new VypisKosik();

    public static VypisKosik getInstance() { return INSTANCE; }

    private VypisKosik() { }

    public void print(Kosik kosik) {
        if (kosik == null) {
            throw new NullPointerException("Kosik nemoze byt nullovy");
        }

        System.out.print("KosikID :        ");
        System.out.println(kosik.getId());
        System.out.print("ZakaznikID:      ");
        System.out.println(kosik.getZakaznik_id());
        System.out.print("ProduktID:       ");
        System.out.println(kosik.getProdukt_id());
        System.out.print("Mnoztvo:         ");
        System.out.println(kosik.getMnozstvo());
        System.out.print("Kedy:            ");
        System.out.println(kosik.getKedy());
        System.out.print("Subtotal:        ");
        System.out.println(kosik.getSubtotal());
        System.out.println("----------------");

    }
}
