package org.example.dao.custom.impl;

import org.example.dao.custom.BorrowingDAO;
import org.example.entity.BorrowBooks;

import java.sql.SQLException;
import java.util.List;

public class BorrowingDAOImpl implements BorrowingDAO {

    @Override
    public boolean save(BorrowBooks ent) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(BorrowBooks ent) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public BorrowBooks search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<BorrowBooks> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
