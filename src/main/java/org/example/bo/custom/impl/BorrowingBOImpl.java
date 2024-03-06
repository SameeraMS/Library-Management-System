package org.example.bo.custom.impl;

import org.example.bo.custom.BorrowingBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.BorrowingDAO;
import org.example.dto.BookDTO;
import org.example.dto.BorrowDTO;
import org.example.dto.UserDTO;
import org.example.entity.Book;
import org.example.entity.BorrowBooks;
import org.example.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowingBOImpl implements BorrowingBO {

    BorrowingDAO borrowingDaoImpl = (BorrowingDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BORROW);
    @Override
    public boolean save(BorrowDTO dto) throws SQLException, ClassNotFoundException {
        User user = new User(dto.getUser().getName(), dto.getUser().getEmail(), dto.getUser().getPassword(), dto.getUser().getTelephone(), null, null);
        Book book = new Book(dto.getBook().getId(), dto.getBook().getTitle(), dto.getBook().getAuthor(), dto.getBook().getGenre(), dto.getBook().getStatus(), null, null);
       return borrowingDaoImpl.save(new BorrowBooks(dto.getId(),user,book,dto.getBorrowDate(),dto.getReturnDate(),dto.getStatus()));
    }

    @Override
    public boolean update(BorrowDTO dto) throws SQLException, ClassNotFoundException {
        User user = new User(dto.getUser().getName(), dto.getUser().getEmail(), dto.getUser().getPassword(), dto.getUser().getTelephone(), null, null);
        Book book = new Book(dto.getBook().getId(), dto.getBook().getTitle(), dto.getBook().getAuthor(), dto.getBook().getGenre(), dto.getBook().getStatus(), null, null);
        return borrowingDaoImpl.update(new BorrowBooks(dto.getId(),user,book,dto.getBorrowDate(),dto.getReturnDate(),dto.getStatus()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return borrowingDaoImpl.delete(id);
    }

    @Override
    public BorrowDTO search(String id) throws SQLException, ClassNotFoundException {
        BorrowBooks search = borrowingDaoImpl.search(id);
        UserDTO user = new UserDTO(search.getUser().getName(), search.getUser().getEmail(), search.getUser().getPassword(), search.getUser().getTelephone(), null);
        BookDTO book = new BookDTO(search.getBook().getId(), search.getBook().getTitle(), search.getBook().getAuthor(), search.getBook().getGenre(), search.getBook().getStatus(), null);
        return new BorrowDTO(search.getId(),user,book,search.getBorrowDate(),search.getReturnDate(),search.getStatus());
    }

    @Override
    public List<BorrowDTO> getAll() throws SQLException, ClassNotFoundException {
        List<BorrowBooks> all = borrowingDaoImpl.getAll();
        List<BorrowDTO> list = new ArrayList<>();

        for(BorrowBooks b : all){
            UserDTO user = new UserDTO(b.getUser().getName(), b.getUser().getEmail(), b.getUser().getPassword(), b.getUser().getTelephone(), null);
            BookDTO book = new BookDTO(b.getBook().getId(), b.getBook().getTitle(), b.getBook().getAuthor(), b.getBook().getGenre(), b.getBook().getStatus(), null);
            list.add(new BorrowDTO(b.getId(),user,book,b.getBorrowDate(),b.getReturnDate(),b.getStatus()));
        }
        return list;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return borrowingDaoImpl.generateNextId();
    }

    @Override
    public List<BorrowDTO> getPendingList() throws SQLException, ClassNotFoundException {
        List<BorrowBooks> pendingList = borrowingDaoImpl.getPendingList();
        List<BorrowDTO> list = new ArrayList<>();

        for(BorrowBooks b : pendingList){
            UserDTO user = new UserDTO(b.getUser().getName(), b.getUser().getEmail(), b.getUser().getPassword(), b.getUser().getTelephone(), null);
            BookDTO book = new BookDTO(b.getBook().getId(), b.getBook().getTitle(), b.getBook().getAuthor(), b.getBook().getGenre(), b.getBook().getStatus(), null);
            list.add(new BorrowDTO(b.getId(),user,book,b.getBorrowDate(),b.getReturnDate(),b.getStatus()));
        }
        return list;
    }

    @Override
    public List<BorrowDTO> getUserList(String email) throws SQLException, ClassNotFoundException {
        List<BorrowBooks> pendingList = borrowingDaoImpl.getUserList(email);
        List<BorrowDTO> list = new ArrayList<>();

        for(BorrowBooks b : pendingList){
            UserDTO user = new UserDTO(b.getUser().getName(), b.getUser().getEmail(), b.getUser().getPassword(), b.getUser().getTelephone(), null);
            BookDTO book = new BookDTO(b.getBook().getId(), b.getBook().getTitle(), b.getBook().getAuthor(), b.getBook().getGenre(), b.getBook().getStatus(), null);
            list.add(new BorrowDTO(b.getId(),user,book,b.getBorrowDate(),b.getReturnDate(),b.getStatus()));
        }
        return list;
    }

    @Override
    public List<BorrowDTO> getNotReturnList(String date) throws SQLException, ClassNotFoundException {
        List<BorrowBooks> pendingList = borrowingDaoImpl.getNotReturnList(date);
        List<BorrowDTO> list = new ArrayList<>();

        for(BorrowBooks b : pendingList){
            UserDTO user = new UserDTO(b.getUser().getName(), b.getUser().getEmail(), b.getUser().getPassword(), b.getUser().getTelephone(), null);
            BookDTO book = new BookDTO(b.getBook().getId(), b.getBook().getTitle(), b.getBook().getAuthor(), b.getBook().getGenre(), b.getBook().getStatus(), null);
            list.add(new BorrowDTO(b.getId(),user,book,b.getBorrowDate(),b.getReturnDate(),b.getStatus()));
        }
        return list;
    }
}
