package app.rdg;

import app.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NajdiDopyt {
    private static final NajdiDopyt INSTANCE = new NajdiDopyt();

    public static NajdiDopyt getInstance() {
        return INSTANCE;
    }

    private NajdiDopyt() {
    }

    public Dopyt findById(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM dopyt WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Dopyt d = new Dopyt();

                    d.setId(r.getInt("id"));
                    d.setPopis_hladania(r.getString("popis_hladania"));
                    d.setPocet_opakovani(r.getInt("pocet_opakovani"));
                    d.setJe_produkt(r.getInt("je_produkt"));


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

    public int findIDByPopis(String popis) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT id FROM dopyt WHERE popis_hladania = ?")) {
            s.setString(1, popis);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Dopyt d = new Dopyt();

                    d.setId(r.getInt("id"));
//                    d.setPopis_hladania(r.getString("popis_hladania"));
//                    d.setPocet_opakovani(r.getInt("pocet_opakovani"));


//                    if (r.next()) {
//                        throw new RuntimeException("Chceli ste returnut viac nez 1 riadok");
//                    }

                    return d.getId();
                } else {
                    return 0;
                }
            }
        }
    }


    public List<Dopyt> findAll() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM dopyt")) {

            try (ResultSet r = s.executeQuery()) {

                List<Dopyt> dopyty = new ArrayList<>();

                while (r.next()) {
                    Dopyt d = new Dopyt();

                    d.setId(r.getInt("id"));
                    d.setPopis_hladania(r.getString("popis_hladania"));
                    d.setPocet_opakovani(r.getInt("pocet_opakovani"));
                    d.setJe_produkt(r.getInt("je_produkt"));


                    dopyty.add(d);
                }

                return dopyty;
            }
        }
    }



}

