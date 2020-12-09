package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Status {
    public String statinumber;
    public String stati;

    public String toString() {
        return statinumber + " - " + stati;
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
                    a.statinumber = words[0];
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