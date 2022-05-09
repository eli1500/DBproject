package app.ui;

import app.rdg.*;
import app.ts.*;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

public class MainMenu extends Menu {
    @Override
    public void print() {
        System.out.println("************************************");
        System.out.println("* 1. zakaznici  - operacie         *");
        System.out.println("* 2. produkty   - operacie         *");
        System.out.println("* 3. sklad      - operacie         *");
        System.out.println("* 4. kosik      - operacie         *");


        System.out.println("* 14. vytvorenie objednavky        *");
        System.out.println("* 14. zrusenie objednavky          *");
        System.out.println("* 16. zaplatenie objednavky        *");
        System.out.println("* 17. expedvanie objednavky        *");
        System.out.println("* 18. hladanie                     *");
        System.out.println(" 19. statistika pre pocet atributov produktu *");
        System.out.println(" 20. statistika pre hladanie       *");
        System.out.println("* 21. exit                         *");
        System.out.println("************************************");


    }
    public void druheMenuckoPRINT(String option){
            switch (option){
                case "1":
                    System.out.println("************************************");
                    System.out.println("* 1. vypíš zoznam zákazníkov       *");
                    System.out.println("* 2. pridaj zákazníka              *");
                    System.out.println("* 3. aktualizuj zákazníka          *");
                    System.out.println("* 4. odstráň zákazníka             *");
                    System.out.println("************************************");
                    break;

                case "2":
                    System.out.println("************************************");
                    System.out.println("* 5. vypíš zoznam produktov        *");
                    System.out.println("* 6. pridaj produkt                *");
                    System.out.println("* 7. aktualizuj produkt            *");
                    System.out.println("* 8. odstráň produkt               *");
                    System.out.println("************************************");
                    break;
                case "3":
                    System.out.println("************************************");
                    System.out.println("* 9. vypis skladove zasoby prduktu *");
                    System.out.println("* 10. aktualizuj sklad             *");
                    System.out.println("************************************");
                    break;
                case "4":
                    System.out.println("**************************************************");
                    System.out.println("* 11. vypíš produkty v nákupnom košíku zákazníka *");
                    System.out.println("* 12. pridaj produkt do košíka                   *");
                    System.out.println("* 13. odstráň produkt z košíka                   *");
                    System.out.println("**************************************************");
                    break;


            }

    }
   
    @Override
    public void handle(String option) throws SQLException, IOException, ObjednavkaException {
        switch (option){
                case "1": vypisVsetkychZakaznikov(); break;
                case "2": pridajZakaznika(); break;
                case "3": aktualizujZakaznika(); break;
                case "4": vymazZakaznika(); break;
                case "5": vypisVsetkyProdukty(); break;
                case "6": pridajProdukt(); break;
                case "7": aktualizujProdukt(); break;
                case "8": vymazProdukt(); break;
                case "9": vypisSkladoveZasobyPreProdukt(); break;
                case "10": aktualizujSklad(); break;
                case "11": vypisKosik(); break;
                case "12": pridajDoKosika(); break;
                case "13": odstranZkosika(); break;

                case "14": vytvorObjednavku(); break;
                case "15": zrusObjednavku(); break;
                case "16": zaplatObjednavku(); break;
                case "17": expedovatObjednavku(); break;
                case "18": vyhladajProdukt(); break;
                case "19": statistikaPoctuAtributov(); break;
                case "20": statistikaHladanie(); break;

                case "21": exit(); break;

            default: System.out.println("Neznama moznost");  break;
        }

    }





