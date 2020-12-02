package application.controller;

import application.model.Department;
import application.model.Priority;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class departmentController {
    public TextField departmentTextField;
    public Button saveButton;
    public Button closeButton;
    public ListView<Department> departmentList;
    ObservableList<Department> list = FXCollections.observableArrayList();

    Department selectedDepartment = null;

    public void initialize() {
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("departments.csv"));
            try {
                //br.readLine(); // ignoriere die erste Zeile => Überschriften

                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    Department a = new Department();

                    String[] words = s.split(";");
                    a.abteilungsNummer = words[0];
                    a.abteilungsName = words[1];

                    list.add(a); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }

        departmentList.setItems(list);
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
