package app.rdg;

import app.DbContext;

import java.sql.*;

public class Kosik {
    private Integer id;
    private Integer zakaznik_id;
    private Integer produkt_id;
    private Integer mnozstvo;
    private Timestamp kedy;
    private Double subtotal;

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

    public Integer getProdukt_id() {
        return produkt_id;
    }

    public void setProdukt_id(Integer produkt_id) {
        this.produkt_id = produkt_id;
    }

    public Integer getMnozstvo() {
        return mnozstvo;
    }

    public void setMnozstvo(Integer mnozstvo) {
        this.mnozstvo = mnozstvo;
    }

    public Timestamp getKedy() {
        return kedy;
    }

    public void setKedy(Timestamp kedy) {
        this.kedy = kedy;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public void insert() throws SQLException{
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO kosik (zakaznik_id, produkt_id, mnozstvo, kedy, subtotal) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            s.setInt(1, zakaznik_id);
            s.setInt(2, produkt_id);
            s.setInt(3, mnozstvo);
            s.setTimestamp(4, kedy);
            s.setDouble(5, subtotal);
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

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE kosik SET zakaznik_id = ?, produkt_id = ?, mnozstvo = ?, kedy = ?, subtotal = ? WHERE id = ?")) {
            s.setInt(1, zakaznik_id);
            s.setInt(2, produkt_id);
            s.setInt(3, mnozstvo);
            s.setTimestamp(4, kedy);
            s.setDouble(5, subtotal);
            s.setInt(6, id);
            s.executeUpdate();
        }
    }

    public void delete() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id nie je nastavene");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM kosik WHERE id = ?")) {
            s.setInt(1, id);

            s.executeUpdate();
        }
    }

    public void deleteByProduktID(int prID) throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id nie je nastavene");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM kosik WHERE produkt_id = ?")) {
            s.setInt(1, prID);

            s.executeUpdate();
        }
    }
}
