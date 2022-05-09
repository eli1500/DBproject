package app.rdg;

import app.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Atribut {
    private Integer id;
    private String nazov;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public void insert() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO atribut (nazov) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            s.setString(1, nazov);

            s.executeUpdate();

            try (ResultSet r = s.getGeneratedKeys()) {
                r.next();
             //   nazov = r.getString(1);

                id = r.getInt(1);
            }
        }
    }


    public void update() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id nie je nastavene");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE atribut SET nazov = ?  WHERE id = ?")) {
            s.setString(1, nazov);
            s.setInt(2, id);
            s.executeUpdate();
        }
    }
}
