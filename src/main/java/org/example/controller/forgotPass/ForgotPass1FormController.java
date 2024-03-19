package org.example.controller.forgotPass;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.UserBO;
import org.example.dto.UserDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class ForgotPass1FormController {
    public TextField txtEmail;
    public AnchorPane root;
    public String email;

    UserBO userBo = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void sendOTPOnAction(ActionEvent actionEvent) {
        String mail = txtEmail.getText();

        if (mail.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter email address").show();
        } else {
            try {
                UserDTO search = userBo.search(mail);

                if (search == null) {
                    new Alert(Alert.AlertType.ERROR, "Invalid Email Address").show();
                } else {
                    email=mail;
                    loadNextForm();
                    new Alert(Alert.AlertType.CONFIRMATION, "OTP Sent").show();
                }
            } catch (SQLException | ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void loadNextForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/forgotPass2_form.fxml"));
        Parent main = fxmlLoader.load();

        ForgotPass2FormController f2 =  fxmlLoader.getController();
        f2.setEmail(email);
        f2.start();

        root.getChildren().clear();
        root.getChildren().add(main);

    }
}
