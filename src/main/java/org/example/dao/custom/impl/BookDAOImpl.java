package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.CrudDAO;
import org.example.dao.custom.AdminDAO;
import org.example.dao.custom.BookDAO;
import org.example.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public boolean save(Book ent) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ent);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Book ent) throws SQLException, ClassNotFoundException {
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
    public Book search(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Book book = session.get(Book.class, id);
        transaction.commit();
        session.close();
        return book;
    }

    @Override
    public List<Book> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Book> books = session.createQuery("FROM Book").list();
        transaction.commit();
        session.close();
        return books;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Object object = session.createQuery("SELECT COUNT(id) FROM Book").uniqueResult();
        transaction.commit();
        session.close();

        int id = Integer.parseInt(object.toString());
        return "B" + (id + 1);
    }
}
