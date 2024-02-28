package org.example.bo.custom.impl;

import org.example.bo.custom.UserBO;
import org.example.dto.UserDTO;
import org.example.entity.User;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    @Override
    public boolean save(UserDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(UserDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public UserDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
