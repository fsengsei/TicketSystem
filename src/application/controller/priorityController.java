package application.controller;

import application.model.Priority;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class priorityController {
    public Button closeButton;
    public Button saveButton;
    public TextField priorityTextField;
    public ListView<Priority> priorityList;

    Priority selectedPriority = null;

    public void initialize() {
        priorityList.setItems(Priority.loadPriorityFile("priorities.csv"));
    }

    public void priorityListClicked(MouseEvent mouseEvent) {
        Priority selected = priorityList.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selectedPriority = selected;

            priorityTextField.setText(selected.prioritaetsText);
        }
    }

    public void saveOnClick(ActionEvent actionEvent) {
    }

    public void closeOnClick(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
