package org.example.controller.admin;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.bo.BOFactory;
import org.example.bo.custom.BookBO;
import org.example.bo.custom.BorrowingBO;
import org.example.dto.BookDTO;
import org.example.dto.BorrowDTO;
import org.example.dto.tm.BorrowTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BorrowFormController {
    public TableView<BorrowTm> tblBorrow;
    public TextField txtEmail;
    public TextField txtBorrowId;
    public TextField txtUserMail;
    public TextField txtBookId;
    public ComboBox<String> cmbStatus;

    BorrowingBO borrowingBO = (BorrowingBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BORROW);
    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);


    public void initialize() {
        loadBorrowTable();
        setCellValueFactory();
        setCmbValues();
    }

    private void setCmbValues() {
        cmbStatus.getItems().clear();
        cmbStatus.getItems().add("Pending");
        cmbStatus.getItems().add("Returned");
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
        initialize();
    }

    public void notReturnedOnAction(ActionEvent actionEvent) {
        LocalDate today = LocalDate.now();
        try {
            tblBorrow.getItems().clear();
            List<BorrowDTO> all = borrowingBO.getNotReturnList(today);

            for (BorrowDTO borrowDTO : all) {
                tblBorrow.getItems().add(new BorrowTm(borrowDTO.getId(), borrowDTO.getUser().getName(), borrowDTO.getBook().getId()+"-"+borrowDTO.getBook().getTitle(), borrowDTO.getBorrowDate(), borrowDTO.getReturnDate(), borrowDTO.getStatus()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void pendingOnAction(ActionEvent actionEvent) {
        try {
            tblBorrow.getItems().clear();
            List<BorrowDTO> all = borrowingBO.getPendingList();

            for (BorrowDTO borrowDTO : all) {
                tblBorrow.getItems().add(new BorrowTm(borrowDTO.getId(), borrowDTO.getUser().getName(), borrowDTO.getBook().getId()+"-"+borrowDTO.getBook().getTitle(), borrowDTO.getBorrowDate(), borrowDTO.getReturnDate(), borrowDTO.getStatus()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        String mail = txtEmail.getText();
        try {
            tblBorrow.getItems().clear();
            List<BorrowDTO> all = borrowingBO.getUserList(mail);

            for (BorrowDTO borrowDTO : all) {
                tblBorrow.getItems().add(new BorrowTm(borrowDTO.getId(), borrowDTO.getUser().getName(), borrowDTO.getBook().getId()+"-"+borrowDTO.getBook().getTitle(), borrowDTO.getBorrowDate(), borrowDTO.getReturnDate(), borrowDTO.getStatus()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        if (txtBorrowId.getText().isEmpty() || txtUserMail.getText().isEmpty() || txtBookId.getText().isEmpty() || cmbStatus.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Missing Information").show();
        } else {
            String id = txtBorrowId.getText();
            String status = cmbStatus.getValue();
            try {
                BorrowDTO search = borrowingBO.search(id);
                BorrowDTO borrowDTO = new BorrowDTO(search.getId(), search.getUser(), search.getBook(), search.getBorrowDate(), search.getReturnDate(), status);
                BookDTO book = bookBO.search(search.getBook().getId());
                book.setStatus("Available");
                borrowingBO.update(borrowDTO);
                bookBO.update(book);
                new Alert(Alert.AlertType.CONFIRMATION, "Updated successfully!").show();
                initialize();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void tblOnAction(MouseEvent mouseEvent) {
        BorrowTm tm = tblBorrow.getSelectionModel().getSelectedItem();
        try {
            BorrowDTO search = borrowingBO.search(tm.getId());
            txtBorrowId.setText(search.getId());
            txtUserMail.setText(search.getUser().getEmail());
            txtBookId.setText(search.getBook().getId());
            cmbStatus.setValue(search.getStatus());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
