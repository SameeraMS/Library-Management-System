package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dao.CrudDAO;
import org.example.dto.AdminDTO;
import org.example.dto.BranchDTO;
import org.example.entity.Branch;
import org.example.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface BranchBO extends SuperBO {
    boolean save(BranchDTO dto) throws SQLException, ClassNotFoundException;

    boolean update(BranchDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;
    BranchDTO search(String id) throws SQLException, ClassNotFoundException;
    List<BranchDTO> getAll() throws SQLException, ClassNotFoundException;
    String generateNextId() throws SQLException, ClassNotFoundException;
}
