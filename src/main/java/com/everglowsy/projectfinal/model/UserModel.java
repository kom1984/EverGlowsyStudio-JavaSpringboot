package com.everglowsy.projectfinal.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="User")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    @NotEmpty( message ="ne doit pas être null ou vide")
    private String firstName;
    @NotEmpty( message ="ne doit pas être null ou vide")
    private String lastName;

    @NotEmpty( message ="ne doit pas être null ou vide")
    @Column(unique = true)
    private String email;
    @NotEmpty( message ="ne doit pas être null ou vide")
    private String telephone;
    @NotEmpty ( message ="ne doit pas être null ou vide")
    @Size(min = 12,
            message = "le mot de passe doit comporter au moins 12 caractères")
    private String password;
    @Transient
    @NotEmpty( message ="ne doit pas être null ou vide")
    @Size(min = 12,
            message = "le mot de passe doit comporter au moins 12 caractères")
    private String passwordConfirm;
    @Enumerated(EnumType.STRING)
    private Role role;
    public UserModel(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;

    }
    public UserModel(String email) {
        this.email = email;
    }

    public UserModel() {

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

//,firstName='%s',lastName='%s' ,firstName,lastName

   @Override
    public String toString() {
        String result = String.format(
                "User[id=%d, name='%s',userName='%s %s']%n",
                id_user, email ,firstName,lastName);

        return result;
    }


}

