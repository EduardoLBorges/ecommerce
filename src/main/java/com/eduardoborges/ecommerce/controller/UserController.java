package com.eduardoborges.ecommerce.controller;

import com.eduardoborges.ecommerce.dto.InsertUserDTO;
import com.eduardoborges.ecommerce.dto.UserDTO;
import com.eduardoborges.ecommerce.entity.User;
import com.eduardoborges.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
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
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Long userId){

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
}