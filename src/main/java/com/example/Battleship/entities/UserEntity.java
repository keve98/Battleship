package com.example.Battleship.entities;


import javax.persistence.*;

@Entity
@Table(name = "user_entity")
public class UserEntity {

    @Id
    @Column(name = "player_name")
    private String player_name;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;



    public UserEntity(Long id, String player_name, String password, String role) {
        this.player_name = player_name;
        this.password = password;
        this.role = role;
    }

    public UserEntity() {

    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setAdmin(String role) {
        this.role = role;
    }
}
