package org.example.bo.custom.impl;

import org.example.bo.custom.AdminBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.AdminDAO;
import org.example.dto.AdminDTO;
import org.example.entity.Admin;

import java.sql.SQLException;

public class AdminBOImpl implements AdminBO {

    AdminDAO adminDaoImpl = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADMIN);
    @Override
    public boolean save(AdminDTO dto) throws SQLException, ClassNotFoundException {
        return adminDaoImpl.save(new Admin(dto.getId(),dto.getName(),dto.getEmail(),dto.getPassword()));
    }

    @Override
    public boolean update(AdminDTO dto) throws SQLException, ClassNotFoundException {
        return adminDaoImpl.update(new Admin(dto.getId(),dto.getName(),dto.getEmail(),dto.getPassword()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return adminDaoImpl.delete(id);
    }

    @Override
    public AdminDTO search(String id) throws SQLException, ClassNotFoundException {
        Admin search = adminDaoImpl.search(id);
        return new AdminDTO(search.getId(),search.getName(),search.getEmail(),search.getPassword());
    }
}
