package org.example.dto.tm;

import lombok.*;
import org.example.dto.BookDTO;
import org.example.dto.UserDTO;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BorrowTm {
    String id;
    String user;
    String book;
    LocalDate borrowDate;
    LocalDate returnDate;
    String status;
}
