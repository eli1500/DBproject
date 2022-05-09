package app.ui;

import app.ts.ObjednavkaException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public abstract class Menu {

    private boolean exit;



    public void run() throws IOException, SQLException, ObjednavkaException {
        exit = false;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (!exit) {
            System.out.println();
            print();
            System.out.println();

            String line1 = br.readLine();
            if (line1 == null) {
                return;
            }

            MainMenu m = new MainMenu();
            m.druheMenuckoPRINT(line1);
            String line2 = br.readLine();
            if (line2 == null) {
                return;
            }

            System.out.println();

            handle(line2);
        }
    }

    public void exit() {
        exit = true;
    }

    public abstract void print();

    public abstract void handle(String option) throws SQLException, IOException, ObjednavkaException;

}
