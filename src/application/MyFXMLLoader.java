package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MyFXMLLoader {

    private static Stage primaryStage = null;

    private Stage actualStage = null;
    private Object controller = null;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public void loadFXML(String fxmlPath, String title) {
        this.loadFXML(fxmlPath, title, primaryStage);
    }

    public void loadFXML(String fxmlPath, String title, Stage parent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            actualStage = new Stage();

            actualStage.initOwner(parent);
            actualStage.initModality(Modality.APPLICATION_MODAL);

            actualStage.setTitle(title);
            actualStage.setScene(scene);
            actualStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Stage getActualStage() {
        return actualStage;
    }

    public Object getController() {
        return controller;
    }

}
