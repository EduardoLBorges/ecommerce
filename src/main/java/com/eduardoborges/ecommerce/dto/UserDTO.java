package com.eduardoborges.ecommerce.dto;

import com.eduardoborges.ecommerce.entity.User;
import com.eduardoborges.ecommerce.entity.enums.Role;

public record UserDTO(Long id,
                      String name,
                      String email,
                      Role role) {
    public UserDTO(User user){
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
