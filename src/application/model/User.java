package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    public static ObservableList<User> loadStatusFile(String filename) {
        ObservableList<User> result = FXCollections.observableArrayList();
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("users.csv"));
            try {
                //br.readLine(); // ignoriere die erste Zeile => Überschriften

                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile

                    User a = new User();
                    String[] words = s.split(";");
                    a.usernumber = words[0];
                    a.titel = words[1];
                    a.name = words[2];
                    a.adress = words[3];
                    a.zip = words[4];
                    a.city = words[5];
                    a.abtnumber = words[6];



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

