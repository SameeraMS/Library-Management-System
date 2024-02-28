package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

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

    public void signupOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("/view/register_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void forgotpassOnAction(MouseEvent mouseEvent) {
    }
}
