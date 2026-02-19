package com.eduardoborges.ecommerce.service;

import com.eduardoborges.ecommerce.dto.InsertUserDTO;
import com.eduardoborges.ecommerce.dto.UserDTO;
import com.eduardoborges.ecommerce.entity.User;
import com.eduardoborges.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createUser(InsertUserDTO insertUserDTO){

        //DTO -> Entity
        var entity = new User(
                null,
                insertUserDTO.name(),
                insertUserDTO.email(),
                insertUserDTO.password(),
                insertUserDTO.role()
        );

        var userSaved = userRepository.save(entity);

        return userSaved.getId();
    }

    public Optional<UserDTO> getUserById(Long userId){

        return userRepository.findById(userId).map(UserDTO::new);
    }
}
