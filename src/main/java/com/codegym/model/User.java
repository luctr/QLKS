package com.codegym.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z]*",message = "The name is a letter consisting of the letters a-z A-Z and has a length of 2 to 30 characters")
    @Size(min = 2,max = 30)
    private String username;

    @NotEmpty
    @Size(min = 10,max = 20)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*?[0-9]).{10,20}$ ",message =
            "At least one lowercase letter, one uppercase letter, number and password between 10 and 20 characters in length")
    private String password;

    @NotEmpty
    @Size(min = 2,max = 30)
    private String fullName;

    @NotEmpty
    @Past
    @DateTimeFormat(pattern = "MM /dd/yyyy")
    private LocalDate dob;

    @NotEmpty
    @Pattern(regexp = "((09|03|07|08|05)+([0-9]{8})\\b)" ,message = "Error")
    private String phone;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String image;

    @ManyToMany(targetEntity = Role.class)
    private List<Role> roles;

    public User() {
    }

    public User(String username, String password, String fullName, LocalDate dob, String phone, String email, String image, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.roles = roles;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
