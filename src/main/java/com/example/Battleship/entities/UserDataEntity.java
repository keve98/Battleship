package com.example.Battleship.entities;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "user_data")
public class UserDataEntity {


    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Id
    @Column(name = "username")
    String username;

    @Column(name = "name")
    String name;

    @Column(name = "phone")
    String phone;

    @Column(name = "email")
    String email;

    @Column(name = "address")
    String address;

    public UserDataEntity(String username, String name, String phone, String email, String address) {
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;

    }

    public UserDataEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString(){
        return this.getUsername() + " : " + this.getName() +" : " + this.getAddress() +" : " + this.getEmail() +" : " + this.getPhone();
    }
}
