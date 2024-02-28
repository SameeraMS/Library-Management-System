package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dao.CrudDAO;
import org.example.dto.AdminDTO;
import org.example.dto.BookDTO;
import org.example.entity.Book;

import java.sql.SQLException;

public interface BookBO extends SuperBO {
    boolean save(BookDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(BookDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;
    BookDTO search(String id) throws SQLException, ClassNotFoundException;
}
