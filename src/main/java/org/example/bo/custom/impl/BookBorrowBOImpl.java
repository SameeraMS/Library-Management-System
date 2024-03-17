package org.example.bo.custom.impl;

import org.example.bo.custom.BookBorrowBO;
import org.example.config.FactoryConfiguration;
import org.example.dto.BookDTO;
import org.example.dto.BorrowDTO;
import org.example.entity.Book;
import org.example.entity.BorrowBooks;
import org.example.entity.Branch;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookBorrowBOImpl implements BookBorrowBO {
    public void borrowBook(BorrowDTO borrowDTO, BookDTO book){

        User user = new User(borrowDTO.getUser().getName(), borrowDTO.getUser().getEmail(), borrowDTO.getUser().getPassword(), borrowDTO.getUser().getTelephone(), null, null);
        Branch branch = new Branch(book.getBranch().getId(), book.getBranch().getLocation(), book.getBranch().getTelephone(), book.getBranch().getEmail(), book.getBranch().getAddress(), null, null);
        Book bookEnt = new Book(borrowDTO.getBook().getId(), borrowDTO.getBook().getTitle(), borrowDTO.getBook().getAuthor(), borrowDTO.getBook().getGenre(), borrowDTO.getBook().getStatus(), branch, null);
        BorrowBooks borrowBooks = new BorrowBooks(borrowDTO.getId(), user, bookEnt, borrowDTO.getBorrowDate(), borrowDTO.getReturnDate(), borrowDTO.getStatus());

        bookEnt.setStatus("Not Available");

        Session session = FactoryConfiguration.getInstance().getSession();
        try{
            Transaction transaction = session.beginTransaction();

            session.persist(borrowBooks);
            session.update(bookEnt);

            transaction.commit();
            session.close();
        }catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
