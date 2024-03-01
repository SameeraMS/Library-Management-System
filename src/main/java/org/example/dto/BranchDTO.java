package org.example.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BranchDTO {
    String id;
    String location;
    int telephone;
    String email;
    String address;
}
