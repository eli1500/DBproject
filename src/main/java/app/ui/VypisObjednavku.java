package app.ui;

import app.rdg.Kosik;
import app.rdg.Objednavka;

public class VypisObjednavku {
    private static final VypisObjednavku INSTANCE = new VypisObjednavku();

    public static VypisObjednavku getInstance() { return INSTANCE; }

    private VypisObjednavku() { }

    public void print(Objednavka objednavka) {
        if (objednavka == null) {
            throw new NullPointerException("Objednavka nemoze byt nullova");
        }

        System.out.print("ObjednavkaID :   ");
        System.out.println(objednavka.getId());
        System.out.print("ZakaznikID:      ");
        System.out.println(objednavka.getZakaznik_id());
        System.out.print("AdresaID:        ");
        System.out.println(objednavka.getAdresa_id());
        System.out.print("Cena_spolu:      ");
        System.out.println(objednavka.getCena_spolu());
        System.out.print("Stav:            ");
        System.out.println(objednavka.getStav());
        System.out.print("ProduktID:     ");
        System.out.println(objednavka.getProdukt_id());
        System.out.print("Mnozstvo:      ");
        System.out.println(objednavka.getMnozstvo());
        System.out.println("----------------");

    }
}
