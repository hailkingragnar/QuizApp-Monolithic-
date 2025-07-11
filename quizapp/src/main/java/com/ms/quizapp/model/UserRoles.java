package com.ms.quizapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserRoles {
    @Id
    private String id;
    private String roles;

    @ManyToMany(mappedBy = "role")
    private List<Users> usersList;
}
