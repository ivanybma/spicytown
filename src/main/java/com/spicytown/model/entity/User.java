package com.spicytown.model.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by yunlongxu on 4/27/16.
 */
@Entity
@Table(name = "user")
public class User {
    // An autogenerated id(unique for each user in the db)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String password;

    @NotNull
    private boolean verified;

    @NotNull
    private String role;

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(String name, String password, boolean verified, String role) {
        this.name = name;
        this.password = password;
        this.verified = verified;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
