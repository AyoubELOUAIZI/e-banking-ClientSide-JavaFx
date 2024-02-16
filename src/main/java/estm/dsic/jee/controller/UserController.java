package estm.dsic.jee.controller;

import estm.dsic.jee.Models.User;
import estm.dsic.jee.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {

    public Label email_toshow;
    public Pane panelCurrentSoled;
    public Pane panelDeposer;
    public Pane panelRetrait;
    public Pane panelHistorique;
    public Button student_logout;
    public Pane panelTransfare;
    private User currentUser;


    @FXML
    public void initialize() {
        // Initialization logic here, like setting user details
       currentUser = UserSession.getCurrentUser();
        if (currentUser != null) {
            email_toshow.setText(currentUser.getUsername());
        }
    }


    public void handleLogout(ActionEvent actionEvent) {
        // Clear the session
        UserSession.clearSession();
       // clearLoginState();
        // Load the Authentication screen
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/estm/dsic/jee/fxml/AuthenticationScreen.fxml")); // replace with your actual path
            Parent root = loader.load();

            // Get the current stage from any control, like a button

            Stage stage = (Stage) student_logout.getScene().getWindow(); // replace 'someButton' with any @FXML injected control in your controller

            // Set the scene to the stage
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, perhaps show an error dialog
        }
    }


    public void handelBtnMonSold(ActionEvent actionEvent) {
        panelCurrentSoled.toFront();
    }

    public void handelGoToRetirer(ActionEvent actionEvent) {
        panelRetrait.toFront();
    }

    public void handelGotoTransfer(ActionEvent actionEvent) {
        panelTransfare.toFront();
    }

    public void handelGoToDeposet(ActionEvent actionEvent) {
        panelDeposer.toFront();
    }

    public void handelGoToHistorique(ActionEvent actionEvent) {
        panelHistorique.toFront();
    }
}

