package application.controller;

import application.model.Department;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class departmentController {
    public TextField departmentTextField;
    public Button saveButton;
    public Button closeButton;
    public ListView<Department> departmentList;

    Department selectedDepartment = null;

    public void initialize() {
        departmentList.setItems(Department.loadStatusFile("departments.csv"));
    }

    public void departmentListClicked(MouseEvent mouseEvent) {
        Department selected = departmentList.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selectedDepartment = selected;

            departmentTextField.setText(selected.abteilungsName);
        }
    }

    public void saveOnClick(ActionEvent actionEvent) {
    }

    public void closeOnClick(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
