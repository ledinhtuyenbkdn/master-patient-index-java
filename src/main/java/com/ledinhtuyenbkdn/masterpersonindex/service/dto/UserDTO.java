package com.ledinhtuyenbkdn.masterpersonindex.service.dto;

import com.ledinhtuyenbkdn.masterpersonindex.model.HealthCenter;
import com.ledinhtuyenbkdn.masterpersonindex.model.Role;

public class UserDTO {

    private Long id;

    private String userName;

    private String password;

    private String fullName;

    private Role role;

    private HealthCenter healthCenter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public HealthCenter getHealthCenter() {
        return healthCenter;
    }

    public void setHealthCenter(HealthCenter healthCenter) {
        this.healthCenter = healthCenter;
    }
}
