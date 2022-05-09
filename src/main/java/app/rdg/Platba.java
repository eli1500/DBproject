package app.rdg;

import app.DbContext;

import java.sql.*;

public class Platba {
    private Integer id;
    private Integer objednavka_id;
    private Integer zakaznik_id;
    private Integer suma_platby;
    private Date datum;
    private String status_platby;
    private String metoda_platby;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObjednavka_id() {
        return objednavka_id;
    }

    public void setObjednavka_id(Integer objednavka_id) {
        this.objednavka_id = objednavka_id;
    }

    public Integer getZakaznik_id() {
        return zakaznik_id;
    }

    public void setZakaznik_id(Integer zakaznik_id) {
        this.zakaznik_id = zakaznik_id;
    }

    public Integer getSuma_platby() {
        return suma_platby;
    }

    public void setSuma_platby(Integer suma_platby) {
        this.suma_platby = suma_platby;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getStatus_platby() {
        return status_platby;
    }

    public void setStatus_platby(String status_platby) {
        this.status_platby = status_platby;
    }

    public String getMetoda_platby() {
        return metoda_platby;
    }

    public void setMetoda_platby(String metoda_platby) {
        this.metoda_platby = metoda_platby;
    }

    public void insert() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO platba (objednavka_id, zakaznik_id, suma_platby, datum, metoda_platby, status_platby) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            s.setInt(1, objednavka_id);
            s.setInt(2, zakaznik_id);
            s.setInt(3, suma_platby);
            s.setObject(4, datum);
            s.setString(5, metoda_platby);
            s.setString(6, status_platby);
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

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE platba SET objednavka_id = ?, zakaznik_id = ?, suma_platby = ?, datum = ?, metoda_platby = ?, status_platby = ? WHERE id = ?")) {
            s.setInt(1, objednavka_id);
            s.setInt(2, zakaznik_id);
            s.setInt(3, suma_platby);
            s.setObject(4, datum);
            s.setString(5, metoda_platby);
            s.setString(6, status_platby);
            s.setInt(7, id);
            s.executeUpdate();
        }
    }



}
