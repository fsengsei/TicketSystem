package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Department {
    public String abteilungsNummer;
    public String abteilungsName;

    @Override
    public String toString() {
        return abteilungsNummer + " - " + abteilungsName;
    }


    public static ObservableList<Department> loadStatusFile(String filename) {
        ObservableList<Department> result = FXCollections.observableArrayList();
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("departments.csv"));
            try {
                //br.readLine(); // ignoriere die erste Zeile => Überschriften

                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    Department a = new Department();

                    String[] words = s.split(";");
                    a.abteilungsNummer = words[0];
                    a.abteilungsName = words[1];

                    result.add(a); // füge Artikel zur Liste hinzu


                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
        return result;

    }
}
