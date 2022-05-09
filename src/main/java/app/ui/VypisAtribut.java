package app.ui;

import app.rdg.*;

import java.sql.SQLException;
import java.util.List;

public class VypisAtribut {
    private static final VypisAtribut INSTANCE = new VypisAtribut();

    public static VypisAtribut getInstance() { return INSTANCE; }

    private VypisAtribut() { }

    public void print(List<Integer> lista) throws SQLException {
        if (lista == null) {
            throw new NullPointerException("Atributy nemozu byt nullove");
        }

        for(int a : lista){
            //System.out.println("vypisujem ap podla intu z pola id: " + ap.getId() +" prvok pola  " + a );
            Atribut atribut = NajdiAtribut.getInstance().findById(a);
            Atribut_Produktu ap = NajdiAtributProduktu.getInstance().findByAtributId(atribut.getId());

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
