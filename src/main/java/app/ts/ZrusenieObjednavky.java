package app.ts;

import app.DbContext;
import app.rdg.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZrusenieObjednavky {
    private static final ZrusenieObjednavky INSTANCE = new ZrusenieObjednavky();

    public static ZrusenieObjednavky getInstance() { return INSTANCE; }


    public boolean zrus(int zakaznik_id, List<Integer> ListObjednavokId) throws ObjednavkaException, SQLException {
        if (zakaznik_id < 1 ) {
            throw new IllegalArgumentException("ZakaznikId nesmie byt mensie ako 1.");
        }


        DbContext.getConnection().setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);  // pre update mnozstva musi vediet ci nebolo menene
        DbContext.getConnection().setAutoCommit(false);

        try {
            Zakaznik zakaznik = NajdiZakaznika.getInstance().findById(zakaznik_id);

            // kontrola zakaz
            if ( zakaznik == null) { throw new ObjednavkaException("Taky zakaznik neexistuje"); }

             for(int i =0; i< ListObjednavokId.size(); i++) {
                Objednavka objednavka = NajdiObjednavku.getInstance().findById(ListObjednavokId.get(i));
                if (objednavka == null) {
                    throw new ObjednavkaException("Taka objednavka neexistuje");
                }
                if (objednavka.getStav().equals("expedovana") ||objednavka.getStav().equals("zaplatena") ) {
                    throw new ObjednavkaException("Nie je mozne zrusit objednavku, pretoze je uz expedovana alebo zaplatena");

                }else {
                    List<String> sbProduktov = new ArrayList<>(List.of(objednavka.getProdukt_id().split(",")));
                    List<String> sbMnoztva = new ArrayList<>(List.of(objednavka.getMnozstvo().split(",")));

                    for(int j =0; j< sbProduktov.size();j++){ ///aj mnozstvo ma rovnku size
                    int id_skladu = NajdiSklad.getInstance().findPocetByProdukt_id(Integer.parseInt(sbProduktov.get(j)));
                    Sklad sklad = NajdiSklad.getInstance().findById(id_skladu);
                    int zvysenyPocetKS = sklad.getPocet_rezerv() - Integer.parseInt(sbMnoztva.get(j));
                    sklad.setPocet_rezerv(zvysenyPocetKS);
                    sklad.update();
                    }

                    objednavka.setStav("zrusena");
                    objednavka.update();

                }

                 System.out.println("Objednavku cislo " + ListObjednavokId.get(i) + " sa uspesne podarilo zrusit!" );

             }
            System.out.println("--------------------------------------");
            // MISKA TREBA MI ZMENIT OBJEDNAVKU, PRIDAJ  MONZTVO A AJ PRODUKT_ID NECH POTOM VIES PRACOVAT SO SKLADOM

            DbContext.getConnection().commit();

            return true;

        } catch(SQLException | ObjednavkaException e) {
            DbContext.getConnection().rollback();
            throw e;
        }
        finally{
            DbContext.getConnection().setAutoCommit(true);
        }
    }
}
