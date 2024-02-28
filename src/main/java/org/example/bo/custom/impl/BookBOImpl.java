package org.example.bo.custom.impl;

import org.example.bo.custom.BookBO;
import org.example.dto.BookDTO;
import org.example.entity.Book;

import java.sql.SQLException;

public class BookBOImpl implements BookBO {
    @Override
    public boolean save(BookDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(BookDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public BookDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
