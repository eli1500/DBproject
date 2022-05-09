package app.rdg;

import app.DbContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Atribut_Produktu {
    private Integer id;
    private Integer atribut_id;
    private Integer produkt_id;
    private String hodnota;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAtribut_id() {
        return atribut_id;
    }

    public void setAtribut_id(Integer atribut_id) {
        this.atribut_id = atribut_id;
    }

    public Integer getProdukt_id() {
        return produkt_id;
    }

    public void setProdukt_id(Integer produkt_id) {
        this.produkt_id = produkt_id;
    }

    public String getHodnota() {
        return hodnota;
    }

    public void setHodnota(String hodnota) {
        this.hodnota = hodnota;
    }


    public void insert() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO atribut_produktu (atribut_id, produkt_id, hodnota) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            s.setInt(1,atribut_id);
            s.setInt(2,produkt_id);
            s.setString(3, hodnota);

            s.executeUpdate();

            try (ResultSet r = s.getGeneratedKeys()) {
                r.next();

                id = r.getInt(1);
            }
        }
    }

    public void update() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id nie je nastavene");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE atribut_produktu SET atribut_id = ?, produkt_id = ?, hodnota = ? WHERE id = ?")) {
            s.setInt(1, atribut_id);
            s.setInt(2, produkt_id);
            s.setString(3, hodnota);
            s.setInt(4, id);
            s.executeUpdate();
        }
    }

    public void deleteByProduktID(int prID) throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id nie je nastavene");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM atribut_produktu WHERE produkt_id = ?")) {
            s.setInt(1, prID);

            s.executeUpdate();
        }
    }
}
