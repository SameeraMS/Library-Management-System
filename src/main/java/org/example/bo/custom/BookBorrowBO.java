package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.BookDTO;
import org.example.dto.BorrowDTO;

public interface BookBorrowBO extends SuperBO {
    void borrowBook(BorrowDTO borrowDTO, BookDTO book);
}
