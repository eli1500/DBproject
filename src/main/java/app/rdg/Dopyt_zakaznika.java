package app.rdg;

import app.DbContext;

import java.sql.*;

public class Dopyt_zakaznika {
    private Integer id;
    private Integer zakaznik_id;
    private Integer dopyt_id;
    private Timestamp kedy; //timestamp

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

    public Integer getDopyt_id() {
        return dopyt_id;
    }

    public void setDopyt_id(Integer dopyt_id) {
        this.dopyt_id = dopyt_id;
    }

    public Timestamp getKedy() {
        return kedy;
    }

    public void setKedy(Timestamp kedy) {
        this.kedy = kedy;
    }

    public void insert() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO dopyt_zakaznika (zakaznik_id, dopyt_id, kedy) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            s.setInt(1,zakaznik_id);
            s.setInt(2, dopyt_id);
            s.setTimestamp(3,kedy);

            s.executeUpdate();

            try (ResultSet r = s.getGeneratedKeys()) {
                r.next();
                id = r.getInt(1);
                zakaznik_id = r.getInt(2);
                dopyt_id = r.getInt(3);
                kedy = r.getTimestamp(4);
            }
        }
    }
}
