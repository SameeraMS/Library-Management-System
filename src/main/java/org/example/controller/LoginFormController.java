package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.bo.custom.UserBO;
import org.example.dto.AdminDTO;
import org.example.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane root;
    public TextField txtUsername;
    public TextField txtPassword;
    public ComboBox<String> cmbType;
    public PasswordField txtPassField;

    AdminBO adminBoImpl = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);
    UserBO userBoImpl = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
        txtPassword.setVisible(false);
        cmbType.getItems().addAll("Admin", "User");
    }

    public void usernameOnAction(ActionEvent actionEvent) {
        txtPassField.requestFocus();
    }

    public void passwordOnAction(ActionEvent actionEvent) {
        loginOnAction(actionEvent);
    }

    public void loginOnAction(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String type = cmbType.getValue();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty() || cmbType.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else {
            if (type.equals("User")) {
                try {
                    UserDTO search = userBoImpl.search(username);

                    if (search == null) {
                        new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
                    } else{
                        if (search.getPassword().equals(password)) {
                            login(search.getName(), "User");
                            new Alert(Alert.AlertType.CONFIRMATION, "Login Successful").show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
                        }
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            } else {
                try {
                    AdminDTO search = adminBoImpl.search(username);

                    if (search == null) {
                        new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
                    } else{
                        if (search.getPassword().equals(password)) {
                            login(search.getName(), "Admin");
                            new Alert(Alert.AlertType.CONFIRMATION, "Login Successful").show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
                        }
                    }
                } catch (SQLException | ClassNotFoundException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void login(String name, String status) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/mainDashboard_form.fxml"));
        Parent main = fxmlLoader.load();

        MainDashboardFormController dash =  fxmlLoader.getController();
        dash.lblUsername.setText(name);
        dash.lblWho.setText(status);
        dash.setEmail(txtUsername.getText());
        dash.setType(cmbType.getValue());
       // dash.btnHide();

        Scene scene = new Scene(main);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
        dash.initialize();
    }

    public void signupOnAction(MouseEvent mouseEvent) throws IOException {
        URL resource = getClass().getResource("/view/register_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void forgotpassOnAction(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/forgotPass1_form.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void showOnAction(ActionEvent actionEvent) {
        if (txtPassField.isVisible()) {
            txtPassField.setVisible(false);
            txtPassword.setVisible(true);
        } else {
            txtPassField.setVisible(true);
            txtPassword.setVisible(false);
        }
    }

    public void setToTxtFieldOnAction(KeyEvent event) {
        String text = txtPassField.getText();
        txtPassword.setText(text);
    }

    public void setToPassFieldOnAction(KeyEvent event) {
        String text = txtPassword.getText();
        txtPassField.setText(text);
    }

    public void passFieldOnAction(ActionEvent actionEvent) {
        loginOnAction(actionEvent);
    }
}
