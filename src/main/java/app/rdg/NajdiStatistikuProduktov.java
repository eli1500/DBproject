package app.rdg;

import app.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NajdiStatistikuProduktov {
    private static final NajdiStatistikuProduktov INSTANCE = new NajdiStatistikuProduktov();

    public static NajdiStatistikuProduktov getInstance() {
        return INSTANCE;
    }

    private NajdiStatistikuProduktov() { }

    public List<Integer> vratPoleProduktov(List<Integer> pole){
        return pole;
    }
    public List<Integer> vratPoleAtributov(List<Integer> pole){
        return pole;
    }

    public List<List<Integer>> statistikaProduktov() throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement(
                "with stat1(pp, numberOfAtributtes) as (\n" +
                        "    select k.produkt_id as pp, count( ap.atribut_id) as numberOfAtributtes from atribut_produktu as ap\n" +
                        "    JOIN kosik k ON k.produkt_id = ap.produkt_id\n" +
                        "    GROUP BY  k.produkt_id )\n" +
                        "select count(numberOfAtributtes), numberOfAtributtes\n" +
                        "from stat1\n" +
                        "group by numberOfAtributtes;")) {

            try (ResultSet r = s.executeQuery()) {

                List<List<Integer>> vsetky = new ArrayList<>();
                while (r.next()) {
                    List<Integer> poleProduktov = new ArrayList<>();
                    poleProduktov.add(r.getInt(1));
                    poleProduktov.add(r.getInt(2));
                    vsetky.add(poleProduktov);
                }



                return vsetky;
            }
        }
    }

}
