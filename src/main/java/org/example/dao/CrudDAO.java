package org.example.dao;

import java.sql.SQLException;

public interface CrudDAO <T> extends SuperDAO{
      boolean save(T ent) throws SQLException, ClassNotFoundException;
      boolean update(T ent) throws SQLException, ClassNotFoundException;
      boolean delete(String id) throws SQLException, ClassNotFoundException;
      T search(String id) throws SQLException, ClassNotFoundException;
}
