package application.controller;

import application.model.Status;
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

public class statuscontroller {
    public TextField statusTextField;
    public ListView<Status> listview;
    public Button saveButton;
    public Button closeButton;

    ObservableList<Status> list = FXCollections.observableArrayList();

    Status selectedstatus = null;

    public void initialize() {
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("stati.csv"));
            try {
                //br.readLine(); // ignoriere die erste Zeile => Überschriften

                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    Status a = new Status();

                    String[] words = s.split(";");
                    a.statinumber = words[0];
                    a.stati = words[1];

                    list.add(a); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
        }

        listview.setItems(list);
    }

    public void listviewclicked(MouseEvent mouseEvent) {
        Status selected = listview.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selectedstatus = selected;

            statusTextField.setText(selected.stati);
        }
    }

    public void saveOnClick(ActionEvent actionEvent) {
    }

    public void closeOnClick(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

