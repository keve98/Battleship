package com.example.Battleship.services;


import com.example.Battleship.entities.UserEntity;
import com.example.Battleship.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findByName(userName);

        if(optionalUser.isPresent()) {
            UserEntity users = optionalUser.get();

           /* List<String> roleList = new ArrayList<String>();
            roleList.add("ADMIN");
            roleList.add("USER");*/

            return User.builder()
                    .username(users.getPlayer_name())
                    //change here to store encoded password in db
                    .password(bCryptPasswordEncoder.encode(users.getPassword()) )
                    .roles(users.getRole().toUpperCase())
                    .build();
        } else {
            throw new UsernameNotFoundException("User Name is not Found");
        }
    }
}
