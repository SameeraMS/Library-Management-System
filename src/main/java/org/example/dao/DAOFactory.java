package org.example.dao;


import org.example.dao.custom.impl.AdminDAOImpl;
import org.example.dao.custom.impl.BookDAOImpl;
import org.example.dao.custom.impl.BranchDAOImpl;
import org.example.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        ADMIN, USER, BOOK, BRANCH
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case ADMIN:
                return new AdminDAOImpl();
            case USER:
                return new UserDAOImpl();
            case BOOK:
                return new BookDAOImpl();
            case BRANCH:
                return new BranchDAOImpl();
            default:
                return null;
        }
    }
}
