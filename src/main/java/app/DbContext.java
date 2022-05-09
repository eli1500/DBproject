package app;

import java.sql.Connection;

public class DbContext {
    private static Connection connection;

    public static void setConnection(Connection connection){
        if(connection == null){
            throw new NullPointerException("connection cant be null");
        }

        DbContext.connection = connection;
    }

    public static Connection getConnection() {
        if (connection == null) {
            throw new IllegalStateException("connection must be set before calling this method");
        }

        return connection;
    }

    public static void clear() {
        connection = null;
    }

}
