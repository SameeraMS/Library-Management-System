package org.example.bo.custom.impl;

import org.example.bo.custom.AdminBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.AdminDAO;
import org.example.dto.AdminDTO;
import org.example.entity.Admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminBOImpl implements AdminBO {

    AdminDAO adminDaoImpl = (AdminDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADMIN);
    @Override
    public boolean save(AdminDTO dto) throws SQLException, ClassNotFoundException {
        return adminDaoImpl.save(new Admin(dto.getName(),dto.getEmail(),dto.getPassword()));
    }

    @Override
    public boolean update(AdminDTO dto) throws SQLException, ClassNotFoundException {
        return adminDaoImpl.update(new Admin(dto.getName(),dto.getEmail(),dto.getPassword()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return adminDaoImpl.delete(id);
    }

    @Override
    public AdminDTO search(String id) throws SQLException, ClassNotFoundException {
        Admin search = adminDaoImpl.search(id);

        if (search == null) {
            return null;
        } else {
            return new AdminDTO(search.getName(),search.getEmail(),search.getPassword());
        }
    }

    @Override
    public List<AdminDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Admin> all = adminDaoImpl.getAll();
        List<AdminDTO> adminDTOS = new ArrayList<>();

        if (all == null) {
            return null;
        }else {
            for (Admin admin : all) {
                adminDTOS.add(new AdminDTO(admin.getName(),admin.getEmail(),admin.getPassword()));
            }
            return adminDTOS;
        }

    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return adminDaoImpl.generateNextId();
    }
}
