package org.example.bo.custom.impl;

import org.example.bo.custom.AdminBO;
import org.example.dto.AdminDTO;
import org.example.entity.Admin;

import java.sql.SQLException;

public class AdminBOImpl implements AdminBO {
    @Override
    public boolean save(AdminDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(AdminDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public AdminDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
