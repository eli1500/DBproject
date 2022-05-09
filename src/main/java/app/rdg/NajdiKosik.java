package app.rdg;

import app.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NajdiKosik {

    private static final NajdiKosik INSTANCE = new NajdiKosik();

    public static NajdiKosik getInstance() { return INSTANCE; }

    private NajdiKosik() { }

    public Kosik findById(int id) throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM kosik WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Kosik c = new Kosik();

                    c.setId(r.getInt("id"));
                    c.setZakaznik_id(r.getInt("zakaznik_id"));
                    c.setProdukt_id(r.getInt("produkt_id"));
                    c.setMnozstvo(r.getInt("mnozstvo"));
                    c.setKedy(r.getTimestamp("kedy"));
                    c.setSubtotal(r.getDouble("subtotal"));

                    if (r.next()) {
                        throw new RuntimeException("Chceli ste returnut viac nez 1 riadok");
                    }
                    return c;

                } else return null;

            }
        }
    }
    public Integer findByZakaznik_idProdukt_id(int zak_id, int produkt_id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT id FROM kosik WHERE zakaznik_id = ? AND produkt_id = ?")) {
            s.setInt(1,zak_id);
            s.setInt(2,produkt_id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Kosik k = new Kosik(); //load(r);
                    k.setId(r.getInt("id"));

                    if (r.next()) { throw new RuntimeException("CHceli ste returnut vic ako 1 dopyt"); }

                    return k.getId();

                } else return null;
            }
            }
        }


    public List<Kosik> findByZakaznik_id(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM kosik WHERE zakaznik_id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                List<Kosik> elements = new ArrayList<>();

                while (r.next()) {
                    elements.add(load(r));
                }

                return elements;
            }
        }
    }


    public List<Kosik> findByProdukt_id(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM kosik WHERE produkt_id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                List<Kosik> elements = new ArrayList<>();

                while (r.next()) {
                    elements.add(load(r));
                }

                return elements;
            }
        }
    }


    protected Kosik load(ResultSet r) throws SQLException {
        Kosik c = new Kosik();

        c.setId(r.getInt("id"));
        c.setZakaznik_id(r.getInt("zakaznik_id"));
        c.setProdukt_id(r.getInt("produkt_id"));
        c.setMnozstvo(r.getInt("mnozstvo"));
        c.setKedy(r.getTimestamp("kedy"));
        c.setSubtotal(r.getDouble("subtotal"));

        //if (r.next()) { throw new RuntimeException("Chceli ste returnut viac nez 1 riadok chyba"); }
        return c;
    }

    public List<Kosik> findAll() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM kosik")) {

            try (ResultSet r = s.executeQuery()) {

                List<Kosik> kosiks = new ArrayList<>();

                while (r.next()) {
                    Kosik c = new Kosik();

                    c.setId(r.getInt("id"));
                    c.setZakaznik_id(r.getInt("zakaznik_id"));
                    c.setProdukt_id(r.getInt("produkt_id"));
                    c.setMnozstvo(r.getInt("mnozstvo"));
                    c.setKedy(r.getTimestamp("kedy"));
                    c.setSubtotal(r.getDouble("subtotal"));

                    kosiks.add(c);
                }

                return kosiks;
            }
        }
    }
}
