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
    public ComboBox <Priority> filterprioritycombobox; // Filtern nach Status
    public ComboBox <Status> filterstatuscombobox; //Filtern nach Priority
    private TicketController active = null;

    private ArrayList<Ticket> allTickets;

    ObservableList<Ticket> list = null;
    ObservableList<Ticket> sortedlist = FXCollections.observableArrayList();
    private Ticket selectedTicket = null;


    /**
     * Filter müssen UND - Verknüpft sein
     */
   public void filters() {
       String textfieldfilter = filternametextfield.getText();
       sortedlist.clear();

       if (!textfieldfilter.equals("") || filterstatuscombobox != null || filterprioritycombobox != null) {
           sortedlist = FXCollections.observableArrayList();
           for (Ticket ticket : list) {
               if (ticket.Name.contains(filternametextfield.getText()) && ticket.Status.statinumber.equals(filterstatuscombobox.getSelectionModel().getSelectedItem().statinumber) && ticket.Priority.prioritaetsNummer.equals(filterprioritycombobox.getSelectionModel().getSelectedItem().prioritaetsNummer)) {
                   sortedlist.add(ticket);
               }
           }

           ticket_ListView.setItems(sortedlist);
       } else {
           ticket_ListView.setItems(list);
       }
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

        controller.nameTextField.setText(selected.Name);
        controller.Idfield.setText(selected.ID);
        controller.DescTextField.setText(selected.Beschreibung);

        active = (TicketController) loader.getController();
        active.setTicket(ticket_ListView.getSelectionModel().getSelectedItem());


    }

    public void initialize() {
        filterstatuscombobox.setItems(Status.loadStatusFile("stati.csv"));
        filterprioritycombobox.setItems(Priority.loadPriorityFile("priorities.csv"));
        ticket_ListView.setItems(Ticket.loadTicketfile("tickets.csv"));
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
        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/ticket.fxml");
        Content_Pane.getChildren().add(root);
        TicketController controller = (TicketController) loader.getController();

        Ticket selected = ticket_ListView.getSelectionModel().getSelectedItem();
        selectedTicket = selected;
        active = (TicketController) loader.getController();
        if (this.selectedTicket != null){
            System.out.println("Tickets aktualisert");
            selectedTicket.Name = controller.nameTextField.getText();
            selectedTicket.ID = controller.Idfield.getText();
            selectedTicket.Beschreibung = controller.DescTextField.getText();
            selectedTicket.Status = controller.status_idComboBox.getSelectionModel().getSelectedItem();
            selectedTicket.Priority = controller.priority_idComboBox.getSelectionModel().getSelectedItem();

            ticket_ListView.refresh();
        } else {
            System.out.println("Neues Ticket");

            Ticket ticket = new Ticket();
            ticket.Name = active.nameTextField.getText();
            ticket.ID = active.Idfield.getText();
            ticket.Beschreibung = active.DescTextField.getText();
            ticket.Status.statinumber = active.status_idComboBox.getSelectionModel().getSelectedItem().toString();
            ticket.Priority.prioritaetsNummer = active.priority_idComboBox.getSelectionModel().getSelectedItem().toString();

            list.add(ticket);
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

        TicketController controller = (TicketController) loader.getController();
        active = (TicketController) loader.getController();
        active.setTicket(null);
    }

    private void writetoTicketfile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("tickets.csv"));

            for (Ticket a : list) {

                bw.write(a.newline());
                bw.newLine();

            }
            bw.flush();

            bw.close();
        } catch (IOException e) {

        }
    }

}
