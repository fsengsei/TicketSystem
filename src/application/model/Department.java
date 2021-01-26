package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Department {
    public Integer abteilungsNummer;
    public String abteilungsName;



    public Department(int depid) {
        this.abteilungsName = Department.getById(depid).abteilungsName;
        this.abteilungsNummer = depid;

    }
    public Department(){
        this.abteilungsName = "";
        this.abteilungsNummer = 0;
    }
    @Override
    public String toString() {
        return abteilungsNummer + " - " + abteilungsName;
    }

    public static Department getById (int id) {
       Department d = null;

        try {
            Connection Connection = AccesDB.getConnection();

            Statement statement = null;
            statement = Connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM departments WHERE department_id = " + id);

            while (results.next()) {
                d = new Department();
                d.abteilungsName = results.getString("name");
                d.abteilungsNummer = results.getInt("department_id");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return d;
    }

    public void update() {
        try {
            Connection Connection = AccesDB.getConnection();
            PreparedStatement statement = null;
            statement = Connection.prepareStatement("UPDATE departments SET name = ? WHERE department_id = ?");
            statement.setString(1, abteilungsName);
            statement.setInt(2, abteilungsNummer);

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
            statement.executeUpdate("DELETE FROM departments WHERE ticket_id = " + abteilungsNummer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Department> loadlist() {
        ObservableList<Department> list = FXCollections.observableArrayList();

        try {
            Connection Connection = AccesDB.getConnection();
            Statement statement = null;

            statement = Connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM departments");

            while (results.next()) {
                Department d = new Department();
                d.abteilungsName = results.getString("name");
                d.abteilungsNummer = results.getInt("department_id");
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}

//    public static ObservableList<Department> loadStatusFile(String filename) {
//        ObservableList<Department> result = FXCollections.observableArrayList();
//        String s;
//        BufferedReader br = null;
//
//        try {
//            br = new BufferedReader(new FileReader("departments.csv"));
//            try {
//                //br.readLine(); // ignoriere die erste Zeile => Überschriften
//
//                while ((s = br.readLine()) != null) {
//                    // s enthält die gesamte Zeile
//                    Department a = new Department();
//
//                    String[] words = s.split(";");
//                    a.abteilungsNummer = words[0];
//                    a.abteilungsName = words[1];
//
//                    result.add(a); // füge Artikel zur Liste hinzu
//                }
//            } finally {
//                br.close();
//            }
//        } catch (IOException io) {
//            System.out.println(io.getMessage());
//        }
//
//        return result;
//    }