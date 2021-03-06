package application.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hsqldb.index.IndexAVLMemory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Priority {
    public Integer prioritaetsNummer;
    public String prioritaetsText;


    public Priority(int prioritaetsNummer, String prioritaetsText) {
        this.prioritaetsText =prioritaetsText;
        this.prioritaetsNummer = prioritaetsNummer;

    }
    public Priority(){
        this.prioritaetsText = "";
        this.prioritaetsNummer = 0;
    }

    @Override
    public String toString() {
        return prioritaetsNummer + " - " + prioritaetsText;
    }

    public void delete() {
        try {
            Connection Connection = AccesDB.getConnection();
            Statement statement = null;

            statement = Connection.createStatement();
            statement.executeUpdate("DELETE FROM priorities WHERE priority_id = " + prioritaetsNummer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Priority getById(int id) {
        Priority obj = null;
        try {
            Connection Connection = AccesDB.getConnection();
            Statement statement = null;

            statement = Connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM priorities WHERE priority_id=" + id);
            if (results.next()) {
                obj = new Priority(
                        results.getInt("priority_id"),
                        results.getString("name"));




            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }


    public void update() {


        try {
            Connection Connection = AccesDB.getConnection();
            PreparedStatement statement = null;
            statement = Connection.prepareStatement("UPDATE priorities SET name = ? WHERE priority_id = ?");
            statement.setString(1, prioritaetsText);
            statement.setInt(2, prioritaetsNummer);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static ObservableList<Priority> loadlist() {
        ObservableList<Priority> list = FXCollections.observableArrayList();

        try {
            Connection Connection = AccesDB.getConnection();
            Statement statement = null;

            statement = Connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM priorities");

            while (results.next()) {
                Priority p = new Priority();
                //    p = getbyid(Integer.parseInt(results.getInt("priority_id")));
                p.prioritaetsText = results.getString("name");
                p.prioritaetsNummer = results.getInt("priority_id");
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static ObservableList<Priority> loadPriorityFile(String filename) {
        ObservableList<Priority> result = FXCollections.observableArrayList();
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
            try {
                //br.readLine(); // ignoriere die erste Zeile => Überschriften

                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    Priority a = new Priority();

                    String[] words = s.split(";");
                    a.prioritaetsNummer = Integer.parseInt(words[0]);
                    a.prioritaetsText = words[1];

                    result.add(a); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (
                IOException io) {
            System.out.println(io.getMessage());
        }

        return result;
    }
}