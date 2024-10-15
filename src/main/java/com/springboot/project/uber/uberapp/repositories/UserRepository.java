package com.springboot.project.uber.uberapp.repositories;

import com.springboot.project.uber.uberapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
