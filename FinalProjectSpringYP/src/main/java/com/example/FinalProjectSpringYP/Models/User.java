package com.example.FinalProjectSpringYP.Models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class User {

    private Long idUser;
    @Size(min = 3, max = 25, message = "The login cannot be less than 8 characters and more than 20 characters")
    @NotBlank(message = "Login is required")
    private String username;
    @Size(min = 8, message = "The password cannot be less than 8 characters")
    @NotBlank(message = "Password is required")
    private String password;

    private boolean active;

    private Set<Role> roles;

    public User() {
    }

    public User(Long idUser, String username, String password, boolean active, Set<Role> roles) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
