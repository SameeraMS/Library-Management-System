package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.AdminDAO;
import org.example.dto.AdminDTO;
import org.example.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public boolean save(Admin ent) throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(ent);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(Admin ent) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Admin search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
