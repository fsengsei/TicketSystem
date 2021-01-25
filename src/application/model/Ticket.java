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

    public String newCSVline() {
        return ID + ";" + Name + ";" + Beschreibung + ";" + Status.statiNummer + ";" + Priority.prioritaetsNummer;
    }

    public static ObservableList<Ticket> getById (int id) {
        ObservableList<Ticket> list = FXCollections.observableArrayList();

        try {
            Connection Connection = AccesDB.getConnection();

            Statement statement = null;
            statement = Connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM ticket WHERE ticket_id = " + id);

            while (results.next()) {
                Ticket t = new Ticket();
                t.Name = results.getString("name");
                t.ID = results.getString("ticket_id");
                t.Beschreibung = results.getString("descreption");
                t.Status = results.getString("status_id");
                t.Priority = results.getString("priority_id");
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void delete() {
        try {
            Connection Connection = AccesDB.getConnection();
            Statement statement = null;

            statement = Connection.createStatement();
            statement.executeUpdate("DELETE FROM ticket WHERE ticket_id = " + ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Ticket> loadlist() {
        ObservableList<Ticket> list = FXCollections.observableArrayList();

        try {
            Connection Connection = AccesDB.getConnection();
            Statement statement = null;

            statement = Connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM ticket");

            while (results.next()) {
                Ticket t = new Ticket();
                t.Name = results.getString("name");
                t.Beschreibung = results.getString("descreption");
                application.model.Priority p = new Priority();
                p.prioritaetsNummer = results.getString("priority_id");
                Status status = new Status();
                status.statiNummer = results.getString("status_id");
                t.Priority = p;
                t.Status = status;
                t.ID = results.getString("ticket_id");
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ObservableList<Ticket> loadTicketfile(String filename) {
        ObservableList<Ticket> result = FXCollections.observableArrayList();
        String s;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            try {
                //br.readLine(); // ignoriere die erste Zeile => Überschriften

                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    Ticket a = new Ticket();

                    String[] words = s.split(";");
                    a.ID = words[0];
                    a.Name = words[1];
                    a.Beschreibung = words[2];
                    Status status = new Status();
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
