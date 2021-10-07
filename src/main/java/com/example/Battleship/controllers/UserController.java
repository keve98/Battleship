package com.example.Battleship.controllers;


import com.example.Battleship.entities.UserAllData;
import com.example.Battleship.entities.UserDataEntity;
import com.example.Battleship.entities.UserEntity;
import com.example.Battleship.entities.UserRole;
import com.example.Battleship.services.UserDetailsServiceImpl;
import com.example.Battleship.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PostMapping(value = "/login")
    public boolean login(@RequestBody UserEntity entity) throws Exception{
        System.out.println("login start");
        if(!userService.login(entity.getUsername(), entity.getPassword())){
            //throw new Exception("Authentication failed");
            return false;
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(entity.getUsername(), entity.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return true;

    }

    @PostMapping(value = "/save")
    public UserDataEntity saveUser(@RequestBody UserAllData newData) throws Exception{
        String username = newData.getUsername();
        String password = newData.getPassword();

        UserEntity userObj = null;

        if(username != null && password != null){
            userObj = userService.getEntity(username);
        }

        if(userObj != null){
            throw new Exception("User with name '" + username +"' already exists!");
        }

        UserEntity newUserEntity = new UserEntity(username, password);
        UserDataEntity newUserDataEntity = new UserDataEntity(newData.getUsername(), newData.getName(), newData.getPhone(), newData.getEmail(), newData.getAddress());

        userService.saveUser(newUserEntity);
        newUserDataEntity.setId(newUserEntity.getId());
        userService.saveUserData(newUserDataEntity);
        userService.saveUserRole(new UserRole(newUserEntity.getId(), 1L));
        return newUserDataEntity;

    }

    @GetMapping("/admin")
    public ResponseEntity<List<UserDataEntity>> getAllUsers() throws Exception {
        if(isAdmin())
            return new ResponseEntity<>(userService.getUsersOnly(), HttpStatus.OK);
       throw new Exception("You don't have access to reach this page!");
    }


    @GetMapping("/user/{name}")
    public ResponseEntity<UserDataEntity> getUserByUsername(@PathVariable String name) throws Exception {
        System.out.println("getuserbyname start");
        boolean authenticated = false;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals(name))
            authenticated = true;
        if(authenticated || isAdmin()) {
            if(authenticated && isAdmin()){
                throw new Exception("Admins haven't got personal data in the database");
            }else {
                return new ResponseEntity<>(userService.getUserData(name), HttpStatus.OK);
            }
        }else{
            throw new Exception("You don't have access to reach this page!");
        }
    }

    public boolean isAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
    }
}
