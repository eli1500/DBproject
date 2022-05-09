package app.rdg;

import app.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sklad {
    private Integer id;
    private Integer produkt_id;
    private Integer pocet_ks;
    private Integer pocet_rezerv;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProdukt_id() {
        return produkt_id;
    }

    public void setProdukt_id(Integer produkt_id) {
        this.produkt_id = produkt_id;
    }

    public Integer getPocet_ks() {
        return pocet_ks;
    }

    public void setPocet_ks(Integer pocet_ks) {
        this.pocet_ks = pocet_ks;
    }

    public Integer getPocet_rezerv() {
        return pocet_rezerv;
    }

    public void setPocet_rezerv(Integer pocet_rezerv) {
        this.pocet_rezerv = pocet_rezerv;
    }

    public void insert() throws SQLException {
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO sklad (produkt_id, pocet_ks, pocet_rezerv) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            s.setInt(1, produkt_id);
            s.setInt(2, pocet_ks);
            s.setInt(3, pocet_rezerv);


            s.executeUpdate();

            try (ResultSet r = s.getGeneratedKeys()) {
                r.next();
//                produkt_id = r.getInt(1);
//                pocet_ks = r.getInt(2);
//                pocet_rezerv = r.getInt(3);

                id = r.getInt(1);
            }
        }
    }

    public void update() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("id skladu nenexistuje");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE sklad SET produkt_id = ?, pocet_ks = ?, pocet_rezerv = ?  WHERE id = ? ")) {
            s.setInt(1, produkt_id);
            s.setInt(2, pocet_ks);
            s.setInt(3, pocet_rezerv);
            s.setInt(4, id);

            s.executeUpdate();
        }
    }
    public void deletePodlaProduktuID(Integer id) throws SQLException {
        if (this.id == null) {
            throw new IllegalStateException("Id nie je nastavene");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM sklad WHERE produkt_id = ?")) {
            s.setInt(1, id);

            s.executeUpdate();
        }
    }
}
