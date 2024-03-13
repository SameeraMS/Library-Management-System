package org.example.controller.admin;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;
import org.example.bo.BOFactory;
import org.example.bo.custom.BookBO;
import org.example.bo.custom.BorrowingBO;
import org.example.bo.custom.BranchBO;
import org.example.bo.custom.UserBO;
import org.example.dto.BookDTO;
import org.example.dto.tm.UserBorrowTm;

import java.sql.SQLException;
import java.util.List;

public class DashboardFormController {
    public Label lblMemberCount;
    public Label lblBookCount;
    public Label lblBranchCount;
    public Label LblReadBookCount;
    public TableView<UserBorrowTm> tblBook;
    @Setter
    private String email;

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);
    BorrowingBO borrowingBO = (BorrowingBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BORROW);


    public void initialize() throws SQLException, ClassNotFoundException {
        loadTables();
        setCellValueFactory();
        setLabels();
    }

    private void setLabels() throws SQLException, ClassNotFoundException {
        lblMemberCount.setText(String.valueOf(userBO.getAll().size()));
        lblBookCount.setText(String.valueOf(bookBO.getAll().size()));
        lblBranchCount.setText(String.valueOf(branchBO.getAll().size()));

        if (email != null) {
            LblReadBookCount.setText(String.valueOf(borrowingBO.getUserList(email).size()));
        }

    }

    private void setCellValueFactory() {
        tblBook.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblBook.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("title"));
        tblBook.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("author"));
        tblBook.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("genre"));
    }

    private void loadTables() throws SQLException, ClassNotFoundException {
        List<BookDTO> bookDTOS = bookBO.getAll();
        tblBook.getItems().clear();

        if (bookDTOS.size() > 5) {
            for (int i = 0; i < 5; i++) {
                tblBook.getItems().add(new UserBorrowTm(bookDTOS.get(i).getId(), bookDTOS.get(i).getTitle(), bookDTOS.get(i).getAuthor(), bookDTOS.get(i).getGenre()));
            }
        } else {
            for (BookDTO bookDTO : bookDTOS) {
                tblBook.getItems().add(new UserBorrowTm(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getGenre()));
            }
        }
    }
}
