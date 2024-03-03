package org.example.dto;

import lombok.*;
import org.example.entity.Branch;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookDTO {
    String id;
    String title;
    String author;
    String genre;
    String status;
    BranchDTO branch;
}
