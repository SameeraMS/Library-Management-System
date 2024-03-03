package org.example.dto;

import lombok.*;
import org.example.entity.Branch;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    String name;
    String email;
    String password;
    BranchDTO Branch;
}
