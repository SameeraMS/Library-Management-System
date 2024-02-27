package org.example.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginFormController {
    public AnchorPane root;
    public TextField txtUsername;
    public TextField txtPassword;

    public void usernameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void passwordOnAction(ActionEvent actionEvent) {
        loginOnAction(actionEvent);
    }

    public void loginOnAction(ActionEvent actionEvent) {
    }

    public void signupOnAction(MouseEvent mouseEvent) {
    }

    public void forgotpassOnAction(MouseEvent mouseEvent) {
    }
}
