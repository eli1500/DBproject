package app.ts;

import app.DbContext;
import app.rdg.*;

import javax.print.attribute.standard.JobKOctets;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class VytvorObjednavku {

    private static final VytvorObjednavku INSTANCE = new VytvorObjednavku();

    public static VytvorObjednavku getInstance() { return INSTANCE; }


    public double cena( List<Integer> produktyZakaznika, List<Integer> ListMnoztsva) throws SQLException {
        double result = 0;
        for(int i =0; i< produktyZakaznika.size();i++) {
            Produkt produkt = NajdiProdukt.getInstance().findById(produktyZakaznika.get(i));
            result += produkt.getCena() * ListMnoztsva.get(i);
        }
        return result;
    }
    public boolean vloz(int zakaznik_id, List<Integer> produktyZakaznika, int adresa_id, List<Integer> ListMnoztsva) throws ObjednavkaException, SQLException {
        if (zakaznik_id < 1 || produktyZakaznika.size() < 1) {
            throw new IllegalArgumentException("Id nesmie byt mensie ako 1.");
        }

        DbContext.getConnection().setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);  // pre update mnozstva musi vediet ci nebolo menene
        DbContext.getConnection().setAutoCommit(false);

        try {
            Zakaznik zakaznik = NajdiZakaznika.getInstance().findById(zakaznik_id);

            // kontrola zakaz
            if ( zakaznik == null) { throw new ObjednavkaException("Taky zakaznik neexistuje"); }

          //   kontrola produktu
            int flag = 0;
            for(int i =0; i< produktyZakaznika.size(); i++) {
                Produkt produkt = NajdiProdukt.getInstance().findById(produktyZakaznika.get(i));
                if (produkt == null) {
                    throw new ObjednavkaException("Taky produkt neexistuje");
                }
                int id_skladu = NajdiSklad.getInstance().findPocetByProdukt_id(produktyZakaznika.get(i));
                Sklad sklad = NajdiSklad.getInstance().findById(id_skladu);
                int pocetDostupnych = sklad.getPocet_ks() - sklad.getPocet_rezerv();
                if(pocetDostupnych - ListMnoztsva.get(i) < 0){
                    flag = 1;
                }

            }
            int mnoztvo = 0;
            if(flag == 1){
                System.out.println();
                System.out.println("Na sklade nie je dostatok mnozstva niekotreho z produktov");
                System.out.println("Produkty budu ale rezervovane!");
                for(int i =0; i< produktyZakaznika.size(); i++) {

                    int id_skladu = NajdiSklad.getInstance().findPocetByProdukt_id(produktyZakaznika.get(i));
                    Sklad sklad = NajdiSklad.getInstance().findById(id_skladu);
                    mnoztvo =  sklad.getPocet_ks() - sklad.getPocet_rezerv();

                    sklad.setPocet_rezerv(sklad.getPocet_rezerv() + ListMnoztsva.get(i) );
                    sklad.update();


                }

            }else{
                for(int i =0; i< produktyZakaznika.size(); i++) {

                    int id_skladu = NajdiSklad.getInstance().findPocetByProdukt_id(produktyZakaznika.get(i));
                    Sklad sklad = NajdiSklad.getInstance().findById(id_skladu);
                    mnoztvo =  sklad.getPocet_ks() - sklad.getPocet_rezerv();
                    // TOTO ESSTE PRI VYTVARANI NETREBA
                    //sklad.setPocet_ks(sklad.getPocet_ks() - ListMnoztsva.get(i) ); //mnzostvo - ??
                    sklad.setPocet_rezerv(sklad.getPocet_rezerv() + ListMnoztsva.get(i));

                    sklad.update();
                }

            }
            // kontrola vytvorenia objednavky
            Objednavka p = new Objednavka();
            p.setZakaznik_id(zakaznik_id);
            p.setAdresa_id(adresa_id);

            p.setCena_spolu( cena(produktyZakaznika,ListMnoztsva)); //cena(produktyZakaznika,ListMnoztsva))
            p.setStav("vytvorena");

            StringBuilder sbPreProdukty = new StringBuilder();
            for(int i =0 ; i< produktyZakaznika.size();i++){
               sbPreProdukty.append( produktyZakaznika.get(i));
               if(i != produktyZakaznika.size() -1){
                   sbPreProdukty.append(",");
               }
            }

            StringBuilder sbPreMnozstvo = new StringBuilder();
            for(int j = 0; j < ListMnoztsva.size();j++){
                sbPreMnozstvo.append( ListMnoztsva.get(j));
                if(j != ListMnoztsva.size() -1){
                    sbPreMnozstvo.append(",");
                }
            }
            p.setProdukt_id(sbPreProdukty.toString());
            p.setMnozstvo(sbPreMnozstvo.toString());


            double rand = new Random().nextDouble();
            double doprava = (0.5 + (rand * (5.5 - 0.5)) );
            doprava = Math.round(doprava * 100) / 100;
            double spolu = p.getCena_spolu() + doprava;
            p.setCena_spolu( (p.getCena_spolu() + doprava));

            p.insert();

            Integer pid = p.getId();
            Objednavka o = NajdiObjednavku.getInstance().findById(pid);



            if (o == null) { throw new ObjednavkaException("Táto objedávka sa nevykonala");
            }else{
                for(var pZ : produktyZakaznika) {
                    if(NajdiKosik.getInstance().findByZakaznik_idProdukt_id(zakaznik_id, pZ) != null) {
                        int Idkosika = NajdiKosik.getInstance().findByZakaznik_idProdukt_id(zakaznik_id, pZ);
                        Kosik k = NajdiKosik.getInstance().findById(Idkosika);
                        k.delete();
                    }else{
                        throw new ObjednavkaException("Tkaeto produkty ste v kosiku nemali");
                    }
                }

                System.out.println("Gratulujeme! Objednavka sa uspesne vytvorila!");
                System.out.println("--------------------------------------------");
                for(int i =0; i< produktyZakaznika.size();i++) {
                    Produkt produkt = NajdiProdukt.getInstance().findById(produktyZakaznika.get(i));
                    System.out.println( "Cena produktu " + produkt.getCena() + "€ * "+  ListMnoztsva.get(i)+  " = " + produkt.getCena()* ListMnoztsva.get(i) + "€");
                }

                System.out.println("Cena za dopravu "  + doprava + "€");
                System.out.println("Cena za objednavku " + p.getCena_spolu() + "€");
                System.out.println("Prosim zaplatte objednavku vo vyske " + spolu + "€");
                System.out.println("--------------------------------------------");
            }

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
