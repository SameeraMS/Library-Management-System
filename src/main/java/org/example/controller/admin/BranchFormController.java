package org.example.controller.admin;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.bo.BOFactory;
import org.example.bo.custom.BranchBO;
import org.example.dto.BranchDTO;
import org.example.dto.tm.BranchTm;

import java.sql.SQLException;
import java.util.List;

public class BranchFormController {
    public TextField txtId;
    public TextField txtLocation;
    public TextField txtTelephone;
    public TextField txtEmail;
    public TextField txtAddress;
    public ComboBox<String> cmbBranch;
    public TableView<BranchTm> tblBranch;

    BranchBO branchBoImpl = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    public void initialize() {
        setNextId();
        loadComboBox();
        loadtable();
        setCellValueFactory();
    }

    private void loadtable() {
        tblBranch.getItems().clear();
        try {
            List<BranchDTO> all = branchBoImpl.getAll();
            for (BranchDTO branchDTO : all) {
                tblBranch.getItems().add(new BranchTm(branchDTO.getId(), branchDTO.getLocation(), branchDTO.getTelephone(), branchDTO.getEmail()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        tblBranch.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblBranch.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("location"));
        tblBranch.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("telephone"));
        tblBranch.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadComboBox() {
        cmbBranch.getItems().clear();
        try {
            List<BranchDTO> all = branchBoImpl.getAll();
            for (BranchDTO branchDTO : all) {
                cmbBranch.getItems().add(branchDTO.getLocation());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setNextId() {
        try {
            String nextId = branchBoImpl.generateNextId();
            txtId.setText(nextId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtIdOnAction(ActionEvent actionEvent) {
        if (txtId.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter branch id").show();
        } else {
            try {
                BranchDTO branchDTO = branchBoImpl.search(txtId.getText());
                txtLocation.setText(branchDTO.getLocation());
                txtTelephone.setText(String.valueOf(branchDTO.getTelephone()));
                txtEmail.setText(branchDTO.getEmail());
                txtAddress.setText(branchDTO.getAddress());
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void txtLocationOnAction(ActionEvent actionEvent) {
        try {
            BranchDTO branchDTO = branchBoImpl.searchByLocation(txtLocation.getText());
            if (branchDTO != null) {
                txtId.setText(branchDTO.getId());
                txtTelephone.setText(String.valueOf(branchDTO.getTelephone()));
                txtEmail.setText(branchDTO.getEmail());
                txtAddress.setText(branchDTO.getAddress());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbBranchOnAction(ActionEvent actionEvent) {
        try {
            BranchDTO branchDTO = branchBoImpl.searchByLocation(cmbBranch.getValue());
            if (branchDTO != null) {
                txtId.setText(branchDTO.getId());
                txtLocation.setText(branchDTO.getLocation());
                txtTelephone.setText(String.valueOf(branchDTO.getTelephone()));
                txtEmail.setText(branchDTO.getEmail());
                txtAddress.setText(branchDTO.getAddress());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String location = txtLocation.getText();
        String telephone = txtTelephone.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();

        if (id.isEmpty() || location.isEmpty() || telephone.isEmpty() || email.isEmpty() || address.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else {
            try {
                branchBoImpl.save(new BranchDTO(id, location, Integer.parseInt(telephone), email, address));
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                initialize();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String location = txtLocation.getText();
        String telephone = txtTelephone.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();

        if (id.isEmpty() || location.isEmpty() || telephone.isEmpty() || email.isEmpty() || address.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else {
            try {
                branchBoImpl.update(new BranchDTO(id, location, Integer.parseInt(telephone), email, address));
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                initialize();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        if (id.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter branch id").show();
        } else {
            try {
                branchBoImpl.delete(id);
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                initialize();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        setNextId();
        txtLocation.clear();
        txtTelephone.clear();
        txtEmail.clear();
        txtAddress.clear();
    }
}
