package com.eduardoborges.ecommerce.dto;

import com.eduardoborges.ecommerce.entity.User;
import com.eduardoborges.ecommerce.entity.enums.Role;

public record InsertUserDTO(String name,
                            String email,
                            String password,
                            Role role) {
    public InsertUserDTO(User user){
        this(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }
}
