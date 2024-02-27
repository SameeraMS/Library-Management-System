package org.example.dao.custom.impl;

import org.example.dao.custom.BranchDAO;
import org.example.entity.Branch;

import java.sql.SQLException;

public class BranchDAOImpl implements BranchDAO {
    @Override
    public boolean save(Branch ent) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Branch ent) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Branch search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
