package app.rdg;

import app.DbContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Zakaznik {
    private Integer id;
    private String meno;
    private String priezvisko;
    private String email;
    private String prihlasovaciMeno;
    private String heslo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrihlasovaciMeno() {
        return prihlasovaciMeno;
    }

    public void setPrihlasovaciMeno(String prihlasovaciMeno) {
        this.prihlasovaciMeno = prihlasovaciMeno;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public void insert() throws SQLException{
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO zakaznik (meno, priezvisko, email, prihlasovaciMeno, heslo) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            s.setString(1, meno);
            s.setString(2, priezvisko);
            s.setString(3, email);
            s.setString(4, prihlasovaciMeno);
            s.setString(5, heslo);
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

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE zakaznik SET meno = ?, priezvisko = ?, email = ?, prihlasovaciMeno = ?, heslo = ? WHERE id = ?")) {
            s.setString(1, meno);
            s.setString(2, priezvisko);
            s.setString(3, email);
            s.setString(4, prihlasovaciMeno);
            s.setString(5, heslo);
            s.setInt(6, id);
            s.executeUpdate();
        }
    }

    public void delete() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id nie je nastavene");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM zakaznik WHERE id = ?")) {
            s.setInt(1, id);

            s.executeUpdate();
        }
    }


}





