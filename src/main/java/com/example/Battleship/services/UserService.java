package com.example.Battleship.services;

import com.example.Battleship.entities.UserDataEntity;
import com.example.Battleship.entities.UserEntity;
import com.example.Battleship.entities.UserRole;
import com.example.Battleship.repositories.RoleRepository;
import com.example.Battleship.repositories.UserDataRepository;
import com.example.Battleship.repositories.UserRepository;
import com.example.Battleship.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDataRepository userDataRepository;
    private UserRoleRepository userRoleRepository;

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

    public void saveUserData(UserDataEntity userDataEntity){
        userDataRepository.save(userDataEntity);
    }

    public void saveUser(UserEntity userEntity){
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    public void saveUserRole(UserRole userRole){
        userRoleRepository.save(userRole);
    }

    public boolean login(String username, String password){
        UserEntity user = userRepository.findByName(username);
        String passwordtmp =  bCryptPasswordEncoder.encode(password);
        if(user.getPassword() == passwordtmp) {
            return true;
        }else{
            return false;
        }
    }




}
