package com.example.Battleship.services;

import com.example.Battleship.entities.UserDataEntity;
import com.example.Battleship.entities.UserEntity;
import com.example.Battleship.repositories.RoleRepository;
import com.example.Battleship.repositories.UserDataRepository;
import com.example.Battleship.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDataRepository userDataRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserDataRepository userDataRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDataRepository = userDataRepository;
    }

    public UserEntity getEntity(String name){
        return userRepository.findByName(name);
    }
    public UserDataEntity getUserData(String name){
        return userDataRepository.findDataByName(name);
    }
    public List<Object> getUsersOnly(){return userDataRepository.findUsersOnly();}



}
