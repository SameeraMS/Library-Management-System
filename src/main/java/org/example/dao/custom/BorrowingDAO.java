package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.BorrowBooks;

import java.sql.SQLException;
import java.util.List;

public interface BorrowingDAO extends CrudDAO<BorrowBooks> {
    List<BorrowBooks> getPendingList() throws SQLException, ClassNotFoundException;
    List<BorrowBooks> getUserList(String email) throws SQLException, ClassNotFoundException;
    List<BorrowBooks> getNotReturnList(String date) throws SQLException, ClassNotFoundException;
}
