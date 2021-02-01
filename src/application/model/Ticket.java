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
    public Integer ID;
    public String Name;
    public String Beschreibung;
    public Status Status;
    public Priority Priority;
    public ObservableList<User> AllUser = FXCollections.observableArrayList();
    public User user;
    public ObservableList<User> userslist = FXCollections.observableArrayList();

    @Override
    public String toString() {
        return ID + "-" + Name;
    }

    public Ticket(int id, String name, String desc, int priorityId, int statusid, int userid, ObservableList<User> userlist) {
        if (statusid != 0 && priorityId != 0){
            this.ID = id;
            this.Name = name;
            this.Beschreibung = desc;
            this.Status = application.model.Status.getById(statusid);
            this.Priority = application.model.Priority.getById(priorityId);
            this.userslist = userlist;
        }


    }
    public Ticket(){
        this.ID = 0;
        this.Name = "";
        this.Beschreibung = "";
        this.Priority = new Priority();
        this.Status = new Status();
    }

    public String newCSVline() {
        return ID + ";" + Name + ";" + Beschreibung + ";" + Status.statiNummer + ";" + Priority.prioritaetsNummer;
    }

    public static Ticket getById(int id) {
        Ticket t = null;

        try {
            Connection Connection = AccesDB.getConnection();

            Statement statement = null;
            statement = Connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM ticket WHERE ticket_id = " + id);

            if (results.next()) {
                t = new Ticket(results.getInt("ticket_id"),
                        results.getString("name"),
                        results.getString("descreption"),
                        results.getInt("status_id"),
                        results.getInt("priority_id"),
                        results.getInt("user_id"),
                        userToTickets(id));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return t;
    }

    public static ObservableList<User> userToTickets(int id){
        ObservableList<User> userList = FXCollections.observableArrayList();
        try {
            Connection connection = AccesDB.getConnection();
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM user_to_ticket WHERE ticket_id = " +id);

            while(result.next()){
                userList.add(User.getById(result.getInt("user_id")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userList;
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
                Ticket t = new Ticket(results.getInt("ticket_id"),
                        results.getString("name"),
                        results.getString("descreption"),
                        results.getInt("priority_id"),
                        results.getInt("status_id"),
                        results.getInt("user_id"),
                        userToTickets(results.getInt("ticket_id")));

                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

   /*
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
                    a.ID = Integer.parseInt(words[0]);
                    a.Name = words[1];
                    a.Beschreibung = words[2];
                    Status status = new Status();
                    status.statiNummer = Integer.parseInt(words[3]);
                    a.Status = status;
                    application.model.Priority p = new Priority();
                    p.prioritaetsNummer = Integer.parseInt(words[4]);
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
    */
}
