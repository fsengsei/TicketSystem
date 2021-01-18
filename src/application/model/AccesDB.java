package application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesDB {
    static {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection connection = null;

    /**
     * Vereinfactes Singleton - Pattern
     */
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:ucanaccess://db/ToDo AA_fsengsei.accdb");
            } catch (SQLException e) {

            }
        }
        return connection;
    }
}
