package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "Borrowing_Books")
public class BorrowBooks {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "User_email")
    private User user;
    @ManyToOne
    @JoinColumn(name = "Book_id")
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
