package app.ui;

import app.rdg.Zakaznik;

public class VypisZakaznika {
    private static final VypisZakaznika INSTANCE = new VypisZakaznika();

    public static VypisZakaznika getInstance() { return INSTANCE; }

    private VypisZakaznika() { }

    public void print(Zakaznik zakaznik) {
        if (zakaznik == null) {
            throw new NullPointerException("Zakaznik nemoze byt nullovy");
        }

        System.out.print("Id :             ");
        System.out.println(zakaznik.getId());
        System.out.print("Meno:            ");
        System.out.println(zakaznik.getMeno());
        System.out.print("Priezvisko:      ");
        System.out.println(zakaznik.getPriezvisko());
        System.out.print("Email:           ");
        System.out.println(zakaznik.getEmail());
        System.out.print("Prilasmeno:      ");
        System.out.println(zakaznik.getPrihlasovaciMeno());
        System.out.print("Heslo:           ");
        System.out.println(zakaznik.getHeslo());
        System.out.println();
    }

}
