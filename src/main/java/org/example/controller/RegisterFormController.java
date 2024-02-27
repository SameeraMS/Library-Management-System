package org.example.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RegisterFormController {
    public AnchorPane root;
    public TextField txtUsername;
    public ComboBox<String> cmbType;
    public TextField txtEmail;
    public TextField txtPassword;
    public TextField txtRePassword;

    public void loginOnAction(MouseEvent mouseEvent) {
    }

    public void signUpOnAction(ActionEvent actionEvent) {
    }

    public void passwordOnAction(ActionEvent actionEvent) {
        txtRePassword.requestFocus();
    }

    public void emailOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void usernameOnAction(ActionEvent actionEvent) {
        txtEmail.requestFocus();
    }
}
