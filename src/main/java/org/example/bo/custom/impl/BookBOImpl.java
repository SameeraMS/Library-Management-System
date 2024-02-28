package org.example.bo.custom.impl;

import org.example.bo.custom.BookBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.BookDAO;
import org.example.dto.BookDTO;
import org.example.entity.Book;

import java.sql.SQLException;

public class BookBOImpl implements BookBO {

    BookDAO bookDaoImpl = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);
    @Override
    public boolean save(BookDTO dto) throws SQLException, ClassNotFoundException {
        return bookDaoImpl.save(new Book(dto.getId(),dto.getTitle(),dto.getAuthor(),dto.getGenre(),dto.getStatus()));
    }

    @Override
    public boolean update(BookDTO dto) throws SQLException, ClassNotFoundException {
        return bookDaoImpl.update(new Book(dto.getId(),dto.getTitle(),dto.getAuthor(),dto.getGenre(),dto.getStatus()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return bookDaoImpl.delete(id);
    }

    @Override
    public BookDTO search(String id) throws SQLException, ClassNotFoundException {
        Book search = bookDaoImpl.search(id);
        return new BookDTO(search.getId(),search.getTitle(),search.getAuthor(),search.getGenre(),search.getStatus());
    }
}
