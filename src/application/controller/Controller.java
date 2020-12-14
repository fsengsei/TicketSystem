package application.controller;

import application.MyFXMLLoader;
import application.model.Ticket;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {

    public ListView<Ticket> ticket_ListView;
    public AnchorPane Content_Pane;


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
        Ticket selecteduser = null;

        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/ticket.fxml");

        Content_Pane.getChildren().add(root);

        TicketController controller = (TicketController) loader.getController();
        Ticket selected = ticket_ListView.getSelectionModel().getSelectedItem();
        controller.nameTextField.setText(selected.Name);
        controller.Idfield.setText(selected.ID);
        controller.DescTextField.setText(selected.Beschreibung);


    }

    public void initialize() {
        ticket_ListView.setItems(Ticket.loadTicketfile("tickets.csv"));
    }

}
