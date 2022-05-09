package app.ui;

import app.rdg.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VypisProdukt {
    private static final VypisProdukt INSTANCE = new VypisProdukt();

    public static VypisProdukt getInstance() { return INSTANCE; }

    private VypisProdukt() { }

    public void print(Produkt produkt) throws SQLException {
        if (produkt == null) {
            throw new NullPointerException("Produkt nemoze byt nullovy");
        }
        int idProduktu = produkt.getId();

        List<Integer> listAtribID = new ArrayList<>(NajdiProdukt.getInstance().pomocna(idProduktu));

        System.out.println("---------------------------");
        System.out.print("Produkt Id :        ");
        System.out.println(produkt.getId());
        System.out.print("Nazov:      ");
        System.out.println(produkt.getNazov());
        System.out.print("Cena:      ");
        System.out.println(produkt.getCena());
        for(var a : listAtribID){
            Atribut atribut = NajdiAtribut.getInstance().findById(a);
            Atribut_Produktu ap = NajdiAtributProduktu.getInstance().findByAtributId(atribut.getId());
            System.out.print("Nazov atributu: ");
            System.out.println(atribut.getNazov());
            System.out.print("Hodnota atributu: ");
            System.out.println(ap.getHodnota());
        }
        System.out.println("---------------------------");







        System.out.println();

    }
}
