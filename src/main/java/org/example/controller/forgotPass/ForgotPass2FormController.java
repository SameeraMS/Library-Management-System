package org.example.controller.forgotPass;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lombok.Setter;
import org.example.util.Mail;


import java.io.IOException;
import java.util.Random;

public class ForgotPass2FormController {
    public TextField txtOtp;
    public AnchorPane root;
    public int otp;

    @Setter
    public String email;

    public void initialize() {
        otp = new Random().nextInt(900000) + 100000;
        System.out.println(otp);

    }

    public void start(){
        Mail mail = new Mail();
        mail.setMsg("Your OTP is " + otp);
        mail.setTo(email);
        mail.setSubject("Library Management System");

        Thread thread = new Thread(mail);
        thread.start();
    }
    public void btnSubmitOnAction(ActionEvent actionEvent) {
        if (txtOtp.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter OTP").show();
        } else {
            if (txtOtp.getText().equals(String.valueOf(otp))) {
                loadNextForm();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid OTP").show();
            }
        }

    }

    private void loadNextForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/forgotPass3_form.fxml"));
            Parent main = fxmlLoader.load();

            ForgotPass3FormController f3 =  fxmlLoader.getController();
            f3.setEmail(email);
            root.getChildren().clear();
            root.getChildren().add(main);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
