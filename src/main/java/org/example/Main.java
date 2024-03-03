package org.example;

import org.example.config.FactoryConfiguration;
import org.example.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Admin ent = new Admin("a","a@a.com",123,"a");

        session.persist(ent);
        transaction.commit();
        session.close();
    }
}
