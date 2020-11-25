package application.controller;

import application.model.Priority;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class priority {
    public Button deleteButton;
    public Button sendButton;
    public TextField priorityTextField;
    public ListView<Priority> priorityList;
    ObservableList<Priority> list = FXCollections.observableArrayList();

    Priority selectedPriority = null;

    public void initialize() {
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("priorities.csv"));
            try {
                //br.readLine(); // ignoriere die erste Zeile => Überschriften

                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    Priority a = new Priority();

                    String[] words = s.split(";");
                    a.prioritaetsNummer = words[0];
                    a.prioritaetsText = words[1];

                    list.add(a); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }

        priorityList.setItems(list);
    }

    public void priorityListClicked(MouseEvent mouseEvent) {
        Priority selected = priorityList.getSelectionModel().getSelectedItem();

        if (selected != null) {
            this.selectedPriority = selected;

            priorityTextField.setText(selected.prioritaetsText);
        }
    }

    public void deletonClick(ActionEvent actionEvent) {
    }

    public void sendonCLick(ActionEvent actionEvent) {
    }
}
