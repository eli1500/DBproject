package app.rdg;

import app.DbContext;
import org.postgresql.jdbc.PgArray;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Objednavka {
    private Integer id;
    private Integer zakaznik_id;
    private Integer adresa_id;
    private Double cena_spolu;
    private String stav;
    private String produkt_id;
    private String mnozstvo;


    public String getProdukt_id() {
        return produkt_id;
    }

    public void setProdukt_id(String produkt_id) {
        this.produkt_id = produkt_id;
    }

    public String getMnozstvo() {
        return mnozstvo;
    }

    public void setMnozstvo(String mnozstvo) {
        this.mnozstvo = mnozstvo;
    }

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

    public Integer getAdresa_id() {
        return adresa_id;
    }

    public void setAdresa_id(Integer adresa_id) {
        this.adresa_id = adresa_id;
    }

    public Double getCena_spolu() {
        return cena_spolu;
    }

    public void setCena_spolu(Double cena_spolu) {
        this.cena_spolu = cena_spolu;
    }

    public String getStav() {
        return stav;
    }

    public void setStav(String stav) {
        this.stav = stav;
    }

    public void insert() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO objednavka (zakaznik_id, adresa_id,cena_spolu, stav, produkt_id, mnozstvo) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            s.setInt(1, zakaznik_id);
            s.setInt(2, adresa_id);
            s.setDouble(3, cena_spolu);
            s.setString(4, stav);
            s.setString(5, produkt_id);
            s.setString(6,mnozstvo);

            s.executeUpdate();

            try (ResultSet r = s.getGeneratedKeys()) {
                r.next();
                id = r.getInt(1);
                zakaznik_id = r.getInt(2);
                adresa_id = r.getInt(3);
                cena_spolu = r.getDouble(4);
                stav = r.getString(5);
                produkt_id = r.getString(6);
                mnozstvo = r.getString(7);
            }
        }
    }

    public void update() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id nie je nastavene");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE objednavka SET zakaznik_id = ?, adresa_id = ?, cena_spolu = ?, stav = ?, produkt_id = ?, mnozstvo = ? WHERE id = ?")) {
            s.setInt(1, zakaznik_id);
            s.setInt(2, adresa_id);
            s.setDouble(3, cena_spolu);
            s.setString(4, stav);
            s.setString(5, produkt_id);
            s.setString(6, mnozstvo);
            s.setInt(7,id);
            s.executeUpdate();
        }
    }

    public void delete() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("id neexistuje");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM objednavka WHERE id = ?")) {
            s.setInt(1, id);

            s.executeUpdate();
        }
    }
}
