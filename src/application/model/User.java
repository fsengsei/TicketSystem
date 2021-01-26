package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class User {
    public Integer usernumber;
    public String titel;
    public String name;
    public String adress;
    public Integer zip;
    public String city;
    public Department Abteilung;
    public String abtnumber;
    public String abtname;

    public String toString() {
        return usernumber + "-" + name + "-" + abtnumber;
    }

    public User(int id, String title, String name, String street, int zip, String city, int departmentId) {
        this.usernumber = id;
        this.titel = title;
        this.name = name;
        this.adress = street;
        this.zip = zip;
        this.city = city;

        this.Abteilung = Department.getById(departmentId);
    }

    public static User getById(int id) {
        User u = null;

        try {
            Connection Connection = AccesDB.getConnection();

            Statement statement = null;
            statement = Connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM users WHERE user_id = " + id);

            if (results.next()) {
                 u = new User(results.getInt("user_id"),
                        results.getString("title"),
                        results.getString("name"),
                        results.getString("adress"),
                        results.getInt("zip"),
                        results.getString("city"),
                        results.getInt("department_id"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }

    public void update() {
        try {
            Connection Connection = AccesDB.getConnection();
            PreparedStatement statement = null;
            statement = Connection.prepareStatement("UPDATE users SET name = ? WHERE street = ? Where city = ? WHERE department = ?");
            statement.setString(1, name);
            statement.setString(2, adress);
            statement.setInt(3, zip);
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
                User u = new User(results.getInt("user_id"),
                        results.getString("title"),
                        results.getString("name"),
                        results.getString("adress"),
                        results.getInt("zip"),
                        results.getString("city"),
                        results.getInt("department_id"));
                list.add(u);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}