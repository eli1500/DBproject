package app.rdg;

import app.DbContext;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NajdiPlatbu {
    private static final NajdiPlatbu INSTANCE = new NajdiPlatbu();

    public static NajdiPlatbu getInstance() {
        return INSTANCE;
    }

    private NajdiPlatbu() {}

    public Platba findById(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM platba WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Platba c = load(r);

                    if (r.next()) { throw new RuntimeException("Neocakavana chyba"); }

                    return c;

                } else return null;
            }
        }
    }



    protected Platba load(ResultSet r) throws SQLException {
        Platba c = new Platba();

        c.setId(r.getInt("id"));
        c.setObjednavka_id(r.getInt("objednavka_id"));
        c.setZakaznik_id(r.getInt("zakaznik_id"));
        c.setSuma_platby(r.getInt("suma_platby"));
        c.setDatum(r.getDate("datum"));
        c.setStatus_platby(r.getString("status_platby"));
        c.setMetoda_platby(r.getString("metoda_platby"));

        return c;
    }
}
