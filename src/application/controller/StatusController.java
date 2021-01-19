package application.controller;

import application.model.Status;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StatusController {
    public TextField statusTextField;
    public ListView<Status> listview;
    public Button saveButton;
    public Button closeButton;

    Status selectedstatus = null;

    public void initialize() {
        listview.setItems(Status.loadlist());
    }

    public void listviewclicked(MouseEvent mouseEvent) {
        Status selected = listview.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selectedstatus = selected;

            statusTextField.setText(selected.stati);
        }
    }

    public void closeOnClick(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void saveOnClick(ActionEvent actionEvent) {
    }

    public void deleteClicked(ActionEvent actionEvent) {
        statusTextField.clear();
        listview.getItems().remove(selectedstatus);

        selectedstatus.delete();
    }
}