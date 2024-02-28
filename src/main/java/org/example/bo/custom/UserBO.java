package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dao.CrudDAO;
import org.example.dto.AdminDTO;
import org.example.dto.UserDTO;
import org.example.entity.User;

import java.sql.SQLException;

public interface UserBO extends SuperBO {
    boolean save(UserDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(UserDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;
    UserDTO search(String id) throws SQLException, ClassNotFoundException;
}
