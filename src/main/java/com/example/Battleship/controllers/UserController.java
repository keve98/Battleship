package com.example.Battleship.controllers;


import com.example.Battleship.entities.UserDataEntity;
import com.example.Battleship.entities.UserEntity;
import com.example.Battleship.entities.UserRole;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes="application/json")
    public UserEntity login(@RequestBody UserEntity entity) throws Exception{
        if(!userService.login(entity.getUsername(), entity.getPassword())){
            throw new Exception("Bad credentials");
        }

        return entity;

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes="application/json")
    public UserDataEntity saveUser(@RequestBody UserDataEntity newData) throws Exception{
        String username = newData.getUsername();
        String password = newData.getPassword();

        UserEntity userObj = null;

        if(username != null && password != null){
            userObj = userService.getEntity(username);
        }

        if(userObj != null){
            throw new Exception("User with name '" + username +"' already exists!");
        }

        userService.saveUser(new UserEntity(username, password));
        userService.saveUserData(newData);
        userService.saveUserRole(new UserRole(newData.getId(), 1L));
        return newData;

       /* UserEntity userEntity = new UserEntity(newData.getUsername(), newData.getPassword());
        UserRole userRole = new UserRole(newData.getId(), 1L);
        userService.saveUser(userEntity);
        userService.saveUserRole(userRole);
        userService.saveUserData(newData);
        return newData;*/
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
