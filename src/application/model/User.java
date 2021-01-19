package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    public String usernumber;
    public String titel;
    public String name;
    public String adress;
    public String zip;
    public String city;
    public String abtnumber;
    public String abtname;

    public String toString() { return usernumber + "-" + name + "-" + abtnumber; }

    public void delete() {
        try {
            Connection Connection = AccesDB.getConnection();
            Statement statement = null;

            statement = Connection.createStatement();
            statement.executeUpdate("DELETE FROM users WHERE user_id = " + usernumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<User> loadlist() {
        ObservableList<User> list = FXCollections.observableArrayList();

        try {
            Connection Connection = AccesDB.getConnection();

            Statement statement = null;
            statement = Connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM users");

            while (results.next()) {
                User u = new User();
                u.name = results.getString("name");
                u.usernumber = results.getString("user_id");
                u.titel = results.getString("title");
                u.adress = results.getString("adress");
                u.zip = results.getString("zip");
                u.city = results.getString("city");
                u.abtnumber = results.getString("department_id");
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}

