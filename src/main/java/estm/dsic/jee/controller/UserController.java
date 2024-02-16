package estm.dsic.jee.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class UserController {



    @FXML
    public void initialize() {
        // Initialization logic here, like setting user details
//        currentUser = UserSession.getCurrentUser();
//        if (currentUser != null) {
//            fullname_toshow.setText(currentUser.getFullName());
//            email_toshow.setText(currentUser.getEmail());
//            tUserEmail.setText(currentUser.getEmail());
//
//            //set the data for the Setting//
//            tUserName.setText(currentUser.getFullName());
//
//            // ... Set other user details
//            updateProfileImage(currentUser.getSexe());
//
//            //load the student Quizzes
//            loadStudentQuizzes();
//            // Listen for selection changes in the quizzesListView
//            quizzesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//                if (newValue != null) {
//                    panelQuizSelected.toFront();
//                    handleSelectedQuizChanged(newValue); // Call your method with the selected quiz name
//                }
//            });
//        }
    }


    public void handleLogout(ActionEvent actionEvent) {
    }

    public void handOpenQuizDetails(MouseEvent mouseEvent) {
    }

    public void handOpenQuizTest(MouseEvent mouseEvent) {
    }
}

