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

public class Controller {

    public ListView<Ticket> ticket_ListView;
    public AnchorPane Content_Pane;
    public TextField filternametextfield; //filtern nach dem Namen des ToDos
    public ComboBox filterprioritycombobox; // Filtern nach Status
    public ComboBox filterstatuscombobox; //Filtern nach Priority
    private TicketController active = null;
    ObservableList<Ticket> list;
    ObservableList<Ticket> sortedlist = FXCollections.observableArrayList();



    /**
     * Filter müssen UND - Verknüpft sein
     */
    public void filters() {

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

    }

    public void filterfieldreleased(KeyEvent keyEvent) {
        String textfieldfilter = filternametextfield.getText();
        sortedlist.clear();

        if (!textfieldfilter.equals("")) {
            for (Ticket ticket : list) {
                if (ticket.Name.contains(filternametextfield.getText())) {
                    sortedlist.add(ticket);
                }
            }

            ticket_ListView.setItems(sortedlist);
        } else {
            ticket_ListView.setItems(list);
        }
    }


    public void statuscomboboxclicked(ActionEvent actionEvent) {
 /*       String selectedstatus = filterstatuscombobox.getSelectionModel().getSelectedItem().toString();
        ObservableList<Ticket> list;
        list = filterstatuscombobox.getItems();
        if (!selectedstatus.equals("")){
            for (Ticket ticket : list){
                if (ticket.statinumber.contains(filterstatuscombobox.getItems().toString())){
                    sortedlist.add(status);
                }


            }
            ticket_ListView.setItems(sortedlist);
        } else {
            ticket_ListView.setItems(list);
        }
    }
    */
    }


    public void priorityComboboxclicked(ActionEvent actionEvent) {


    }

    public void saveclicked(ActionEvent actionEvent) {
        //Wenn Ticket neu -> Laden des Tickets und hinzufügen in die Liste!
        //Datei aktualisieren
    }

    public void deleteclicked(ActionEvent actionEvent) {
        //Laden des Tickets
        //Entfernen aus ListView
        //Datei aktualisieren
        if (this.selectedArticle != null) {
            // Aktualisiere die Artikeldaten
            // (übernimm die aktuellen Daten von den Textfeldern)
            // und speichere alles in die Datei
            list.remove(selectedArticle);

            System.out.println("Daten gelöscht");

            articleNumberTextField.clear();
            articleNameTextField.clear();
            articleDepotTextField.clear();
            articlePriceTextField.clear();


            articleList.refresh();
            articleList.setItems(list);
            writeArticlesToFile();
        }


    }

    public void newclicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/ticket.fxml");
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        Content_Pane.getChildren().add(root);

        TicketController controller = (TicketController) loader.getController();
        active = (TicketController) loader.getController();
        active.setTicket(null);
    }
}
