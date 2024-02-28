package org.example.bo.custom.impl;

import org.example.bo.custom.UserBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.UserDAO;
import org.example.dto.UserDTO;
import org.example.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDaoImpl = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean save(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDaoImpl.save(new User(dto.getName(), dto.getEmail(), dto.getPassword()));
    }

    @Override
    public boolean update(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDaoImpl.update(new User(dto.getName(), dto.getEmail(), dto.getPassword()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return userDaoImpl.delete(id);
    }

    @Override
    public UserDTO search(String id) throws SQLException, ClassNotFoundException {
        User search = userDaoImpl.search(id);
        return new UserDTO(search.getName(), search.getEmail(), search.getPassword());
    }

    @Override
    public List<UserDTO> getAll() throws SQLException, ClassNotFoundException {
        List<User> all = userDaoImpl.getAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : all) {
            userDTOS.add(new UserDTO(user.getName(), user.getEmail(), user.getPassword()));
        }
        return userDTOS;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return userDaoImpl.generateNextId();
    }
}
