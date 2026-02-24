package com.eduardoborges.ecommerce.controller;

import com.eduardoborges.ecommerce.dto.InsertUserDTO;
import com.eduardoborges.ecommerce.dto.UserDTO;
import com.eduardoborges.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody InsertUserDTO insertUserDTO){

        var userId = userService.createUser(insertUserDTO);
        return ResponseEntity.created(URI.create("/users" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId){

        var user = userService.getUserById(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/list")
    public List<UserDTO> getUsersList(){

        return userService.getUsersList();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId,
                                           @RequestBody InsertUserDTO insertUserDTO){

        userService.updateUser(userId, insertUserDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable Long userId){

        userService.deleteUserById(userId);
    }
}