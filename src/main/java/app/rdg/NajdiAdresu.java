package app.rdg;

import app.DbContext;

import javax.sql.XADataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NajdiAdresu {
    private static final NajdiAdresu INSTANCE = new NajdiAdresu();

    public static NajdiAdresu getInstance() {
        return INSTANCE;
    }
    private NajdiAdresu(){}

    public Adresa findById(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM adresa WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Adresa a = new Adresa();

                    a.setId(r.getInt("id"));
                    a.setZakaznik_id(r.getInt("zakaznik_id"));
                    a.setUlica(r.getString("ulica"));
                    a.setPSC(r.getString("PSC"));
                    a.setMesto(r.getString("mesto"));
                    a.setKrajina(r.getString("krajina"));

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


    public List<Adresa> findAll() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM adresa")) {

            try (ResultSet r = s.executeQuery()) {

                List<Adresa> adresy = new ArrayList<>();

                while (r.next()) {
                    Adresa a = new Adresa();

                    a.setId(r.getInt("id"));
                    a.setZakaznik_id(r.getInt("zakaznik_id"));
                    a.setUlica(r.getString("ulica"));
                    a.setPSC(r.getString("PSC"));
                    a.setMesto(r.getString("mesto"));
                    a.setKrajina(r.getString("krajina"));

                    adresy.add(a);
                }

                return adresy;
            }
        }
    }


}
