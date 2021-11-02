package com.example.Battleship.entities;


import javax.persistence.*;

@Entity
@Table(name = "users_roles")
public class UserRole {

    @Id
    @Column(name = "user_id")
    public Long userid;

    @Column(name = "role_id")
    public Long roleid;

    public String username;
    public String principle;

    public UserRole(Long userid, Long roleid){
        this.userid = userid;
        this.roleid = roleid;
    }

    public UserRole() {

    }


    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrinciple() {
        return principle;
    }

    public void setPrinciple(String principle) {
        this.principle = principle;
    }
}
