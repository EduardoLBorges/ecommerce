package com.eduardoborges.ecommerce.service;

import com.eduardoborges.ecommerce.dto.InsertUserDTO;
import com.eduardoborges.ecommerce.dto.UserDTO;
import com.eduardoborges.ecommerce.entity.User;
import com.eduardoborges.ecommerce.entity.enums.Role;
import com.eduardoborges.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

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
                Role.USER
        );

        var userSaved = userRepository.save(entity);

        return userSaved.getId();
    }

    public Optional<UserDTO> getUserById(Long userId){

        return userRepository.findById(userId).
                map(UserDTO::new);
    }

    public List<UserDTO> getUsersList(){

        return userRepository.findAll()
                .stream().
                map(UserDTO::new).
                toList();
    }

    public void updateUser(Long userId,
                           InsertUserDTO insertUserDTO){

        var userEntity = userRepository.findById(userId);

        if (userEntity.isPresent()){
            var user = userEntity.get();

            if (insertUserDTO.name() != null){
                user.setName(insertUserDTO.name());
            }

            if (insertUserDTO.password() != null){
                user.setPassword(insertUserDTO.password());
            }

            userRepository.save(user);
        }
    }

    public void deleteUserById(Long userId){

        userRepository.deleteById(userId);
    }
}