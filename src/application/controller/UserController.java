package application.controller;

import application.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UserController {
    public TextField nameTextField;
    public TextField titleTextField;
    public TextField streetButton;
    public TextField ZipTextField;
    public TextField CityTextField;
    public ComboBox departmentComboBox;
    public TextField CountryTextField;
    public Button saveButtom;
    public Button closeButton;
    public ListView<User> listviewuser;

    User selecteduser = null;

    public void initialize() {
        listviewuser.setItems(User.loadStatusFile("users.csv"));
    }


    public void listviewuserclicked(MouseEvent mouseEvent) {
        User selected = listviewuser.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selecteduser = selected;

            nameTextField.setText(selected.name);
            titleTextField.setText(selected.titel);
            streetButton.setText(selected.adress);
            ZipTextField.setText(selected.zip);
            CityTextField.setText(selected.city);
            CountryTextField.setVisible(false);
        }
    }

    public void closeOnCLick(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void saveOnClick(ActionEvent actionEvent) {
    }
}
