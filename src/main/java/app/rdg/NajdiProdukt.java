package app.rdg;

import app.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NajdiProdukt {
    private static final NajdiProdukt INSTANCE = new NajdiProdukt();

    public static NajdiProdukt getInstance() {
        return INSTANCE;
    }
    private NajdiProdukt(){}


    public Produkt findById(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM produkt WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Produkt p = new Produkt();

                    p.setId(r.getInt("id"));
                    p.setNazov(r.getString("nazov"));
                    p.setCena(r.getDouble("cena"));


                    if (r.next()) {
                        throw new RuntimeException("Chceli ste returnut viac nez 1 riadok");
                    }

                    return p;
                } else {
                    return null;
                }
            }
        }
    }
    public Double getCEna(int id_produktu) throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM produkt where produkt.cena = ?")) {
            s.setDouble(1, id_produktu);
//            s.setInt(2, id_produktu);
            try (ResultSet r = s.executeQuery()) {

                Produkt p = new Produkt();

                return p.getCena();

            }
        }
    }

    public List<Atribut> getNazovAtr(int id_attr) throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM atribut where atribut.id = ?")) {
            s.setInt(1, id_attr);
//            s.setInt(2, id_produktu);
            try (ResultSet r = s.executeQuery()) {

                List<Atribut> aq = new ArrayList<Atribut>();


                while (r.next()) {
                    Atribut q = new Atribut();
                    q.setId(r.getInt("id"));
                    q.setNazov(r.getString("nazov"));



                    aq.add(q);
                }
                for(var q : aq){
                   // System.out.println(q.getId());;
                    System.out.println("Nazov atributu:    "+ q.getNazov());
                }

                return aq;

            }
        }
    }

    public List<Integer> pomocna(int id_produktu) throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM atribut_produktu where atribut_produktu.produkt_id = ?")) {
            s.setInt(1, id_produktu);
//            s.setInt(2, id_produktu);
            try (ResultSet r = s.executeQuery()) {

                List<Integer> ap = new ArrayList<Integer>();

                while (r.next()) {
                    Atribut_Produktu p = new Atribut_Produktu();
                    p.setId(r.getInt("id"));
                    p.setProdukt_id(r.getInt("produkt_id"));
                    p.setAtribut_id(r.getInt("atribut_id"));
                    p.setHodnota(r.getString("hodnota"));


                    ap.add(p.getAtribut_id());
                }
//                for(var p : ap){
//                    getNazovAtr(p.getId());
//                    System.out.println("ID produktu:      "  +p.getProdukt_id());
//                    System.out.println("Hpdnota atributu: "+ p.getHodnota());
//                    System.out.println();
//                }
        //        System.out.println((ap));
                return ap;


            }
        }
    }



    public List<Produkt> findAll() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM produkt  " )) {
//                "JOIN atribut_produktu ap ON ap.produkt_id = p.id JOIN atribut a ON a.id = ap.atribut_id
            //p LEFT JOIN atribut_produktu ap ON ap.produkt_id = p.id

            try (ResultSet r = s.executeQuery()) {
                List<Atribut> aq = new ArrayList<Atribut>();
                List<Produkt> produkty = new ArrayList<>();


                while (r.next()) {
                    Produkt p = new Produkt();
//                    Atribut q = new Atribut(); //
//                    q.setId(r.getInt("id"));
//                    q.setNazov(r.getString("nazov"));

                    p.setId(r.getInt("id"));
                    p.setNazov(r.getString("nazov"));
                    p.setCena(r.getDouble("cena"));

                    produkty.add(p);
 //                   aq.add(q);
                }
                for(var p : produkty){
                    pomocna(p.getId());
                }

                return produkty;
            }
        }
    }

    public int hladajIDpodlaPopisu(String popis) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT id FROM produkt WHERE  to_tsvector(nazov) @@ to_tsquery(?)")) {
            s.setString(1, popis);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Produkt p = new Produkt();

                    p.setId(r.getInt("id"));
//                    d.setPopis_hladania(r.getString("popis_hladania"));
//                    d.setPocet_opakovani(r.getInt("pocet_opakovani"));


//                    if (r.next()) {
//                        throw new RuntimeException("Chceli ste returnut viac nez 1 riadok");
//                    }

                    return p.getId();
                } else {
                    return 0;
                }
            }
        }
    }


}