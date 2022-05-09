package app.ui;

import app.rdg.Kosik;
import app.rdg.Sklad;

public class VypisSklad {
    private static final VypisSklad INSTANCE = new VypisSklad();

    public static VypisSklad getInstance() { return INSTANCE; }

    private VypisSklad() { }

    public void print(Sklad sklad) {
        if (sklad == null) {
            throw new NullPointerException("Sklad nemoze byt nullovy");
        }

//        System.out.print("SkladID :                         ");
//        System.out.println(sklad.getId());
        System.out.print("ProduktID:                        ");
        System.out.println(sklad.getProdukt_id());
        System.out.print("Pocet kusov:                      ");
        System.out.println(sklad.getPocet_ks());
        System.out.print("Pocet aktualne rezervovanych:     ");
        System.out.println(sklad.getPocet_rezerv());
        System.out.println("--------------------------------");

    }
}
