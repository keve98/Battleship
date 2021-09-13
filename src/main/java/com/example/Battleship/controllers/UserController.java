package com.example.Battleship.controllers;


import com.example.Battleship.entities.UserEntity;
import com.example.Battleship.services.UserDetailsServiceImpl;
import com.example.Battleship.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;


@RestController
public class UserController {


    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    public String username;
    public String password;


    @Autowired
    public UserController(UserService userService, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userService = userService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }


    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody UserEntity entity){
        UserDetails user = userDetailsServiceImpl.loadUserByUsername(entity.getUsername());
        return (user.getPassword().equals(entity.getPassword()));

    }


    @GetMapping("/admin")
    public ResponseEntity<List<Object>> getAllUsers(){
        return new ResponseEntity<>(userService.getUsersOnly(), HttpStatus.OK);
    }


    @GetMapping("/user/{name}")
    public ResponseEntity<String> getUserByUsername(@PathVariable String name){
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
