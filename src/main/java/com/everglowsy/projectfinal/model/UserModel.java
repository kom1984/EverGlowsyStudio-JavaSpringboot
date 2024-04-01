package com.everglowsy.projectfinal.model;

import jakarta.persistence.*;

@Entity
@Table(name="User")

public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    private String firstName;
    private String lastName;

    private String email;
    private String telephone;

    private String password;

    @Transient
    private String passwordConfirm;

    @Enumerated(EnumType.STRING)
    private Role role;
    public UserModel(){

    }


    public UserModel(String username) {
        this.email = username;
    }

    public Long getId_user() {
        return id_user;
    }



    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }



   @Override
    public String toString() {
        String result = String.format(
                "User[id=%d, name='%s']%n",
                id_user, email);

        return result;
    }


}

