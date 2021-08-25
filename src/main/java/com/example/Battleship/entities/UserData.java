package com.example.Battleship.entities;


import javax.persistence.*;


@Table(name = "user_data")
public class UserData {

    @Id
    @Column(name = "player_name")
    private String player_name;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;


    public UserData(String player_name, String name, String address, String phone, String email) {
        this.player_name = player_name;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
