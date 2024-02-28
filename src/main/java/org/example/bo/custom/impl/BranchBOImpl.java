package org.example.bo.custom.impl;

import org.example.bo.custom.BranchBO;
import org.example.dto.BranchDTO;
import org.example.entity.Branch;

import java.sql.SQLException;

public class BranchBOImpl implements BranchBO {

    @Override
    public boolean save(BranchDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(BranchDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public BranchDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
