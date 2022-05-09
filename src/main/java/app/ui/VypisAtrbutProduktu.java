package app.ui;

import app.rdg.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VypisAtrbutProduktu {
    private static final VypisAtrbutProduktu INSTANCE = new VypisAtrbutProduktu();

    public static VypisAtrbutProduktu getInstance() { return INSTANCE; }

    private VypisAtrbutProduktu() { }

    public void print(List<Integer> listap) throws SQLException {
        if (listap == null) {
            throw new NullPointerException("AtributyProduktov nemozu byt nullove");
        }

        for(int a : listap){
            Atribut_Produktu ap = NajdiAtributProduktu.getInstance().findById(a);
            //System.out.println("vypisujem ap podla intu z pola id: " + ap.getId() +" prvok pola  " + a );
            Atribut atribut = NajdiAtribut.getInstance().findById(ap.getAtribut_id());

            Produkt produkt = NajdiProdukt.getInstance().findById(ap.getProdukt_id());
            System.out.println("---------------------------");
            System.out.print("Produkt Id :        ");
            System.out.println(produkt.getId());
            System.out.print("Nazov:      ");
            System.out.println(produkt.getNazov());
            System.out.print("Cena:      ");
            System.out.println(produkt.getCena());

            System.out.print("Nazov atributu: ");
            System.out.println(atribut.getNazov());
            System.out.print("Hodnota atributu: ");
            System.out.println(ap.getHodnota());
        }
        System.out.println("---------------------------");







        System.out.println();

    }
}
