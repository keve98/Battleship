package com.example.Battleship.controllers;

import com.example.Battleship.entities.UserEntity;
import com.example.Battleship.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }


    @GetMapping("/admin")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    @GetMapping("/user/{name}")
    public ResponseEntity<UserEntity> getUserByName(@PathVariable String name){
        return new ResponseEntity<>(userService.getEntity(name), HttpStatus.OK);
    }





}
