package org.example.controller.admin;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.bo.BOFactory;
import org.example.bo.custom.BranchBO;
import org.example.bo.custom.UserBO;
import org.example.dto.BranchDTO;
import org.example.dto.UserDTO;
import org.example.dto.tm.UserTm;
import org.example.regex.Regex;

import java.sql.SQLException;
import java.util.List;

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

    public void initialize() {
        setCellValueFactory();
        loadTables();
        loadComboBox();
    }

    private void setCellValueFactory() {
        tblUser.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblUser.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblUser.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("telephone"));
        tblUser.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("branch"));
    }

    private void loadTables() {
        tblUser.getItems().clear();
        try {
            List<UserDTO> all = userBo.getAll();
            for (UserDTO userDTO : all) {
                tblUser.getItems().add(new UserTm(userDTO.getEmail(), userDTO.getName(), userDTO.getTelephone(), userDTO.getBranch().getLocation()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadComboBox() {
        cmbBranch.getItems().clear();
        cmbSearchBy.getItems().clear();
        try {
            List<BranchDTO> all = branchBo.getAll();
            for (BranchDTO branchDTO : all) {
                cmbBranch.getItems().add(branchDTO.getLocation());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        cmbSearchBy.getItems().addAll("Name", "Email", "Telephone");
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String searchby = cmbSearchBy.getValue();
        try{
            switch (searchby) {
                case "Name":
                    List<UserDTO> userDTOS = userBo.searchUserByName(txtSearch.getText());
                    tblUser.getItems().clear();
                    for (UserDTO userDTO : userDTOS) {
                        tblUser.getItems().add(new UserTm(userDTO.getEmail(), userDTO.getName(), userDTO.getTelephone(), userDTO.getBranch().getLocation()));
                    }
                    break;
                case "Email":
                    UserDTO userDTO = userBo.searchUserByEmail(txtSearch.getText());
                    setToTable(userDTO);
                    break;
                case "Telephone":
                    UserDTO dto = userBo.searchUserByTelephone(txtSearch.getText());
                    setToTable(dto);
                    break;
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setToTable(UserDTO userDTO) {
        tblUser.getItems().clear();
        tblUser.getItems().add(new UserTm(userDTO.getEmail(), userDTO.getName(), userDTO.getTelephone(), userDTO.getBranch().getLocation()));
    }


    public void tblOnAction(MouseEvent mouseEvent) {
        UserTm userTm = tblUser.getSelectionModel().getSelectedItem();
        txtName.setText(userTm.getName());
        txtMail.setText(userTm.getEmail());
        txtTelephone.setText(String.valueOf(userTm.getTelephone()));
        cmbBranch.setValue(userTm.getBranch());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        if(Regex.getNamePattern().matcher(txtName.getText()).matches()){
            if(Regex.getMobilePattern().matcher(txtTelephone.getText()).matches()){
                if (txtName.getText().isEmpty() || txtMail.getText().isEmpty() || txtTelephone.getText().isEmpty() || cmbBranch.getValue().isEmpty()) {
                    new Alert(Alert.AlertType.ERROR, "All fields are required").show();
                } else {
                    try {
                        BranchDTO branchDTO = branchBo.searchByLocation(cmbBranch.getValue());
                        UserDTO search = userBo.search(txtMail.getText());
                        userBo.update(new UserDTO(txtName.getText(),txtMail.getText(),search.getPassword(), Integer.parseInt(txtTelephone.getText()), branchDTO));
                        new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                        clearFields();
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "Invalid Mobile Number").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Name").show();
        }
    }

    private void clearFields() {
        txtMail.clear();
        txtName.clear();
        txtTelephone.clear();
        cmbBranch.setValue(null);
        initialize();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if (txtName.getText().isEmpty() || txtMail.getText().isEmpty() || txtTelephone.getText().isEmpty() || cmbBranch.getValue().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else {
            try {
                userBo.delete(txtMail.getText());
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                clearFields();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        txtSearchOnAction(actionEvent);
    }
}
