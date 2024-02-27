package org.example.dao.custom.impl;

import org.example.dao.custom.AdminDAO;
import org.example.entity.Admin;

import java.sql.SQLException;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public boolean save(Admin ent) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Admin ent) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Admin search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
