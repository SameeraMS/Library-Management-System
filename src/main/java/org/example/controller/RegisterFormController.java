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
import org.example.bo.custom.BranchBO;
import org.example.bo.custom.UserBO;
import org.example.bo.custom.impl.AdminBOImpl;
import org.example.dto.AdminDTO;
import org.example.dto.BranchDTO;
import org.example.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class RegisterFormController {
    public AnchorPane root;
    public TextField txtUsername;
    public ComboBox<String> cmbType;
    public TextField txtEmail;
    public TextField txtPassword;
    public TextField txtRePassword;
    public ComboBox<String> cmbBranch;

    AdminBO adminBoImpl = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);
    UserBO userBoImpl = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    public void initialize() {
        cmbType.getItems().addAll("Admin", "User");
        cmbBranch.setVisible(false);

        try {
            for (BranchDTO branchDTO : branchBO.getAll()) {
                cmbBranch.getItems().add(branchDTO.getLocation());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty() || type.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else if (!password.equals(rePassword)) {
            new Alert(Alert.AlertType.ERROR, "Password does not match").show();
        } else {

            if (type.equals("User")) {
                if (cmbBranch.getValue() == null) {
                    new Alert(Alert.AlertType.ERROR, "Please select branch").show();
                } else {
                    try {
                        BranchDTO branchDTO = branchBO.searchByLocation(cmbBranch.getValue());
                        userBoImpl.save(new UserDTO(username, email, password, branchDTO));
                        new Alert(Alert.AlertType.CONFIRMATION, "Register Successful").show();
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

            } else {
                try {
                    adminBoImpl.save(new AdminDTO(username, email, password));
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

    public void cmbTypeOnAction(ActionEvent actionEvent) {
        if (cmbType.getValue().equals("User")) {
            cmbBranch.setVisible(true);
        } else {
            cmbBranch.setVisible(false);
        }
    }
}
