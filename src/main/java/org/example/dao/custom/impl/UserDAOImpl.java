package org.example.dao.custom.impl;

import org.example.dao.custom.UserDAO;
import org.example.entity.User;

import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User ent) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(User ent) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
