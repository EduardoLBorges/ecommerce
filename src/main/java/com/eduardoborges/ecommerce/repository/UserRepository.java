package com.eduardoborges.ecommerce.repository;

import com.eduardoborges.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
