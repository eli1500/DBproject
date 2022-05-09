package app.rdg;

import app.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Produkt {
    private Integer id;
    private String nazov;
    private List<Atribut> poleAtributov;
    private List<Atribut_Produktu> poleAtributovPrudktov;
    private Double cena;

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

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
        try (PreparedStatement s = DbContext.getConnection().prepareStatement("INSERT INTO produkt (nazov, cena) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            s.setString(1, nazov);
            s.setDouble(2, cena);

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

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("UPDATE produkt SET nazov = ?, cena = ? WHERE id = ?")) {
            s.setString(1, nazov);
            s.setDouble(2, cena);
            s.setInt(3, id);
            s.executeUpdate();
        }
    }

    public void delete() throws SQLException {
        if (id == null) {
            throw new IllegalStateException("Id nie je nastavene");
        }

        try (PreparedStatement s = DbContext.getConnection().prepareStatement("DELETE FROM produkt WHERE id = ?" )) { // "DELETE FROM sklad WHERE produkt_id = ?;"
            s.setInt(1, id);
//            s.setInt(2, id);
            int IDsklad = NajdiSklad.getInstance().findPocetByProdukt_id(id);
            Sklad sklad = NajdiSklad.getInstance().findById(IDsklad);
            if(sklad != null){
                sklad.deletePodlaProduktuID(id);
            }
            List<Kosik> listKosikovSproduktami = NajdiKosik.getInstance().findByProdukt_id(id);
            for(var k : listKosikovSproduktami){

                if(k != null){
                    k.deleteByProduktID(id);
                }

            }
            Atribut_Produktu atribut_produktu= NajdiAtributProduktu.getInstance().findByProduktId(id);
            if(atribut_produktu != null){
                atribut_produktu.deleteByProduktID(id);
            }


            s.executeUpdate();
        }
    }

}
