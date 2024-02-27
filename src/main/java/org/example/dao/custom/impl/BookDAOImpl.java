package org.example.dao.custom.impl;

import org.example.dao.CrudDAO;
import org.example.dao.custom.AdminDAO;
import org.example.dao.custom.BookDAO;
import org.example.entity.Book;

import java.sql.SQLException;

public class BookDAOImpl implements BookDAO {
    @Override
    public boolean save(Book ent) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Book ent) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Book search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
