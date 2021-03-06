package application.controller;

import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class TicketController {

    public TextField nameTextField;
    public Button createButton;
    public TextArea DescTextField;

    public ComboBox<Priority> priority_idComboBox;
    public ComboBox<Status> status_idComboBox;
    public ComboBox order_idComboBox;
    public TextField Idfield;
    public ComboBox<User> user_idComboBox;
    public ListView<User> selectedUserlistview;
    public Button addUser;
    private Ticket ticket;

    public void setTicket(Ticket t) {
        this.ticket = t;

        if (t != null) {
            nameTextField.setText(t.Name);
            DescTextField.setText(t.Beschreibung);
            status_idComboBox.setItems(Status.loadlist());
            priority_idComboBox.setItems(Priority.loadlist());
            user_idComboBox.setItems(User.loadlist());

            for (Status s : status_idComboBox.getItems()) {
                if (s.statiNummer.equals(t.Status.statiNummer)) {
                    status_idComboBox.getSelectionModel().select(s);
                    break;
                }
            }

            for (Priority p : priority_idComboBox.getItems()) {
                if (p.prioritaetsNummer.equals(t.Priority.prioritaetsNummer)) {
                    priority_idComboBox.getSelectionModel().select(p);
                    break;
                }
            }
            for (User u : user_idComboBox.getItems() ){
                if (u.usernumber.equals(t.user.usernumber)){
                    user_idComboBox.getSelectionModel().select(u);
                    break;
                }
            }
        } else {
            nameTextField.setText("");
            DescTextField.setText("");
            Idfield.setText("");

            status_idComboBox.setItems(Status.loadlist());
            priority_idComboBox.setItems(Priority.loadlist());
        }
    }

    public Ticket getTicket() {
        /** Aktualisieren der Ticket - Daten */
        ticket.Name = nameTextField.getText();
        ticket.ID = Integer.parseInt(Idfield.getText());
        ticket.Beschreibung = DescTextField.getText();
        ticket.Status = status_idComboBox.getSelectionModel().getSelectedItem();
        ticket.Priority = priority_idComboBox.getSelectionModel().getSelectedItem();
        return ticket;
    }


    public void closeOnClick(ActionEvent actionEvent) {
    }

    public void saveOnClick(ActionEvent actionEvent) {
    }

    public void addUser(ActionEvent actionEvent) {
        User selectedUser = user_idComboBox.getSelectionModel().getSelectedItem();

        if (selectedUser != null){
            selectedUserlistview.getItems().add(selectedUser);
            user_idComboBox.getItems().remove(selectedUser);
        }
    }

    public void removeUser(ActionEvent actionEvent) {
        User selectedUser = selectedUserlistview.getSelectionModel().getSelectedItem();

        if (selectedUser != null){
            selectedUserlistview.getItems().remove(selectedUser);
            user_idComboBox.getItems().add(selectedUser);
        }
    }
}
