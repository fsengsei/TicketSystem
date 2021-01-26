package application.controller;

import application.MyFXMLLoader;
import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    public ListView<Ticket> ticket_ListView;
    public AnchorPane Content_Pane;
    public TextField filternametextfield; //filtern nach dem Namen des ToDos
    public ComboBox<Priority> filterprioritycombobox; // Filtern nach Status
    public ComboBox<Status> filterstatuscombobox; //Filtern nach Priority
    private TicketController active = null;

    private ArrayList<Ticket> allTickets;

    ObservableList<Ticket> list = null;
    ObservableList<Ticket> sortedlist = FXCollections.observableArrayList();
    private Ticket selectedTicket = null;

    /** Filter müssen UND - Verknüpft sein */
    public void filters() {
        String textfieldfilter = filternametextfield.getText();
        sortedlist = FXCollections.observableArrayList(allTickets);

        if (!textfieldfilter.equals("") || filterstatuscombobox.getValue() != null || filterprioritycombobox.getValue() != null) {
            if (textfieldfilter.length() > 0) {
                sortedlist.removeIf(ticket -> !ticket.Name.toLowerCase().contains(filternametextfield.getText().toLowerCase()));
            }

            if (filterstatuscombobox.getValue() != null) {
                sortedlist.removeIf(ticket -> ticket.Status.statiNummer.equals(filterstatuscombobox.getSelectionModel().getSelectedItem().statiNummer));
            }

            if (filterprioritycombobox.getValue() != null) {
                sortedlist.removeIf(ticket -> ticket.Priority.prioritaetsNummer.equals(filterprioritycombobox.getSelectionModel().getSelectedItem().prioritaetsNummer));
            }
        }

        ticket_ListView.setItems(sortedlist);
    }

    public void editStatiClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/status.fxml", "Stati bearbeiten");
    }

    public void editPrioritiesClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/priority.fxml", "Prioritäten bearbeiten");
    }

    public void editUserClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/user.fxml", "Benutzer bearbeiten");
    }

    public void editDepartmentClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/department.fxml", "Abteilung bearbeiten");
    }

    public void closeProgramClicked(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void ticketviewclicked(MouseEvent mouseEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/ticket.fxml");

        Content_Pane.getChildren().add(root);

        TicketController controller = (TicketController) loader.getController();
        Ticket selected = ticket_ListView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            controller.nameTextField.setText(selected.Name);
            controller.Idfield.setText(selected.ID.toString());
            controller.DescTextField.setText(selected.Beschreibung);
        }

        active = (TicketController) loader.getController();
        active.setTicket(ticket_ListView.getSelectionModel().getSelectedItem());
    }

    public void initialize() {
        filterstatuscombobox.setItems(Status.loadlist());
        filterprioritycombobox.setItems(Priority.loadlist());
        ticket_ListView.setItems(Ticket.loadlist());
        allTickets = new ArrayList<>(ticket_ListView.getItems());
        list = FXCollections.observableArrayList(ticket_ListView.getItems());
    }

    public void filterfieldreleased(KeyEvent keyEvent) {
        filters();
    }

    public void statuscomboboxclicked(ActionEvent actionEvent) {
        filters();
    }

    public void priorityComboboxclicked(ActionEvent actionEvent) {
        filters();
    }

    public void saveclicked(ActionEvent actionEvent) {
        //Wenn Ticket neu -> Laden des Tickets und hinzufügen in die Liste!
        //Datei aktualisieren

        Ticket selected = ticket_ListView.getSelectionModel().getSelectedItem();


        if (selected != null) {
            System.out.println("Tickets aktualisert");
            selected.Name = active.nameTextField.getText();
            selected.ID = Integer.parseInt(active.Idfield.getText());
            selected.Beschreibung = active.DescTextField.getText();
            selected.Status.statiNummer = active.status_idComboBox.getSelectionModel().getSelectedItem().statiNummer;
            selected.Priority.prioritaetsNummer = active.priority_idComboBox.getSelectionModel().getSelectedItem().prioritaetsNummer;

            ticket_ListView.refresh();
        } else {
            Ticket t = new Ticket();
            t.Status = new Status();
            t.Priority = new Priority();
            System.out.println("Neues Ticket");

            t.Name = active.nameTextField.getText();
            t.ID = Integer.parseInt(active.Idfield.getText());
            t.Beschreibung = active.DescTextField.getText();
            t.Status.statiNummer = active.status_idComboBox.getSelectionModel().getSelectedItem().statiNummer;
            t.Priority.prioritaetsNummer = active.priority_idComboBox.getSelectionModel().getSelectedItem().prioritaetsNummer;


            list.add(t);
            ticket_ListView.setItems(list);
            ticket_ListView.refresh();

        }


        writetoTicketfile();
    }

    public void deleteclicked(ActionEvent actionEvent) {
        //Laden des Tickets
        //Entfernen aus ListView
        //Datei aktualisieren
        Ticket selected = ticket_ListView.getSelectionModel().getSelectedItem();
        selectedTicket = selected;
        if (this.selectedTicket != null) {
            // Aktualisiere die Artikeldaten
            // (übernimm die aktuellen Daten von den Textfeldern)
            // und speichere alles in die Datei
            System.out.println("Daten gelöscht");
            list.remove(this.selectedTicket);

            ticket_ListView.setItems(list);
            ticket_ListView.refresh();

            writetoTicketfile();
            newclicked(actionEvent);
        }
    }

    public void newclicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/ticket.fxml");
        Content_Pane.getChildren().add(root);

        active = (TicketController) loader.getController();
        active.setTicket(null);
    }

    private void writetoTicketfile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("tickets.csv"));
            for (Ticket a : list) {
                bw.write(a.newCSVline());
                bw.newLine();
            }

            bw.flush();
            bw.close();
        } catch (IOException e) {

        }
    }
}
