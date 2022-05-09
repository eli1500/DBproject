package app.rdg;


import app.DbContext;

import javax.swing.plaf.SliderUI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NajdiSklad {
    private static final NajdiSklad INSTANCE = new NajdiSklad();

    public static NajdiSklad getInstance() {
        return INSTANCE;
    }

    private NajdiSklad() {}

    public Sklad findById(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM sklad WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Sklad c = load(r);

                    if (r.next()) { throw new RuntimeException("Neocakavana chyba"); }

                    return c;

                } else return null;
            }
        }
    }

    public int findPocetByProdukt_id(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM sklad WHERE produkt_id = ?")) {
            s.setInt(1, id);
            int skladove = 0;
            int rezervovane = 0;
            int id_skladu = 0;
            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Sklad c = load(r);
                     skladove = c.getPocet_ks();
                    rezervovane = c.getPocet_rezerv();
                    id_skladu = c.getId();

                }

//                return skladove - rezervovane;
                return id_skladu;
            }

        }
    }



    protected Sklad load(ResultSet r) throws SQLException {
        Sklad c = new Sklad();
        c.setId(r.getInt("id"));
        c.setProdukt_id(r.getInt("produkt_id"));
        c.setPocet_ks(r.getInt("pocet_ks"));
        c.setPocet_rezerv(r.getInt("pocet_rezerv"));

        return c;
    }
}
