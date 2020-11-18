import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Controller {

    public void departmentonClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("department/Department.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("user");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {

        }
    }

    public void useronClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user/user.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("user");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {

        }
    }

    public void ticketonClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ticket/ticket.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("ticket");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {

        }
    }

    public void commentonClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("comment/Comment.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("user");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {

        }
    }

    public void statusonClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("status/status.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("user");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {

        }
    }

    public void orderonClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("order/order.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("user");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {

        }
    }

    public void priorityonClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("priority/priority.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setTitle("Priority");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {

        }
    }
}
