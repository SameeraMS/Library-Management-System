package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
public class Book {
    @Id
    String id;
    String title;
    String author;
    String genre;
    String status;

    @ManyToOne
    Branch branch;
}
