package app;

import app.ts.ObjednavkaException;
import app.ui.MainMenu;
import org.postgresql.ds.PGSimpleDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setServerName("db.dai.fmph.uniba.sk");
        dataSource.setPortNumber(5432);
        dataSource.setDatabaseName("playground");
        dataSource.setUser("mattova22@uniba.sk");
        dataSource.setPassword("tobudeine123");

        try (Connection connection = dataSource.getConnection()) {
            DbContext.setConnection(connection);
            MainMenu mainMenu = new MainMenu();
            mainMenu.run();

        } catch (ObjednavkaException e) {
            e.printStackTrace();
        } finally {
            DbContext.clear();
        }
    }


}

//            System.out.println("jsem tady pripojen");
//            String query  = "SELECT employee_id, first_name, last_name FROM employees;";
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            while(rs.next()) {
//                int id = rs.getInt(1);
//                String firstName = rs.getString(2);
//                String lastName = rs.getString(3);
//                System.out.println(id + " " + firstName + " " + lastName);
//
//            }
//            rs.close();
//            statement.close();
