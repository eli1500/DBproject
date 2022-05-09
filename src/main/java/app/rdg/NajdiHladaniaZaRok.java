package app.rdg;

import app.DbContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NajdiHladaniaZaRok {
    private static final NajdiHladaniaZaRok INSTANCE = new NajdiHladaniaZaRok();

    public static NajdiHladaniaZaRok getInstance() {
        return INSTANCE;
    }

    private NajdiHladaniaZaRok() { }


    public List<List<String>> statistikaZaRok(String zadanyRok) throws SQLException {

        try (PreparedStatement s = DbContext.getConnection().prepareStatement(
                "\n" +
                        "with cte as\n" +
                        "(select\n" +
                        "    to_char(kedy,'YYYY-MM') as existujeZaznam,\n" +
                        "    count(je_produkt) as count_all,\n" +
                        "    count(je_produkt) filter ( where je_produkt = 0 ) as count_0\n" +
                        "    from dopyt join dopyt_zakaznika dz on dopyt.id = dz.dopyt_id\n" +
                        "where kedy >= ?::date at time zone 'UTC'\n" +
                        " and  kedy <= ?::date at time zone 'UTC'\n" +
                        "group by 1), cte2(yearmonth) as\n" +
                        "(select to_char(g,'YYYY-MM') from generate_series(?::date, ?::date, interval '1 month')  g)\n" +
                        "select yearmonth,\n" +
                        "        COALESCE(cte.existujeZaznam::varchar,'null') as existujezaznam,\n" +
                        "       COALESCE (cte.count_all::varchar, 'null') as pocetProduktov,\n" +
                        "      COALESCE( cte.count_0::varchar, 'null') as pocetNeproduktov,\n" +
                        " COALESCE(round(cte.count_0::numeric/count_all,2)::varchar, 'null') as pomer\n" +
                        "    from cte right join cte2 on cte.existujeZaznam = yearmonth;\n")) {
            s.setString(1, zadanyRok + "-01-01 00:00");
            s.setString(2,  "2022-05-09 23:59");
            s.setString(3, zadanyRok + "-01-01");
            s.setString(4, "2022-05-09");

            try (ResultSet r = s.executeQuery()) {

                List<List<String>> vsetky = new ArrayList<>();
                while (r.next()) {
                    List<String> poleJedenRiadok = new ArrayList<>();
                    poleJedenRiadok.add(r.getString(1));
                    poleJedenRiadok.add(r.getString(2));
                    poleJedenRiadok.add(r.getString(3));
                    poleJedenRiadok.add(r.getString(4));
                    poleJedenRiadok.add(r.getString(5));
                    vsetky.add(poleJedenRiadok);
                }



                return vsetky;
            }
        }
    }

}
