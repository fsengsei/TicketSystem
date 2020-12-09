package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Priority {
    public String prioritaetsNummer;
    public String prioritaetsText;

    @Override
    public String toString() {
        return prioritaetsNummer + " - " + prioritaetsText;
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
                    a.prioritaetsNummer = words[0];
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