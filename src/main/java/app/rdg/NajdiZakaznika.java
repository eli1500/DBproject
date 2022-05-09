package app.rdg;

import app.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NajdiZakaznika {
    private static final NajdiZakaznika INSTANCE = new NajdiZakaznika();

    public static NajdiZakaznika getInstance() {
        return INSTANCE;
    }
    private NajdiZakaznika(){}

    public Zakaznik findById(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM zakaznik WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Zakaznik c = new Zakaznik();

                    c.setId(r.getInt("id"));
                    c.setMeno(r.getString("meno"));
                    c.setPriezvisko(r.getString("priezvisko"));
                    c.setEmail(r.getString("email"));
                    c.setPrihlasovaciMeno(r.getString("prihlasovaciMeno"));
                    c.setHeslo(r.getString("heslo"));

                    if (r.next()) {
                        throw new RuntimeException("Chceli ste returnut viac nez 1 riadok");
                    }

                    return c;
                } else {
                    return null;
                }
            }
        }
    }

    public List<Zakaznik> findByKosik_id(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM kosik WHERE zakaznik_id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                List<Zakaznik> elements = new ArrayList<>();

                while (r.next()) {
                    elements.add(load(r));
                }

                return elements;
            }
        }
    }


    public List<Zakaznik> findAll() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM zakaznik")) {

            try (ResultSet r = s.executeQuery()) {

                List<Zakaznik> zakaznici = new ArrayList<>();

                while (r.next()) {
                    Zakaznik c = new Zakaznik();

                    c.setId(r.getInt("id"));
                    c.setMeno(r.getString("meno"));
                    c.setPriezvisko(r.getString("priezvisko"));
                    c.setEmail(r.getString("email"));
                    c.setPrihlasovaciMeno(r.getString("prihlasovaciMeno"));
                    c.setHeslo(r.getString("heslo"));

                    zakaznici.add(c);
                }

                return zakaznici;
            }
        }
    }

    protected Zakaznik load(ResultSet r) throws SQLException {
        Zakaznik c = new Zakaznik();

        c.setId(r.getInt("id"));
        c.setMeno(r.getString("meno"));
        c.setPriezvisko(r.getString("priezvisko"));
        c.setEmail(r.getString("email"));
        c.setPrihlasovaciMeno(r.getString("prihlasovaciMeno"));
        c.setHeslo(r.getString("heslo"));

        if (r.next()) { throw new RuntimeException("Neocakavana chyba"); }
        return c;
    }


}
