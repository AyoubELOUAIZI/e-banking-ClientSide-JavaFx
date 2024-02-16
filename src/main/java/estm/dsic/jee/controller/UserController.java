package estm.dsic.jee.controller;

import estm.dsic.jee.Models.User;
import estm.dsic.jee.UserSession;
import estm.dsic.jee.services.AccountService;
import estm.dsic.jee.services.AuthenticationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class UserController {

    public Label email_toshow;
    public Pane panelCurrentSoled;
    public Pane panelDeposer;
    public Pane panelRetrait;
    public Pane panelHistorique;
    public Button student_logout;
    public Pane panelTransfare;
    public Label tCurrentSold;
    public TextField tfDeposite;
    public TextField tfWithdrawAmount;
    public TextField tfAmountToSend;
    public TextField tfReciverEmail;
    private User currentUser;

    private AccountService accountService = (AccountService) Naming.lookup("rmi://localhost:52369/AccountService");

    public UserController() throws MalformedURLException, NotBoundException, RemoteException {
    }


    @FXML
    public void initialize() throws RemoteException {
        // Initialization logic here, like setting user details
       currentUser = UserSession.getCurrentUser();
        if (currentUser != null) {
            email_toshow.setText(currentUser.getUsername());
            tCurrentSold.setText(getCurrentBalance());
        }
    }
    

    public String getCurrentBalance() throws RemoteException {
        return String.valueOf(accountService.checkBalance(currentUser.getUserId()));
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

//    public void handelSendMoney(ActionEvent actionEvent) throws RemoteException {
//        accountService.transfer(currentUser.getUserId(),tfReciverEmail.getText().trim(),Double.parseDouble(tfAmountToSend.getText()));
//    }

    public void handelSendMoney(ActionEvent actionEvent) throws RemoteException {
        // Validate amount
        String amountText = tfAmountToSend.getText();
        if (amountText.isEmpty()) {
            showAlert("Amount field cannot be empty.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                showAlert("Amount must be a positive number.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid amount format. Please enter a valid number.");
            return;
        }

        // Validate receiver's email
        String receiverUsername = tfReciverEmail.getText().trim();
        if (receiverUsername.isEmpty() || !isValidEmail(receiverUsername)) {
            showAlert("Invalid receiver's email.");
            return;
        }

        // Perform money transfer
        boolean transferSuccess = accountService.transfer(currentUser.getUserId(), receiverUsername, amount);
        if (transferSuccess) {
            showAlert("Money transferred successfully.");
            // Clear input fields
            tfAmountToSend.clear();
            tfReciverEmail.clear();
            // Update UI
            tCurrentSold.setText(getCurrentBalance());
        } else {
            showAlert("Failed to transfer money. Please try again later.");
        }
    }

    private boolean isValidEmail(String email) {
        // Basic email validation
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void handelWithdraw(ActionEvent actionEvent) throws RemoteException {
      boolean isWithdrawOk= accountService.withdraw(currentUser.getUserId(),Double.parseDouble(tfWithdrawAmount.getText()));
      if(isWithdrawOk){
          //good message
          System.out.println("withdraw ok");
          //update the filed
          tfWithdrawAmount.setText("");
          //update the balance again
          tCurrentSold.setText(getCurrentBalance());
      }else{
          //bad message
          System.out.println("withdraw not ok");
      }
    }

    public void handelDeposet(ActionEvent actionEvent) throws RemoteException {
        boolean isDeposetOk= accountService.deposit(currentUser.getUserId(),Double.parseDouble(tfDeposite.getText()));
        if(isDeposetOk){
            //good message
            System.out.println("isDeposetOk ok");
            //update the filed
            tfDeposite.setText("");
            //update the balance again
            tCurrentSold.setText(getCurrentBalance());
        }else{
            //bad message
            System.out.println("isDeposetOk not ok");
        }
    }
}