    private void vypisVsetkychZakaznikov() throws SQLException{
        for(Zakaznik z : NajdiZakaznika.getInstance().findAll()){
            VypisZakaznika.getInstance().print(z);
        }
    }
    private void vypisVsetkyProdukty() throws SQLException{
        for(Produkt p : NajdiProdukt.getInstance().findAll()){
            VypisProdukt.getInstance().print(p);
        }
    }
    private void vypisSkladoveZasobyPreProdukt() throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Zadajte IDproduktu:");
        int id_Skladu =  NajdiSklad.getInstance().findPocetByProdukt_id(Integer.parseInt(br.readLine()));
        Sklad sklad = NajdiSklad.getInstance().findById(id_Skladu);
        VypisSklad.getInstance().print(sklad);


    }
    private void pridajZakaznika() throws SQLException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Zakaznik zakaznik = new Zakaznik();

        System.out.println("Zadajte meno:");
        zakaznik.setMeno(br.readLine());
        System.out.println("Zadajte priezvisko:");
        zakaznik.setPriezvisko(br.readLine());
        System.out.println("Zadajte email:");
        zakaznik.setEmail(br.readLine());
        System.out.println("Zadajte prihlasovacie meno:");
        zakaznik.setPrihlasovaciMeno(br.readLine());
        System.out.println("Zadajte heslo:");
        zakaznik.setHeslo(br.readLine());

        zakaznik.insert();

        System.out.println("Pridanie zakaznika prebehlo uspesne");
        System.out.print("Id zakaznika: ");
        System.out.println(zakaznik.getId());
    }

    private void pridajProdukt() throws SQLException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Produkt produkt = new Produkt();

        System.out.println("Zadajte nazov produktu:");
        produkt.setNazov(br.readLine());
        System.out.println("Zadajte cenu:");
        produkt.setCena(Double.valueOf(br.readLine()));
        produkt.insert();

        Atribut atribut = new Atribut();
        System.out.println("Zadajte nazov atributu pre produkt: ");
        atribut.setNazov(br.readLine());
        atribut.insert();

        Atribut_Produktu ap = new Atribut_Produktu();
        ap.setAtribut_id(atribut.getId());
        ap.setProdukt_id(produkt.getId());
        System.out.println("Zadajte hodnotu atributu pre produkt: ");
        ap.setHodnota(br.readLine());
        ap.insert();

        //kontrola skladu ci uz dany produkt neobsahuje
        int id_skladu = NajdiSklad.getInstance().findPocetByProdukt_id(produkt.getId());
        Sklad sklad = NajdiSklad.getInstance().findById(id_skladu);

        if(sklad == null) {
            Sklad s = new Sklad();
            s.setProdukt_id(produkt.getId());

            System.out.println("Zadajte pocet ks pre produkt: ");
            s.setPocet_ks(Integer.valueOf(br.readLine()));
            System.out.println("Zadajte pocet rezervovanych ks pre produkt: ");
            s.setPocet_rezerv(Integer.valueOf(br.readLine()));
            s.insert();

            System.out.println("Novy produkt sa pridal do skladu");
        }else{
            int kolkoKSjeteraz = sklad.getPocet_ks();
            int kolkoREZERjeteraz = sklad.getPocet_rezerv();
            System.out.println("Produkt uz v sklade existuje");

            System.out.println("Zadajte novy(+) pocet ks pre produkt: ");
            sklad.setPocet_ks(Integer.parseInt(br.readLine()) + kolkoKSjeteraz);
            System.out.println("Zadajte novy(+) pocet rezervovanych ks pre produkt: ");
            sklad.setPocet_rezerv(Integer.parseInt(br.readLine()) + kolkoREZERjeteraz);


        }


        //kontrola ci uz taky produkt neni na sklade

        System.out.println("Pridanie produktu prebehlo uspesne");
        System.out.print("Id produktu: ");
        System.out.println(produkt.getId());
    }

    private void aktualizujZakaznika() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Zadajte id zakaznika:");
        int customerId = Integer.parseInt(br.readLine());

        Zakaznik zakaznik = NajdiZakaznika.getInstance().findById(customerId);

        if (zakaznik == null) {
            System.out.println("Takyto zakaznik neexistuje");
        } else {
            VypisZakaznika.getInstance().print(zakaznik);

            System.out.println("Zadajte meno:");
            zakaznik.setMeno(br.readLine());
            System.out.println("Zadajte priezvisko:");
            zakaznik.setPriezvisko(br.readLine());
            System.out.println("Zadajte email:");
            zakaznik.setEmail(br.readLine());
            System.out.println("Zadajte prihlasovacie meno:");
            zakaznik.setPrihlasovaciMeno(br.readLine());
            System.out.println("Zadajte heslo:");
            zakaznik.setHeslo(br.readLine());

            zakaznik.update();
            System.out.println();
            System.out.println("Zakaznik bol uspesne aktualizovany!");
        }
    }

    private void aktualizujProdukt() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Zadajte id produktu:");
        int produktId = Integer.parseInt(br.readLine());

        Produkt produkt = NajdiProdukt.getInstance().findById(produktId);
       if (produkt == null) {
            System.out.println("Takyto produkt neexistuje");
        } else {
            VypisProdukt.getInstance().print(produkt );

            System.out.println("Zmente nazov:");
            produkt.setNazov(br.readLine());
            System.out.println("Zmente cenu:");
            produkt.setCena(Double.valueOf(br.readLine()));
            produkt.update();

           Atribut_Produktu ap = NajdiAtributProduktu.getInstance().findByProduktId(produktId);

           if(ap == null){
               Atribut atribut1 = new Atribut();
                System.out.println("Tento produkt nema nastavene ziadne atributy");
                System.out.println("Zadajte nazov atributu pre produkt: ");
                atribut1.setNazov(br.readLine());
                atribut1.insert();

                Atribut_Produktu ap1 = new Atribut_Produktu();
                ap1.setAtribut_id(atribut1.getId());
                ap1.setProdukt_id(produktId);
                System.out.println("Zadajte hodnotu atributu pre produkt: ");
                ap1.setHodnota(br.readLine());
                ap1.insert();
            }else {
               Atribut atribut = NajdiAtribut.getInstance().findById(ap.getAtribut_id());


               System.out.println("Zmente nazov atributu pre produkt: ");
                atribut.setNazov(br.readLine());
                atribut.update();

                ap.setAtribut_id(atribut.getId());
                ap.setProdukt_id(produktId);
                System.out.println("Zmente hodnotu atributu pre produkt: ");
                ap.setHodnota(br.readLine());
                ap.update();


            }

            int id_skladu = NajdiSklad.getInstance().findPocetByProdukt_id(produktId);
            Sklad sklad = NajdiSklad.getInstance().findById(id_skladu);

            System.out.println("Zadajte novy pocet ks pre produkt: ");
            sklad.setPocet_ks(Integer.parseInt(br.readLine()) );
            System.out.println("Zadajte novy pocet rezervovanych ks pre produkt: ");
            sklad.setPocet_rezerv(Integer.parseInt(br.readLine()) );
            sklad.setProdukt_id(produktId);
            sklad.update();




            System.out.println();
            System.out.println("Produkt bol uspesne aktualizovany!");
        }
    }

    private void aktualizujSklad() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Zadajte id produktu:");
        int produktID = Integer.parseInt(br.readLine());

        int IDsklad = NajdiSklad.getInstance().findPocetByProdukt_id(produktID);
        Sklad sklad = NajdiSklad.getInstance().findById(IDsklad);

        if (sklad == null) {
            System.out.println("Takyto sklad neexistuje");
        } else {
            VypisSklad.getInstance().print(sklad);

            sklad.setId(IDsklad);
            sklad.setProdukt_id(produktID);
            System.out.println("Zadajte pocet kusov:");
            sklad.setPocet_ks(Integer.valueOf(br.readLine()));
            System.out.println("Zadajte pocet rezervovanych kusov:");
            sklad.setPocet_rezerv(Integer.valueOf(br.readLine()));

            sklad.update();
            System.out.println();
            System.out.println("Sklad bol uspesne aktualizovany!");
        }
    }

    private void vymazZakaznika() throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter a customer's id:");
        int customerId = Integer.parseInt(br.readLine());

        Zakaznik zakaznik = NajdiZakaznika.getInstance().findById(customerId);

        if (zakaznik == null) {
            System.out.println("Takyto zakaznik neexistuje");
        } else {
            zakaznik.delete();
            System.out.println("Vymazanie zakaznika bolo uspesne");
        }
    }

    private void vymazProdukt() throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Zadajte id produktu:");
        int produktId = Integer.parseInt(br.readLine());

        Produkt produkt = NajdiProdukt.getInstance().findById(produktId);

        if (produkt == null) {
            System.out.println("Takyto produkt neexistuje");
        } else {
            produkt.delete();
            System.out.println("Vymazanie produktu bolo uspesne");
        }
    }

    private void vypisKosik() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Zadajte ID zakaznika:");
        int zakID = Integer.parseInt(br.readLine());
        Zakaznik z = NajdiZakaznika.getInstance().findById(zakID);
        if(z== null){
            System.out.println("Takyto zakaznik neexistuje. Prosim vytvorte si konto!");
        }else {
            List<Kosik> id_kosika = NajdiKosik.getInstance().findByZakaznik_id(zakID);
            if (id_kosika.size() == 0) {
                System.out.println("Tento zakaznik nema vytvoreny kosik. ");
            } else {
                for (var kosik : id_kosika) {
                    VypisKosik.getInstance().print(kosik);
                }
            }
        }
    }
    private void vytvorNovyKosik(int zakznik_id) throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Kosik kosik = new Kosik();

        kosik.setZakaznik_id(zakznik_id);
        System.out.println("Zadajte id produktu:");
        int idProduktu = Integer.parseInt(br.readLine());
        kosik.setProdukt_id(idProduktu);
        System.out.println("Zadajte mnozstvo produktu:");
        kosik.setMnozstvo(Integer.valueOf(br.readLine()));
        kosik.setKedy(Timestamp.from(Instant.now()));
        Produkt produkt= NajdiProdukt.getInstance().findById(idProduktu);
        double cenaProduktu = produkt.getCena();
        kosik.setSubtotal(cenaProduktu * kosik.getMnozstvo());
        kosik.insert();


        System.out.println("Pridanie produktu do kosika prebehlo uspesne");
        System.out.print("Id kosika: ");
        System.out.println(kosik.getId());

    }
    private void vytvorKosikExisZa(int zakznik_id) throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int idVytvKosika = 0;
        System.out.println("Zadajte id produktu:");
        int idProduktu = Integer.parseInt(br.readLine());
        List<Kosik> kosiky = NajdiKosik.getInstance().findByZakaznik_id(zakznik_id);
        //tuto sa vlastne riesi uz aj aktualizacia mnozstva v kosiku
        for(var k : kosiky){
            if(k.getProdukt_id().equals(idProduktu)){
                System.out.println("Takyto produkt uz v kosiku mate.");
                System.out.println("Zadajte mnozstvo produktu:");
                k.setMnozstvo( Integer.parseInt(br.readLine()));
                k.setKedy(Timestamp.from(Instant.now()));
                Produkt produkt= NajdiProdukt.getInstance().findById(idProduktu);
                double cenaProduktu = produkt.getCena();
                k.setSubtotal(cenaProduktu * k.getMnozstvo());
                k.update();
                 idVytvKosika = k.getId();

                System.out.println("Aktualizovanie produktu v kosiku prebehlo uspesne");
                System.out.print("Id vytvoreneho kosika: ");
                System.out.println(idVytvKosika);

            }else{
                k.setProdukt_id(idProduktu);
                System.out.println("Zadajte mnozstvo produktu:");
                k.setMnozstvo( Integer.parseInt(br.readLine()));
                k.setKedy(Timestamp.from(Instant.now()));
                Produkt produkt= NajdiProdukt.getInstance().findById(idProduktu);
                double cenaProduktu = produkt.getCena();
                k.setSubtotal(cenaProduktu * k.getMnozstvo());
                k.insert();
                idVytvKosika = k.getId();

                System.out.println("Pridanie produktu do kosika prebehlo uspesne");
                System.out.print("Id vytvoreneho kosika: ");
                System.out.println(idVytvKosika);
            }

        }



    }

    private void pridajDoKosika() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Zadajte ID zakaznika:");
        int zakznik_id = Integer.parseInt(br.readLine());
        List<Kosik> id_kosika =  NajdiKosik.getInstance().findByZakaznik_id(zakznik_id);
        if(id_kosika == null || id_kosika.size()==0){
            System.out.println("Tento zakaznik este nema vytvoreny ziadny kosik");
            System.out.println("Vytvorte si prvy!");
            vytvorNovyKosik(zakznik_id);

        }else{
            System.out.println("Tento zakaznik u nas ma momentalne nasledovne kosiky");
             for(var kosik : id_kosika){
                VypisKosik.getInstance().print(kosik);
            }
            System.out.println("Pridajte si produkt, ktory este nemate v kosiku!");
            vytvorKosikExisZa(zakznik_id);
        }

    }

    private void odstranZkosika() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Zadajte id kosika:");
        int kosikId = Integer.parseInt(br.readLine());

        Kosik kosik = NajdiKosik.getInstance().findById(kosikId);

        if (kosik == null) {
            System.out.println("Takyto kosik neexistuje");
        } else {
            kosik.delete();
            System.out.println("Vymazanie kosika a jeho prdouktov v nom prebehlo uspesne");
        }
    }


    private void vytvorObjednavku() throws SQLException, IOException, ObjednavkaException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(Kosik k : NajdiKosik.getInstance().findAll()){
            VypisKosik.getInstance().print(k);
        }

        System.out.println("Zadajte id zakaznika:");
        int zakaznikId = Integer.parseInt(br.readLine());
        List<Integer> IDkosikov = new ArrayList<>();
        for(Kosik q : NajdiKosik.getInstance().findByZakaznik_id(zakaznikId)){
            VypisKosik.getInstance().print(q);
            IDkosikov.add(q.getId());
        }
        if(zakaznikId<1 || String.valueOf(zakaznikId).equals("")){
            throw new IllegalArgumentException("Takyto zakaznik neexistuje");
        }

        System.out.println("Zadajte id produktu/ov cez oddelovac (,) bez meddzery:");
        List<Integer> produktId = new ArrayList<Integer>() ;
        String[] poleStringov = (br.readLine()).split(",");
        for(var s: poleStringov){
            produktId.add(Integer.parseInt(s));
        }

      //  System.out.println(produktId);

        System.out.println("Zadajte mnozstvo produktu cez oddelovac (,) bez meddzery VPORADI V AKOM STE ZADALI PRODUKTY:");
        List<Integer> mnozstvo = new ArrayList<Integer>() ;
        String[] poleStringovMn = (br.readLine()).split(",");
        for(var sMN: poleStringovMn){
            mnozstvo.add(Integer.parseInt(sMN));
        }
        //System.out.println(mnozstvo);

        System.out.println("Zadajte ulicu:");
        String ulica = br.readLine();

        System.out.println("Zadajte PSC:");
        String PSC = br.readLine();

        System.out.println("Zadajte mesto:");
        String mesto = (br.readLine());

        System.out.println("Zadajte krajinu:");
        String krajina = (br.readLine());

        Adresa a = new Adresa();
        a.setZakaznik_id(zakaznikId);
        a.setUlica(ulica);
        a.setPSC(PSC);
        a.setKrajina(krajina);
        a.setMesto(mesto);

        a.insert();

        Adresa na = NajdiAdresu.getInstance().findById(a.getId());

        int ideckoAdresy = a.getId();

        VytvorObjednavku o = new VytvorObjednavku();
        o.vloz(zakaznikId,produktId, ideckoAdresy, mnozstvo);

    }
    private void zrusObjednavku() throws IOException, SQLException, ObjednavkaException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Zadajte id zakaznika:");
        int zakaznikId = Integer.parseInt(br.readLine());

        for(Objednavka o : NajdiObjednavku.getInstance().findByZakaznik_id(zakaznikId)){
            VypisObjednavku.getInstance().print(o);
        }

        System.out.println("Zadajte id objednavky/iek, ktore chcete zrusit V TVARE s ',' BEZ MEDZERY:");
        List<Integer> IdObjednavok = new ArrayList<Integer>() ;
        String[] poleStringovObj = (br.readLine()).split(",");
        for(var sObj: poleStringovObj){
            IdObjednavok.add(Integer.parseInt(sObj));
        }


        ZrusenieObjednavky zo = new ZrusenieObjednavky();
        zo.zrus(zakaznikId,IdObjednavok);

    }

    private void zaplatObjednavku() throws IOException, SQLException, ObjednavkaException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Zadajte id zakaznika:");
        int zakaznikId = Integer.parseInt(br.readLine());
        if( NajdiObjednavku.getInstance().findByZakaznik_id(zakaznikId) != null && (NajdiObjednavku.getInstance().findByZakaznik_id(zakaznikId).size())!=0) {
            System.out.println(NajdiObjednavku.getInstance().findByZakaznik_id(zakaznikId));
            System.out.println(NajdiObjednavku.getInstance().findByZakaznik_id(zakaznikId).size());
            for (Objednavka o : NajdiObjednavku.getInstance().findByZakaznik_id(zakaznikId)) {
                VypisObjednavku.getInstance().print(o);
            }
        }else{
            throw new IllegalArgumentException("Tento zakaznik nema vytvorenu ziadnu objednavku");
        }

        System.out.println("Zadajte id objednavky ktoru chcete zaplatit (musi byt v stave VYTVORENA):");
        int objednavkaId = Integer.parseInt(br.readLine());

        System.out.println("Zadajte metodu platby: ");
        String metodPlatby = String.valueOf(br.readLine());

        System.out.println("Zadajte cislo karty: ");
        br.readLine();
        System.out.println("Zadajte CVVC kod karty: ");
        br.readLine();
        System.out.println("Zadajte platnost karty od V TVARE MM/YY: ");
        br.readLine();
        System.out.println("Zadajte overovaci kod: ");
        br.readLine();

        System.out.println("Zadajte sumu, ktoru chcete zaplatit: ");
        int suma = Integer.parseInt(br.readLine());
        ZaplatObjednavku zo = new ZaplatObjednavku();
        zo.zaplat(zakaznikId,objednavkaId,metodPlatby,suma);

    }

    private void expedovatObjednavku() throws IOException, SQLException, ObjednavkaException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Zadajte id objednavky, ktoru chcete expedovat MUSI BY ZAPLATENA:");
        int objednavkaID = Integer.parseInt(br.readLine());


        ExpedovanieObjednavky eo = new ExpedovanieObjednavky();
        eo.expeduj(objednavkaID);

    }
    private void vyhladajProdukt() throws SQLException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean bolProdukt = false;

        System.out.println("Zadajte zakaznik ID:");
        int zakaznikId = Integer.parseInt(br.readLine());

        System.out.println("Zadajte pojem na vyhladavanie:");
        String pojem = br.readLine();
        if(pojem.contains(" ")){
            pojem = pojem.replaceAll(" "," | ");
        }

        if(NajdiProdukt.getInstance().hladajIDpodlaPopisu(pojem.toLowerCase()) != 0){
            int produktId = NajdiProdukt.getInstance().hladajIDpodlaPopisu(pojem.toLowerCase());
            Produkt produkt = NajdiProdukt.getInstance().findById(produktId);
            VypisProdukt.getInstance().print(produkt);
            bolProdukt = true;

        }else if(NajdiAtributProduktu.getInstance().hladajIDpodlaHodnoty(pojem.toLowerCase()).size()!=0){
            List<Integer> pole = NajdiAtributProduktu.getInstance().hladajIDpodlaHodnoty(pojem.toLowerCase());
            VypisAtrbutProduktu.getInstance().print(pole);

        }else if(NajdiAtribut.getInstance().hladajIDpodlaAtrib(pojem.toLowerCase()).size() != 0){
            List<Integer> pole = NajdiAtribut.getInstance().hladajIDpodlaAtrib(pojem.toLowerCase());
            VypisAtribut.getInstance().print(pole);

        }else{
            System.out.println("Vasmu hladanemu slovu nevyhovuje ziaden z nasich produktov");
        }

        Dopyt dopyt = new Dopyt();
        int idPopisu = NajdiDopyt.getInstance().findIDByPopis(pojem.toLowerCase());
       // System.out.println(idPopisu + "idPopisu");
        if(idPopisu == 0){
            dopyt.setPopis_hladania(pojem.toLowerCase());
            dopyt.setPocet_opakovani(1);
            if(bolProdukt){
                dopyt.setJe_produkt(1);
            }else{
                dopyt.setJe_produkt(0);
            }

            dopyt.insert();
            Dopyt vykonanyD =NajdiDopyt.getInstance().findById(dopyt.getId());

            if(vykonanyD.getId() != null){
                System.out.println("Uspesne sme zapisali novy dopyt");
            }else{
                System.out.println("Niekde nastala chyba, pri zapisovani dopytu do tabulky");
            }

            Dopyt_zakaznika dz = new Dopyt_zakaznika();
            dz.setKedy(Timestamp.from(Instant.now()));
            dz.setDopyt_id(vykonanyD.getId());
            dz.setZakaznik_id(zakaznikId);
            dz.insert();
            Dopyt_zakaznika vykonanyDZ = NajdiDopytZakaznika.getInstance().findById(dz.getId());

            if(vykonanyDZ != null){
                System.out.println("Uspesne sme zapisali novy dopytZakaznika");
            }else{
                System.out.println("Niekde nastala chyba, pri zapisovani dopytuZakaznika do tabulky");
            }
        }else{
            Dopyt existujeDopyt = NajdiDopyt.getInstance().findById(idPopisu);
            existujeDopyt.setPocet_opakovani(existujeDopyt.getPocet_opakovani() + 1);
            existujeDopyt.update();
            System.out.println("updatovali sme stary dopyt");

            Dopyt_zakaznika dz = new Dopyt_zakaznika();
            dz.setKedy(Timestamp.from(Instant.now()));
            dz.setDopyt_id(existujeDopyt.getId());
            dz.setZakaznik_id(zakaznikId);
            dz.insert();
            Dopyt_zakaznika vykonanyDZ = NajdiDopytZakaznika.getInstance().findById(dz.getId());

            if(vykonanyDZ != null){
                System.out.println("Uspesne sme zapisali novy dopytZakaznika");
            }else{
                System.out.println("Niekde nastala chyba, pri zapisovani dopytuZakaznika do tabulky");
            }
        }




    }

    private void statistikaHladanie() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Zadajte rok: ");
        String rok = br.readLine();
        List<List<String>> spoluVsetko = NajdiHladaniaZaRok.getInstance().statistikaZaRok(rok);
        VypisStatistikuHladania.getInstance().print(spoluVsetko);

    }

    private void statistikaPoctuAtributov() throws SQLException {
        List<List<Integer>> spoluVsetko  = NajdiStatistikuProduktov.getInstance().statistikaProduktov();
        VypisStattistikuPodlaAtributov.getInstance().print(spoluVsetko);

    }


}
