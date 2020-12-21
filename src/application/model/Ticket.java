package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ticket {
    public String ID;
    public String Name;
    public String Beschreibung;
    public Status Status;
    public Priority Priority;



    @Override
    public String toString() {
        return ID + "-" + Name;
    }


    public String newline(){
        return  ID + ";" + Name + ";" + Beschreibung + ";" + Status.statiNummer + ";" + Priority.prioritaetsNummer;
    }

    public static ObservableList<Ticket> loadTicketfile(String filename) {
        ObservableList<Ticket> result = FXCollections.observableArrayList();
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
            try {
                //br.readLine(); // ignoriere die erste Zeile => Überschriften

                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    Ticket a = new Ticket();

                    String[] words = s.split(";");
                    a.ID = words[0];
                    a.Name = words[1];
                    a.Beschreibung = words[2];
                    Status status  = new Status();
                    status.statiNummer = words[3];
                    a.Status = status;
                    application.model.Priority p = new Priority();
                    p.prioritaetsNummer = words[4];
                    a.Priority = p;

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
