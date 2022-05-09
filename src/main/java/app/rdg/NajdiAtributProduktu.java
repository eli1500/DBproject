package app.rdg;

import app.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NajdiAtributProduktu {
    private static final NajdiAtributProduktu INSTANCE = new NajdiAtributProduktu();

    public static NajdiAtributProduktu getInstance() {
        return INSTANCE;
    }
    private NajdiAtributProduktu(){}

    public Atribut_Produktu findById(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM atribut_produktu WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Atribut_Produktu a = new Atribut_Produktu();

                    a.setId(r.getInt("id"));
                    a.setAtribut_id(r.getInt("atribut_id"));
                    a.setProdukt_id(r.getInt("produkt_id"));
                    a.setHodnota(r.getString("hodnota"));


                    if (r.next()) {
                        throw new RuntimeException("Chceli ste returnut viac nez 1 riadok");
                    }

                    return a;
                } else {
                    return null;
                }
            }
        }
    }


    public Atribut_Produktu findByProduktId(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM atribut_produktu WHERE produkt_id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Atribut_Produktu a = new Atribut_Produktu();

                    a.setId(r.getInt("id"));
                    a.setAtribut_id(r.getInt("atribut_id"));
                    a.setProdukt_id(r.getInt("produkt_id"));
                    a.setHodnota(r.getString("hodnota"));


//                    if (r.next()) {
//                        throw new RuntimeException("Chceli ste returnut viac nez 1 riadok");
//                    }

                    return a;
                } else {
                    return null;
                }
            }
        }
    }

    public Atribut_Produktu findByAtributId(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM atribut_produktu WHERE atribut_id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Atribut_Produktu a = new Atribut_Produktu();

                    a.setId(r.getInt("id"));
                    a.setAtribut_id(r.getInt("atribut_id"));
                    a.setProdukt_id(r.getInt("produkt_id"));
                    a.setHodnota(r.getString("hodnota"));


//                    if (r.next()) {
//                        throw new RuntimeException("Chceli ste returnut viac nez 1 riadok");
//                    }

                    return a;
                } else {
                    return null;
                }
            }
        }
    }

    public List<Atribut_Produktu> findAll() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM atribut_produktu")) {

            try (ResultSet r = s.executeQuery()) {

                List<Atribut_Produktu> atributyP = new ArrayList<>();

                while (r.next()) {
                    Atribut_Produktu ap = new Atribut_Produktu();

                    ap.setId(r.getInt("id"));
                    ap.setAtribut_id(r.getInt("atribut_id"));
                    ap.setProdukt_id(r.getInt("produkt_id"));
                    ap.setHodnota(r.getString("hodnota"));


                    atributyP.add(ap);
                }

                return atributyP;
            }
        }
    }

    public List<Integer> hladajIDpodlaHodnoty(String popis) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT id FROM atribut_produktu WHERE  to_tsvector(hodnota) @@ to_tsquery(?)")) {
            s.setString(1, popis);

            try (ResultSet r = s.executeQuery()) {
                List<Integer> poleId = new ArrayList<>();

                while (r.next()) {
                    Atribut_Produktu ap = new Atribut_Produktu();
                    ap.setId(r.getInt("id"));
//                    ap.setHodnota(r.getString("hodnota"));
//                    ap.setAtribut_id(r.getInt("atribut_id"));
//                    ap.setProdukt_id(r.getInt("produkt_id"));


                    poleId.add(ap.getId());

                }
               // System.out.println(poleId);
                return poleId;

            }
        }
    }
}
