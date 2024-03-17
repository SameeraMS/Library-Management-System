package org.example.controller.admin;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.example.bo.BOFactory;
import org.example.bo.custom.BookBO;
import org.example.bo.custom.BranchBO;
import org.example.dto.BookDTO;
import org.example.dto.BranchDTO;
import org.example.dto.tm.BookTm;

import java.sql.SQLException;
import java.util.List;

public class BookMangeFormController {
    public TextField txtSearch;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAuthor;
    public TextField txtGenre;
    public ComboBox<String> cmbBranch;
    public TableView<BookTm> tblBooks;
    public ComboBox<String> cmbSelectBranch;
    public ComboBox<String> cmbStatus;

    BookBO bookBOImpl = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);
    BranchBO branchBOImpl = (BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    public void initialize() {
        setNextId();
        loadComboBox();
        loadtable();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        tblBooks.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblBooks.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("title"));
        tblBooks.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("author"));
        tblBooks.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadtable() {
        tblBooks.getItems().clear();
        try {
            List<BookDTO> all = bookBOImpl.getAll();
            for (BookDTO bookDTO : all) {
                tblBooks.getItems().add(new BookTm(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getStatus()));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadComboBox() {
        cmbBranch.getItems().clear();
        cmbSelectBranch.getItems().clear();
        try {
            List<BranchDTO> all = branchBOImpl.getAll();
            for (BranchDTO branchDTO : all) {
                cmbBranch.getItems().add(branchDTO.getLocation());
                cmbSelectBranch.getItems().add(branchDTO.getLocation());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        cmbStatus.getItems().addAll("Available", "Not Available");
    }

    private void setNextId() {
        try {
            String nextId = bookBOImpl.generateNextId();
            txtId.setText(nextId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        if (cmbBranch.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Select Branch First").show();
        } else {
            searchBook();
        }
    }

    private void searchBook() {
        String title = txtSearch.getText();
        String branch = cmbSelectBranch.getValue();

        try {
            BranchDTO dto = branchBOImpl.searchByLocation(branch);
            List<BookDTO> all = bookBOImpl.searchByTitle(title, dto.getId());
            tblBooks.getItems().clear();
            for (BookDTO bookDTO : all) {
                tblBooks.getItems().add(new BookTm(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getStatus()));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String title = txtName.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        String status = cmbStatus.getValue();
        String branch = cmbBranch.getValue();


        if (id.isEmpty() || title.isEmpty() || author.isEmpty() || genre.isEmpty() || status.isEmpty() || branch.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else {
            try {
                BranchDTO branchDTO = branchBOImpl.searchByLocation(branch);
                bookBOImpl.save(new BookDTO(id, title, author, genre, status, branchDTO));
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                initialize();
                clear();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String title = txtName.getText();
        String author = txtAuthor.getText();
        String genre = txtGenre.getText();
        String status = cmbStatus.getValue();
        String branch = cmbBranch.getValue();


        if (id.isEmpty() || title.isEmpty() || author.isEmpty() || genre.isEmpty() || status.isEmpty() || branch.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
        } else {
            try {
                BranchDTO branchDTO = branchBOImpl.searchByLocation(branch);
                bookBOImpl.update(new BookDTO(id, title, author, genre, status, branchDTO));
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                initialize();
                clear();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            bookBOImpl.delete(id);
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            initialize();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtName.clear();
        txtAuthor.clear();
        txtGenre.clear();
        cmbStatus.setValue(null);
        cmbBranch.setValue(null);
        initialize();
    }

    public void searchOnAction(ActionEvent actionEvent) {
        searchBook();
    }

    public void clear(){
        txtId.clear();
        txtName.clear();
        txtAuthor.clear();
        txtGenre.clear();
        cmbStatus.setValue(null);
        initialize();
    }

    public void tblOnAction(MouseEvent mouseEvent) {
        BookTm tm = tblBooks.getSelectionModel().getSelectedItem();
        try {
            BookDTO search = bookBOImpl.search(tm.getId());
            txtId.setText(search.getId());
            txtName.setText(search.getTitle());
            txtAuthor.setText(search.getAuthor());
            txtGenre.setText(search.getGenre());
            cmbStatus.setValue(search.getStatus());
            cmbBranch.setValue(search.getBranch().getLocation());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchOnTimeOnAction(KeyEvent event) {
        String title = txtSearch.getText();
        String branch = cmbSelectBranch.getValue();

        if (branch == null) {
            new Alert(Alert.AlertType.ERROR, "Select Branch First").show();
        } else {
            try {
                BranchDTO dto = branchBOImpl.searchByLocation(branch);
                List<BookDTO> all = bookBOImpl.searchOnTime(title, dto.getId());
                tblBooks.getItems().clear();
                for (BookDTO bookDTO : all) {
                    tblBooks.getItems().add(new BookTm(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getStatus()));
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
