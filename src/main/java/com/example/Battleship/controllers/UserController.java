package com.example.Battleship.controllers;

import com.example.Battleship.entities.UserDataEntity;
import com.example.Battleship.entities.UserEntity;
import com.example.Battleship.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<Object>> getAllUsers(){
        return new ResponseEntity<>(userService.getUsersOnly(), HttpStatus.OK);
        //return new ResponseEntity<>(userService.getAllData(), HttpStatus.OK);
    }


    @GetMapping("/user/{name}")
    public ResponseEntity<String> getUserByName(@PathVariable String name){
        boolean authenticated = SecurityContextHolder.getContext().getAuthentication().getName().equals(name);
        if(authenticated || isAdmin()) {
            if(authenticated && isAdmin()){
                return new ResponseEntity<>("Admins haven't got personal data in the database", HttpStatus.OK);
            }else {
                return new ResponseEntity<>(userService.getUserData(name).toString(), HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<>("You don't have access to reach this page!", HttpStatus.OK);
        }
    }

    public boolean isAdmin(){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
    }

}
