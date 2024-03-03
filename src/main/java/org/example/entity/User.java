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
public class User {

    String name;
    @Id
    String email;
    String password;
    @Column(unique = true)
    int telephone;

    @ManyToOne
    Branch branch;
}
