package app.rdg;

import app.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Adresa {
    private Integer id;
    private Integer zakaznik_id;
    private String ulica;
    private String PSC;
    private String mesto;
    private String krajina;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getZakaznik_id() {
        return zakaznik_id;
    }

    public void setZakaznik_id(Integer zakaznik_id) {
        this.zakaznik_id = zakaznik_id;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getPSC() {
        return PSC;
    }

    public void setPSC(String PSC) {
        this.PSC = PSC;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getKrajina() {
        return krajina;
    }

    public void setKrajina(String krajina) {
        this.krajina = krajina;
    }

    public void insert() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO adresa (zakaznik_id, ulica, PSC, mesto, krajina) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            s.setInt(1, zakaznik_id);
            s.setString(2, ulica);
            s.setString(3, PSC);
            s.setString(4, mesto);
            s.setString(5, krajina);
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

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE adresa SET zakaznik_id = ?, ulica = ?, PSC = ?, mesto = ?, krajina = ? WHERE id = ?")) {
            s.setInt(1, zakaznik_id);
            s.setString(2, ulica);
            s.setString(3, PSC);
            s.setString(4, mesto);
            s.setString(5, krajina);
            s.setInt(6, id);
            s.executeUpdate();
        }
    }

    public void delete() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id nie je nastavene");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM adresa WHERE id = ?")) {
            s.setInt(1, id);

            s.executeUpdate();
        }
    }
}
