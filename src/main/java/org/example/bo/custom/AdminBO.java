package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dao.CrudDAO;
import org.example.dto.AdminDTO;
import org.example.entity.Admin;

import java.sql.SQLException;

public interface AdminBO extends SuperBO {
     boolean save(AdminDTO dto) throws SQLException, ClassNotFoundException;

     boolean update(AdminDTO dto) throws SQLException, ClassNotFoundException;

     boolean delete(String id) throws SQLException, ClassNotFoundException;
    AdminDTO search(String id) throws SQLException, ClassNotFoundException;
}
