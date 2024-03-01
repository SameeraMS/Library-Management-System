package org.example.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
public class Branch {

    @Id
    String id;
    String location;
    int telephone;
    String email;
    String address;
    @OneToMany(mappedBy = "branch",cascade = CascadeType.ALL, orphanRemoval = true)
    List<Book> books;
}
