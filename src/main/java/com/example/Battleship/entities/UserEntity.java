package com.example.Battleship.entities;


import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @SequenceGenerator(name="UserSequence", sequenceName="sequserid", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="UserSequence")
    @Column(name = "id")
    private Long Id;

    @Column(name = "player_name")
    private String player_name;

    @Column(name = "password")
    private String password;

    @Column(name = "isAdmin")
    private boolean isAdmin;



    public UserEntity(Long id, String player_name, String password, boolean isAdmin) {
        Id = id;
        this.player_name = player_name;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return Id;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
