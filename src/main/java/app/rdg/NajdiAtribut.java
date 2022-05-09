package app.rdg;

import app.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NajdiAtribut {
    private static final NajdiAtribut INSTANCE = new NajdiAtribut();

    public static NajdiAtribut getInstance() {
        return INSTANCE;
    }
    private NajdiAtribut(){}

    public Atribut findById(int id) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM atribut WHERE id = ?")) {
            s.setInt(1, id);

            try (ResultSet r = s.executeQuery()) {
                if (r.next()) {
                    Atribut a = new Atribut();

                    a.setId(r.getInt("id"));
                    a.setNazov(r.getString("nazov"));


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


    public List<Atribut> findAll() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT * FROM atribut")) {

            try (ResultSet r = s.executeQuery()) {

                List<Atribut> atributy = new ArrayList<>();

                while (r.next()) {
                    Atribut a = new Atribut();

                    a.setId(r.getInt("id"));
                    a.setNazov(r.getString("nazov"));

                    atributy.add(a);
                }

                return atributy;
            }
        }
    }


    public List<Integer> hladajIDpodlaAtrib(String popis) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("SELECT id FROM atribut WHERE  to_tsvector(nazov) @@ to_tsquery(?)")) {
            s.setString(1, popis);

            try (ResultSet r = s.executeQuery()) {
                List<Integer> poleId = new ArrayList<>();

                while (r.next()) {
                    Atribut a = new Atribut();
                    a.setId(r.getInt("id"));


                    poleId.add(a.getId());
                }
                return poleId;
            }
        }
    }

}
