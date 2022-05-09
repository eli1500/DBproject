package app.ts;

import app.DbContext;
import app.rdg.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class ExpedovanieObjednavky {
    private static final ExpedovanieObjednavky INSTANCE = new ExpedovanieObjednavky();

    public static ExpedovanieObjednavky getInstance() { return INSTANCE; }


    public boolean expeduj( int objednavka_id) throws ObjednavkaException, SQLException {
        if (objednavka_id < 1) {
            throw new IllegalArgumentException("Id nesmie byt mensie ako 1.");
        }

        DbContext.getConnection().setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);  // pre update mnozstva musi vediet ci nebolo menene
        DbContext.getConnection().setAutoCommit(false);

        try {
            Objednavka o = NajdiObjednavku.getInstance().findById(objednavka_id);
            if(o == null){
                System.out.println("Objednavka s takymto ID neexistuje");
            }else {
                if (o.getStav().equals("zrusena")) {
                    System.out.println(("Tato objednavka bola uz bohuzial zrusena"));
                } else if (o.getStav().equals("expedovana")) {
                    System.out.println("Tato objednavka je uz expedovana");
                } else if (o.getStav().equals("vytvorena")) {
                    System.out.println("Tuto objednavku je este potrbne zaplatit");
                }
                String[] ProduktyID = o.getProdukt_id().split(",");
                String[] mnozstva = o.getMnozstvo().split(",");

                //   kontrola produktu
                for (int i = 0; i < ProduktyID.length; i++) {
                    Produkt produkt = NajdiProdukt.getInstance().findById(Integer.parseInt(ProduktyID[i]));
                    if (produkt == null) {
                        throw new IllegalArgumentException("Taky produkt neexistuje/ bol null");
                    }
                    int id_skladu = NajdiSklad.getInstance().findPocetByProdukt_id(Integer.parseInt(ProduktyID[i]));
                    Sklad sklad = NajdiSklad.getInstance().findById(id_skladu);
                    int pocetDostupnych = sklad.getPocet_ks() - sklad.getPocet_rezerv();

                    sklad.setPocet_ks(sklad.getPocet_ks() - Integer.parseInt(mnozstva[i]));
                    sklad.setPocet_rezerv(sklad.getPocet_rezerv() - Integer.parseInt(mnozstva[i]));

                    sklad.update();
                }


                //zmena stavu v objednavke na expedovana
                o.setStav("expedovana");
                o.update();

                System.out.println("----------------------------------------------------");
                System.out.println("Gratulujeme! Objednavka bola uspesne expedovana!");
                System.out.println("Zakupene produkty su na ceste k Vam, dakujeme za nakup!");
                System.out.println("---------------------------------------------------");
                DbContext.getConnection().commit();

            }
            return true;
        } catch(SQLException e) {
            DbContext.getConnection().rollback();
            throw e;
        }
        finally{
            DbContext.getConnection().setAutoCommit(true);
        }
    }

}
