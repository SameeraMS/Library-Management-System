package org.example.controller.user;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.example.bo.BOFactory;
import org.example.bo.custom.*;
import org.example.bo.custom.impl.BookBorrowBOImpl;
import org.example.dto.BookDTO;
import org.example.dto.BorrowDTO;
import org.example.dto.BranchDTO;
import org.example.dto.UserDTO;
import org.example.dto.tm.UserBorrowTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BookFormController {
    public TextField txtSearch;
    public ComboBox<String> cmbBranch;
    public TextField txtBorrowId;
    public TextField txtUserMail;
    public TextField txtBookId;
    public TextField txtToday;
    public TextField txtReturnDate;
    public TableView<UserBorrowTm> tblBook;
    public Label lblStatus;
    public Label lblBookId;
    public Label lblTitle;
    public Label lblAuthor;
    public Label lblGenre;
    public Label lblBorrowBookId;
    public Label lblBorrowBookTitle;
    public Label lblBorrowBookReturnDate;
    public TextField txtBranchLocation;
    public TextField txtBookName;
    public Button btnAdd;
    public Button btnBorrow;
    BorrowingBO borrowingBO = (BorrowingBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BORROW);
    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);
    BookBorrowBO bookBorrowBO = (BookBorrowBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOKBORROW);

    public void initialize() throws SQLException, ClassNotFoundException {
        tblBook.getItems().clear();
        cmbBranch.getItems().clear();
        List<BranchDTO> all = branchBO.getAll();

        for (BranchDTO branchDTO : all) {
            cmbBranch.getItems().add(branchDTO.getLocation());
        }

        String nextId = borrowingBO.generateNextId();
        txtBorrowId.setText(nextId);
        LocalDate today = LocalDate.now();
        txtToday.setText(today.toString());
        txtReturnDate.setText(today.plusDays(15).toString());

        clearFields();

    }

    private void clearFields() {
        lblBookId.setText("");
        lblTitle.setText("");
        lblAuthor.setText("");
        lblGenre.setText("");
        txtBookId.setText("");
        txtBookName.setText("");
    }

    public void start() throws SQLException, ClassNotFoundException {
        List<BorrowDTO> userList = borrowingBO.getUserList(txtUserMail.getText());
        for (BorrowDTO borrowDTO : userList) {
            if (borrowDTO.getStatus().equals("Pending")) {
                lblStatus.setText("Please Return The Book");
                lblBorrowBookId.setText(borrowDTO.getBook().getId());
                lblBorrowBookTitle.setText(borrowDTO.getBook().getTitle());
                lblBorrowBookReturnDate.setText(borrowDTO.getReturnDate().toString());
                btnAdd.setDisable(true);
                btnBorrow.setDisable(true);
                break;
            }
        }
    }


    public void txtSearchOnAction(KeyEvent event) {

    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (cmbBranch.getValue().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Select Branch First").show();
        } else{
            String location = cmbBranch.getValue();
            BranchDTO branchDTO = branchBO.searchByLocation(location);
            List<BookDTO> bookDTOS = bookBO.searchByTitle(txtSearch.getText(), branchDTO.getId());

            tblBook.getItems().clear();
            for (BookDTO bookDTO : bookDTOS) {
                tblBook.getItems().add(new UserBorrowTm(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getGenre()));
            }
            setCellValueFactory();
        }

    }

    public void tblOnAction(MouseEvent mouseEvent) {
        UserBorrowTm selectedItem = tblBook.getSelectionModel().getSelectedItem();
        lblBookId.setText(selectedItem.getId());
        lblTitle.setText(selectedItem.getTitle());
        lblAuthor.setText(selectedItem.getAuthor());
        lblGenre.setText(selectedItem.getGenre());
    }

    public void borrowOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (txtBorrowId.getText().isEmpty() || txtUserMail.getText().isEmpty() || txtBookId.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else {
            String borrowId = txtBorrowId.getText();
            String userMail = txtUserMail.getText();
            String bookId = txtBookId.getText();

            LocalDate today = LocalDate.now();
            LocalDate returnDate = LocalDate.parse(txtReturnDate.getText());

            UserDTO user = userBO.search(userMail);
            BookDTO book = bookBO.search(bookId);

            BorrowDTO borrowDTO = new BorrowDTO(borrowId, user, book, today, returnDate, "Pending");

            //1st step
            /*borrowingBO.save(borrowDTO);

            book.setStatus("Not Available");
            bookBO.update(book);*/

            //2nd step
            bookBorrowBO.borrowBook(borrowDTO, book);


            new Alert(Alert.AlertType.INFORMATION, "Book Borrowed Successfully").show();
            initialize();
            start();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if (lblBookId.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Select Book First").show();
        }else {
            txtBookId.setText(lblBookId.getText());
            txtBookName.setText(lblTitle.getText());
        }
    }

    public void cmbBranchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String location = cmbBranch.getValue();
        BranchDTO branchDTO = branchBO.searchByLocation(location);
        txtBranchLocation.setText(location);

        if (branchDTO != null) {
            List<BookDTO> bookDTOS = bookBO.searchByBranch(branchDTO.getId());

            tblBook.getItems().clear();
            for (BookDTO bookDTO : bookDTOS) {
                tblBook.getItems().add(new UserBorrowTm(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getGenre()));
            }
            setCellValueFactory();
        }
    }

    private void setCellValueFactory() {
        tblBook.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblBook.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("title"));
        tblBook.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("author"));
        tblBook.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("genre"));
    }
}
