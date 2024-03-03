package org.example.controller.forgotPass;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Setter;
import org.example.bo.BOFactory;
import org.example.bo.custom.UserBO;
import org.example.dto.UserDTO;


import java.sql.SQLException;

public class ForgotPass3FormController {
    public TextField txtPass1;
    public TextField txtPass2;

    @Setter
    public String email;
    public AnchorPane root;

    UserBO userBo = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);


    public void changeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String pass1 = txtPass1.getText();
        String pass2 = txtPass2.getText();

        if(pass1.equals(pass2)){
            UserDTO search = userBo.search(email);
            UserDTO userDTO = new UserDTO(search.getName(), search.getEmail(), pass1,search.getTelephone()  , search.getBranch());

            try {
                userBo.update(userDTO);
                new Alert(Alert.AlertType.CONFIRMATION, "Password Changed Successfully").show();

                Stage stage = (Stage) root.getScene().getWindow();
                stage.close();

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Password does not match").show();
        }
    }
}
