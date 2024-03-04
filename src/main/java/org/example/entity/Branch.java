package org.example.entity;

import jakarta.persistence.*;
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

    @Column(unique = true)
    String location;
    int telephone;
    String email;
    String address;

}
