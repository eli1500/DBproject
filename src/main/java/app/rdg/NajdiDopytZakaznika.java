package app.rdg;

import app.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NajdiDopytZakaznika {
    private static final NajdiDopytZakaznika INSTANCE = new NajdiDopytZakaznika();

    public static NajdiDopytZakaznika getInstance() {
        return INSTANCE;
    }

    private NajdiDopytZakaznika() {
    }

    public Dopyt_zakaznika findById(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM dopyt_zakaznika WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Dopyt_zakaznika d = new Dopyt_zakaznika();

                    d.setId(r.getInt("id"));
                    d.setZakaznik_id(r.getInt("zakaznik_id"));
                    d.setDopyt_id(r.getInt("dopyt_id"));
                    d.setKedy(r.getTimestamp("kedy"));


                    if (r.next()) {
                        throw new RuntimeException("Chceli ste returnut viac nez 1 riadok");
                    }

                    return d;
                } else {
                    return null;
                }
            }
        }
    }


}
