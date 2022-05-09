package app.ts;

import app.DbContext;
import app.rdg.*;
import app.ui.VypisObjednavku;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class ZaplatObjednavku {

    private static final ZaplatObjednavku INSTANCE = new ZaplatObjednavku();

    public static ZaplatObjednavku getInstance() { return INSTANCE; }


    public boolean zaplat(int zakaznik_id, int objednavka_id, String metoda_platby, int suma) throws ObjednavkaException, SQLException {
        if (zakaznik_id < 1 || objednavka_id < 1) {
            throw new IllegalArgumentException("Id nesmie byt mensie ako 1.");
        }

        DbContext.getConnection().setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);  // pre update mnozstva musi vediet ci nebolo menene
        DbContext.getConnection().setAutoCommit(false);

        try {
            Zakaznik zakaznik = NajdiZakaznika.getInstance().findById(zakaznik_id);

            // kontrola zakaz
            if (zakaznik == null) {
                throw new IllegalArgumentException("Zakaznik bol nullovy");
            }


            Objednavka o = NajdiObjednavku.getInstance().findById(objednavka_id);
            if(o.getStav().equals("zrusena") ){
                System.out.println("Tato objednavka bola uz bohuzial zrusena");
            }else if( o.getStav().equals("expedovana") ){
                System.out.println("Tato objednavka je uz expedovana");
            }else if( o.getStav().equals("zaplatena")){
                System.out.println("Tato objednavka je uz zaplatena");
            }
            String[] ProduktyID = o.getProdukt_id().split(",");
            String[] mnozstva = o.getMnozstvo().split(",");

            //   kontrola produktu

            for(int i =0; i< ProduktyID.length; i++) {
                int flag = 0;
                Produkt produkt = NajdiProdukt.getInstance().findById(Integer.parseInt(ProduktyID[i]));
                if (produkt == null) {
                    throw new IllegalArgumentException("Taky produkt neexistuje/ bol null");
                }
                int id_skladu = NajdiSklad.getInstance().findPocetByProdukt_id(Integer.parseInt(ProduktyID[i]));
                Sklad sklad = NajdiSklad.getInstance().findById(id_skladu);
                int pocetDostupnych = sklad.getPocet_ks() - sklad.getPocet_rezerv();
                if(pocetDostupnych - Integer.parseInt(mnozstva[i]) < 0){
                    flag = 1;
                }

                if(flag == 1){
                    System.out.println();
                    System.out.println("Na sklade nie je dostatok mnozstva produktu s ID " + ProduktyID[i] );
                    System.out.println("Produkty budu ale kvoli vasej poziadavke doplnene z velkoskladu priamo k nam uz coskoro!");

                    sklad.setPocet_ks(sklad.getPocet_rezerv() + (Integer.parseInt(mnozstva[i]) - sklad.getPocet_ks()) );
                    sklad.update();

                }else{
                    sklad.setPocet_rezerv(sklad.getPocet_rezerv() - Integer.parseInt(mnozstva[i]));
                    sklad.update();
            }
            }
            //kontrola sumy, ktoru zaplatil
            if (o.getCena_spolu() != suma){
                System.out.println("Cena, ktoru ste chceli zaplatit a cena za objednavku sa nezhoduju!");
            }

            // kontrola zaplatenia platby
            Platba p = new Platba();
            p.setZakaznik_id(zakaznik_id);
            p.setObjednavka_id(objednavka_id);
            p.setMetoda_platby(metoda_platby);
            p.setSuma_platby(suma);
            p.setStatus_platby("spracovana");
            p.setDatum(Date.valueOf(LocalDate.now()));
            System.out.println(Date.valueOf(LocalDate.now()));
            p.insert();

            //zmena stavu v objednavke na zaplatena
            o.setStav("zaplatena");
            o.update();


            Platba platbaUrobena = NajdiPlatbu.getInstance().findById(p.getId());

            if (platbaUrobena == null) { throw new IllegalArgumentException("Táto platba sa nevykonala");
            }else{
                System.out.println("-----------------------------------------------");
                System.out.println("Gratulujeme! Objednavka bola uspesne zaplatena!");
                System.out.println("-----------------------------------------------");
            }

            DbContext.getConnection().commit();

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
