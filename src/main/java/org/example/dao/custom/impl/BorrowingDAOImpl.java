package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.BorrowingDAO;
import org.example.entity.Book;
import org.example.entity.BorrowBooks;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class BorrowingDAOImpl implements BorrowingDAO {

    @Override
    public boolean save(BorrowBooks ent) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ent);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(BorrowBooks ent) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(ent);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(id);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public BorrowBooks search(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        BorrowBooks boorrow = session.get(BorrowBooks.class, id);
        transaction.commit();
        session.close();
        return boorrow;
    }

    @Override
    public List<BorrowBooks> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<BorrowBooks> list = session.createQuery("FROM Borrowing.Books").list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Object object = session.createQuery("SELECT id FROM Borrowing.Books ORDER BY id DESC LIMIT 1").uniqueResult();
        transaction.commit();
        session.close();

        if(object != null) {
            String CurrentId = object.toString();
            String[] split = CurrentId.split("BB0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            if(id<10) {
                return "BB00" + id;
            } else {
                return "BB0" + id;
            }
        } else {
            return "BB001";
        }
    }
}
