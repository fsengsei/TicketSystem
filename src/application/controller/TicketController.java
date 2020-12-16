package application.controller;

import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TicketController {

    public TextField nameTextField;
    public Button createButton;
    public TextArea DescTextField;
    public Button deleteButton;
    public ComboBox <Priority> priority_idComboBox;
    public ComboBox <Status> status_idComboBox;
    public ComboBox order_idComboBox;
    public TextField Idfield;
    private Ticket ticket;
    public void setTicket(Ticket t) {
        this.ticket = t;

        nameTextField.setText(t.Name);
        DescTextField.setText(t.Beschreibung);
        status_idComboBox.setItems(Status.loadStatusFile("stati.csv"));
        priority_idComboBox.setItems(Priority.loadPriorityFile("priorities.csv"));

        for (Status s : status_idComboBox.getItems()) {
            if (s.statinumber == t.Status.statinumber) {
                status_idComboBox.getSelectionModel().select(s);
                break;
            }
        }

        for (Priority p : priority_idComboBox.getItems()) {
            if (p.prioritaetsNummer == t.Priority.prioritaetsNummer) {
                priority_idComboBox.getSelectionModel().select(p);
                break;
            }
        }
    }
    public Ticket getTicket(){
        /**
         * aktualisieren der Ticket - Daten
         */
        ticket.Name = nameTextField.getText();
        ticket.ID = Idfield.getText();
        ticket.Beschreibung = DescTextField.getText();
        ticket.Status = (Status) status_idComboBox.getSelectionModel().getSelectedItem();
        ticket.Priority = (Priority) priority_idComboBox.getSelectionModel().getSelectedItem();
        return ticket;
    }




    public void closeOnClick(ActionEvent actionEvent) {
    }

    public void saveOnClick(ActionEvent actionEvent) {
    }
}
