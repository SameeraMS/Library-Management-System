package org.example.controller.admin;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.bo.BOFactory;
import org.example.bo.custom.BorrowingBO;
import org.example.dto.BorrowDTO;
import org.example.dto.tm.BorrowTm;

import java.sql.SQLException;
import java.util.List;

public class BorrowFormController {
    public TableView<BorrowTm> tblBorrow;
    public TextField txtEmail;
    public TextField txtBorrowId;
    public TextField txtUserMail;
    public TextField txtBookId;
    public ComboBox<String> cmbStatus;

    BorrowingBO borrowingBO = (BorrowingBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BORROW);


    public void initialize() {
        loadBorrowTable();
        setCellValueFactory();
    }

    private void loadBorrowTable() {
        try {
            tblBorrow.getItems().clear();
            List<BorrowDTO> all = borrowingBO.getAll();

            for (BorrowDTO borrowDTO : all) {
                tblBorrow.getItems().add(new BorrowTm(borrowDTO.getId(), borrowDTO.getUser().getName(), borrowDTO.getBook().getId()+"-"+borrowDTO.getBook().getTitle(), borrowDTO.getBorrowDate(), borrowDTO.getReturnDate(), borrowDTO.getStatus()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        tblBorrow.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblBorrow.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("user"));
        tblBorrow.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("book"));
        tblBorrow.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        tblBorrow.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        tblBorrow.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void allOnAction(ActionEvent actionEvent) {
    }

    public void notReturnedOnAction(ActionEvent actionEvent) {
    }

    public void pendingOnAction(ActionEvent actionEvent) {
    }

    public void searchOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }
}
