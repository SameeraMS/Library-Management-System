package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;

public class AdminDashboardFormController {
    public AnchorPane changePain;
    public Label lblUsername;
    public AnchorPane root;
    @Setter
    public String user;

    public void initialize() {
        lblUsername.setText(user);
    }

    public void booksOnAction(ActionEvent actionEvent) {
        changeForm("/view/admin/bookMange_form.fxml");
    }

    public void branchOnAction(ActionEvent actionEvent) {
        changeForm("/view/admin/branch_form.fxml");
    }

    public void userOnAction(ActionEvent actionEvent) {
        changeForm("/view/admin/user_form.fxml");
    }

    public void borrowOnAction(ActionEvent actionEvent) {
        changeForm("/view/admin/borrow_form.fxml");
    }

    public void dashboardOnAction(ActionEvent actionEvent) {
        changeForm("/view/admin/dashboard_form.fxml");
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) root.getScene().getWindow();
        window.close();

        Parent node = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(node);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void changeForm(String form) {
        try{
            URL resource = this.getClass().getResource(form);
            Parent load = FXMLLoader.load(resource);
            changePain.getChildren().clear();
            changePain.getChildren().add(load);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
