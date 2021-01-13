package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Status {
    public String statiNummer;
    public String stati;

    @Override
    public String toString() {
        return statiNummer + " - " + stati;
    }

    public static ObservableList <Status> loadlist(){
        ObservableList<Status> list = FXCollections.observableArrayList();


        try {
            Connection Connection = AccesDB.getConnection();
            Statement statement = null;

            statement = Connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM stati");

            while (results.next()){
                Status s = new Status();
                s.stati = results.getString("name");
                s.statiNummer = results.getString("status_id");
                list.add(s);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;



    }

    public static ObservableList<Status> loadStatusFile(String filename) {
        ObservableList<Status> result = FXCollections.observableArrayList();
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
            try {
                //br.readLine(); // ignoriere die erste Zeile => Überschriften

                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    Status a = new Status();

                    String[] words = s.split(";");
                    a.statiNummer = words[0];
                    a.stati = words[1];

                    result.add(a); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }

        return result;
    }
}