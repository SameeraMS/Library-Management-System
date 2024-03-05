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
     String id;
    @ManyToOne
     User user;
    @ManyToOne
     Book book;
     LocalDate borrowDate;
     LocalDate returnDate;
     String status;
}
