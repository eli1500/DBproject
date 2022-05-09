package app.rdg;

import app.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NajdiObjednavku {
    private static final NajdiObjednavku INSTANCE = new NajdiObjednavku();

    public static NajdiObjednavku getInstance() { return INSTANCE; }

    private NajdiObjednavku() { }

    public Objednavka findById(int id) throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement(
                "SELECT * FROM objednavka WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Objednavka c = new Objednavka();

                    c.setId(r.getInt("id"));
                    c.setZakaznik_id(r.getInt("zakaznik_id"));
                    c.setAdresa_id(r.getInt("adresa_id"));
                    c.setCena_spolu(r.getDouble("cena_spolu"));
                    c.setStav(r.getString("stav"));
                    c.setProdukt_id(r.getString("produkt_id"));
                    c.setMnozstvo(r.getString("mnozstvo"));

                    if (r.next()) {
                        throw new RuntimeException("Neocakavana chyba");
                    }
                    return c;

                } else return null;

            }
        }
    }

    public List<Objednavka> findByZakaznik_id(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM objednavka WHERE zakaznik_id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                List<Objednavka> elements = new ArrayList<>();

                while (r.next()) {
                    elements.add(load(r));
                }

                return elements;
            }
        }
    }



    protected Objednavka load(ResultSet r) throws SQLException {
        Objednavka c = new Objednavka();

        c.setId(r.getInt("id"));
        c.setZakaznik_id(r.getInt("zakaznik_id"));
        c.setAdresa_id(r.getInt("adresa_id"));
        c.setCena_spolu(r.getDouble("cena_spolu"));
        c.setStav(r.getString("stav"));
        c.setProdukt_id(r.getString("produkt_id"));
        c.setMnozstvo(r.getString("mnozstvo"));

        return c;
    }


}
