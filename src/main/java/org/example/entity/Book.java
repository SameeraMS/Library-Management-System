package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Branch_id")
    Branch branch;
}
