package com.example.Battleship.services;

import com.example.Battleship.entities.UserDataEntity;
import com.example.Battleship.entities.UserEntity;
import com.example.Battleship.entities.UserRole;
import com.example.Battleship.repositories.RoleRepository;
import com.example.Battleship.repositories.UserDataRepository;
import com.example.Battleship.repositories.UserRepository;
import com.example.Battleship.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDataRepository userDataRepository;
    private UserRoleRepository userRoleRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserDataRepository userDataRepository,
                       UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDataRepository = userDataRepository;
        this.userRoleRepository = userRoleRepository;

    }

    public UserEntity getEntity(String name){
        return userRepository.findByName(name);
    }

    public UserDataEntity getUserData(String name){
        return userDataRepository.findDataByName(name);
    }

    public List<UserDataEntity> getUsersOnly(){return userDataRepository.findUsersOnly();}
    public List<UserDataEntity> findAll(){return userDataRepository.findAll();}

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
        if(bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return true;
        }else{
            return false;
        }
    }

    public List<UserDataEntity> searchUsernames(String name){
        return userDataRepository.searchUsernames(name);
    }


    public String getRoleIdFromUserId(Long id){
        return userRoleRepository.getRoleIdFromUserId(id);
    }

    public String getRoleNameFromId(Integer id){
        return roleRepository.getRoleNameFromID(id);
    }


    public void sendVerificationEmail(UserDataEntity userDataEntity, UserEntity user) throws MessagingException, UnsupportedEncodingException {
        String toAddress = userDataEntity.getEmail();
        String fromAddress = "bmestudent07@gmail.com";
        String senderName = "Battleship";
        String subject = "Please verify your registration";


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);


        String verifyURL = "http://localhost:4200/#" + "/verify/" + user.getVerificatonCode();

        String content = "Dear "+userDataEntity.getName()+",<br><br>"
                + "Please click the link below to verify your registration:<br>"
                +"<a href=\""+verifyURL+"\">Verify now</a>"
                + "<br><br>Thank you,<br>"
                + "Battleship Team";


        helper.setText(content, true);

        mailSender.send(message);
    }

    public void sendSpam() throws MessagingException, UnsupportedEncodingException {
        String toAddress = "mucsirobert01@gmail.com";
        String fromAddress = "bmestudent07@gmail.com";
        String senderName = "Battleship";
        String subject = "Szia Lajos";


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);


        //String verifyURL = "http://localhost:4200/#" + "/verify/" + user.getVerificatonCode();

        String content = "Anyád";

        helper.setText(content, true);

        mailSender.send(message);

        toAddress = "kocsilevi@gmail.com";
        helper.setTo(toAddress);
        mailSender.send(message);

        toAddress = "bazsarmartin1@gmail.com";
        helper.setTo(toAddress);
        mailSender.send(message);

    }

    public void verify(String code){
        userRepository.verify(code);
    }

    public UserEntity findByCode(String code){
        return userRepository.findByCode(code);
    }


}
