package com.springboot.project.uber.uberapp.entities;

import com.springboot.project.uber.uberapp.entities.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.LAZY) // it will create a separate table for roles with name app_User_roles
    @Enumerated(EnumType.STRING) // Storing enums as strings, basically we are telling JPA to store the enum values as strings in the database otherwise it will store as integers based on the order of declaration in the enum class
    private Set<Role> roles;
}