package application.controller;

import application.model.Status;
import application.model.User;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserController {
    public TextField nameTextField;
    public TextField titleTextField;
    public TextField streetButton;
    public TextField ZipTextField;
    public TextField CityTextField;
    public ComboBox departmentComboBox;
    public TextField CountryTextField;
    public Button SaveButtom;
    public Button CloseButton;
    public ListView<User> listviewuser;
    ObservableList<User> list = FXCollections.observableArrayList();


    User selecteduser = null;

    public void initialize() {
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("users.csv"));
            try {
                //br.readLine(); // ignoriere die erste Zeile => Überschriften

                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile

                    User a = new User();


                    String[] words = s.split(";");
                    a.usernumber = words[0];
                    a.titel = words[1];
                    a.name = words[2];
                    a.adress = words[3];
                    a.zip = words[4];
                    a.city = words[5];
                    a.abtnumber = words[6];



                    list.add(a); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }

        listviewuser.setItems(list);
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
    }

    public void saveOnClick(ActionEvent actionEvent) {
    }
}
