package org.example.controller.admin;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.bo.BOFactory;
import org.example.bo.custom.BranchBO;
import org.example.bo.custom.UserBO;
import org.example.dto.tm.UserTm;

public class UserFormController {
    public TextField txtSearch;
    public TableView<UserTm> tblUser;
    public TextField txtMail;
    public TextField txtName;
    public TextField txtTelephone;
    public ComboBox<String> cmbBranch;
    public ComboBox<String> cmbSearchBy;

    UserBO userBo = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    BranchBO branchBo = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    public void txtSearchOnAction(ActionEvent actionEvent) {
    }

    public void tblOnAction(MouseEvent mouseEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }
}
