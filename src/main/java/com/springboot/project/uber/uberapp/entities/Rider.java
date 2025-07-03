package com.springboot.project.uber.uberapp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name ="user_id", unique = true)
    private User user; // One-to-one relationship with User entity, each rider is a user

    private Double rating; // Rating of the rider, can be used to determine the rider's reliability or behavior during rides
}
