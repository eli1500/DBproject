package app.rdg;

import app.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dopyt {
    private Integer id;
    private String popis_hladania;
    private Integer pocet_opakovani;
    private Integer je_produkt;

    public Integer getJe_produkt() {
        return je_produkt;
    }

    public void setJe_produkt(Integer je_produkt) {
        this.je_produkt = je_produkt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPopis_hladania() {
        return popis_hladania;
    }

    public void setPopis_hladania(String popis_hladania) {
        this.popis_hladania = popis_hladania;
    }

    public Integer getPocet_opakovani() {
        return pocet_opakovani;
    }

    public void setPocet_opakovani(Integer pocet_opakovani) {
        this.pocet_opakovani = pocet_opakovani;
    }

    public void insert() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO dopyt (popis_hladania, pocet_opakovani, je_produkt) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            s.setString(1, popis_hladania);
            s.setInt(2, pocet_opakovani);
            s.setInt(3,je_produkt);

            s.executeUpdate();

            try (ResultSet r = s.getGeneratedKeys()) {
                r.next();
                id = r.getInt(1);
                popis_hladania = r.getString(2);
                pocet_opakovani = r.getInt(3);
                je_produkt = r.getInt(4);
            }
        }
    }


    public void update() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id nie je nastavene");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE dopyt SET popis_hladania = ?, pocet_opakovani = ?, je_produkt=? WHERE id = ?")) {
            s.setString(1, popis_hladania);
            s.setDouble(2, pocet_opakovani);
            s.setInt(3,je_produkt);
            s.setInt(4, id);
            s.executeUpdate();
        }
    }
}
