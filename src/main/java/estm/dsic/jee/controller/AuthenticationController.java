package estm.dsic.jee.controller;

import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import estm.dsic.jee.Models.User;
import estm.dsic.jee.UserSession;
import estm.dsic.jee.services.AuthenticationService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthenticationController {

   @FXML
    private TextField email_login;

    @FXML
    private PasswordField password_login;

   private AuthenticationService authenticationService = (AuthenticationService) Naming.lookup("rmi://localhost:52369/AuthenticationService");

    public AuthenticationController() throws MalformedURLException, NotBoundException, RemoteException {
    }

    @FXML
    public void initialize() {

    }

    public void handleLogin() {
        String email = email_login.getText();
        String password = password_login.getText();
        //just for testing
//        email="user1@gmail.com";
//        password="password1";

        try {
            // Call the remote method to authenticate the user
            User user = authenticationService.authenticateUser(email, password);

            // Handle the result
            if (user != null) {
                System.out.println("Authentication successful for user: " + user);
                // Proceed with application navigation or other actions
                UserSession.setCurrentUser(user);
                navigateToUserScreen();
            } else {
                System.out.println("Authentication failed.");
                // Display error message to the user

                // Login failed
                // Show an error message or take appropriate action
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText("Invalid email or password");
                alert.setContentText("Please try again.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception (e.g., display error message)
        }
    }

    private void navigateToUserScreen() {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/estm/dsic/jee/fxml/UserScreen.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

                // Close the current login window
                Stage currentStage = (Stage) email_login.getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        });
    }

}





