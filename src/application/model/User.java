package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class User {
    public String usernumber;
    public String titel;
    public String name;
    public String adress;
    public String zip;
    public String city;
    public Department Abteilung;
    public String abtnumber;
    public String abtname;

    public String toString() { return usernumber + "-" + name + "-" + abtnumber; }

    public static User getById (int id) {
        User u = null;

        try {
            Connection Connection = AccesDB.getConnection();

            Statement statement = null;
            statement = Connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM users WHERE user_id = " + id);

            if (results.next()) {
                u = new User();
                u.name = results.getString("name");
                u.usernumber = results.getString("user_id");
                u.adress = results.getString("street");
                u.zip = results.getString("zip");
                u.city = results.getString("city");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }

    public void update () {
        try {
            Connection Connection = AccesDB.getConnection();
            PreparedStatement statement = null;
            statement = Connection.prepareStatement("UPDATE users SET name = ? WHERE street = ? Where city = ? WHERE department = ?");
            statement.setString(1, name);
            statement.setString(2, adress);
            statement.setString(3, zip);
            statement.setString(4, city);
            statement.setString(5, abtnumber);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
                u.Abteilung = Department.getById(results.getInt("department_id"));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}