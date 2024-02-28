package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.bo.custom.impl.AdminBOImpl;
import org.example.dto.AdminDTO;

import java.io.IOException;
import java.net.URL;

public class RegisterFormController {
    public AnchorPane root;
    public TextField txtUsername;
    public ComboBox<String> cmbType;
    public TextField txtEmail;
    public TextField txtPassword;
    public TextField txtRePassword;

    AdminBO adminBoimpl = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);

    public void initialize() {
        cmbType.getItems().addAll("Admin", "User");
    }

    public void loginOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("/view/login_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void signUpOnAction(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String type = cmbType.getValue();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String rePassword = txtRePassword.getText();

        if (type.equals("Admin")) {
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "All fields are required").show();
            } else if (!password.equals(rePassword)) {
                new Alert(Alert.AlertType.ERROR, "Password does not match").show();
            } else {
                try {
                    adminBoimpl.save(new AdminDTO("001", username, email, password));
                    new Alert(Alert.AlertType.CONFIRMATION, "Register Successful").show();
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
            }
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
