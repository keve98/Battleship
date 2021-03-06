package com.example.Battleship.controllers;


import com.example.Battleship.entities.UserAllData;
import com.example.Battleship.entities.UserDataEntity;
import com.example.Battleship.entities.UserEntity;
import com.example.Battleship.entities.UserRole;
import com.example.Battleship.services.UserDetailsServiceImpl;
import com.example.Battleship.services.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;


import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {


    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    public String username;
    public String password;
    String princ;


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
    public UserRole login(@RequestBody UserEntity entity) throws Exception{
        System.out.println("login start");
        if(!userService.login(entity.getUsername(), entity.getPassword())){
            throw new Exception("Bad credentials");
        }

        UserEntity ret = userService.getEntity(entity.getUsername());

        if(!ret.isEnabled()){
            throw new Exception("Your account is not verified yet. Please check your emails.");
        }
        String userroleid = userService.getRoleIdFromUserId(ret.getId());
        String principlename = userService.getRoleNameFromId(Integer.parseInt(userroleid));
        UserRole userroleentity = new UserRole();
        userroleentity.setPrinciple(principlename);
        userroleentity.setUsername(entity.getUsername());
        userroleentity.setUserid(Long.parseLong(userroleid));
        userroleentity.setRoleid(Long.parseLong(userroleid));
        Authentication authentication = new UsernamePasswordAuthenticationToken(entity.getUsername(), entity.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        princ = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.username = entity.getUsername();

        return userroleentity;

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

        String randomCode = RandomString.make(64);
        newUserEntity.setVerificatonCode(randomCode);
        newUserEntity.setEnabled(false);

        userService.saveUser(newUserEntity);
        newUserDataEntity.setId(newUserEntity.getId());
        userService.saveUserData(newUserDataEntity);
        userService.saveUserRole(new UserRole(newUserEntity.getId(), 1L));

        userService.sendVerificationEmail(newUserDataEntity, newUserEntity);

        return newUserDataEntity;

    }

    @GetMapping("/verify/{code}")
    public UserEntity verify(@PathVariable String code) throws Exception {
        userService.verify(code);
        UserEntity temp = userService.findByCode(code);
        if(temp == null){
            throw new Exception("Invalid verification code");
        }
        if(!temp.isEnabled()){
            throw new Exception("Verification is failed, try an other one.");
        }
        return temp;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<UserDataEntity>> getAllUsers() throws Exception {
        if(isAdmin()) {
            List<UserDataEntity> users = new ArrayList();
            users = userService.getUsersOnly();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }else {
            throw new Exception("You don't have access to reach this page!");
        }
    }


    @GetMapping("/user/{name}")
    public ResponseEntity<UserDataEntity> getUserByUsername(@PathVariable String name) throws Exception {
            System.out.println("getuserbyname start");
            boolean authenticated = false;
            if (this.username.equals(name))
                authenticated = true;
            if (authenticated || isAdmin()) {
                if (authenticated && isAdmin()) {
                    throw new Exception("Admins haven't got personal data in the database");
                } else {
                    System.out.println("return personal data for user");
                    return new ResponseEntity<>(userService.getUserData(name), HttpStatus.OK);
                }
            } else {
                throw new Exception("You don't have access to reach this page!");
            }
    }

    @GetMapping("/searchUsernames/{username}")
    public ResponseEntity<List<UserDataEntity>> searchUsernames(@PathVariable String username) throws Exception{
        if(isAdmin()){
            System.out.println("search username: " + username);
            List<UserDataEntity> list = new ArrayList();
            list = userService.searchUsernames(username);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }else{
            throw new Exception("You havent got an admin principle.");
        }
    }

    @GetMapping("/logout")
    public void logout(){
        this.username = "";
        this.princ = "";
        SecurityContextHolder.clearContext();
    }

    @GetMapping("/isAdmin")
    public boolean isAdminOrUser(){
        return isAdmin();
    }


    public boolean isAdmin() {
        return princ.equals("admin");
    }
}
