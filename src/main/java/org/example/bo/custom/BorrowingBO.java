package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.BookDTO;
import org.example.dto.BorrowDTO;

import java.sql.SQLException;
import java.util.List;

public interface BorrowingBO extends SuperBO {
    boolean save(BorrowDTO dto) throws SQLException, ClassNotFoundException;
    boolean update(BorrowDTO dto) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    BorrowDTO search(String id) throws SQLException, ClassNotFoundException;
    List<BorrowDTO> getAll() throws SQLException, ClassNotFoundException;
    String generateNextId() throws SQLException, ClassNotFoundException;
}
