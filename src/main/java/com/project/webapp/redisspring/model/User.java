package com.project.webapp.redisspring.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

import javax.validation.constraints.*;

@Data
@RedisHash("USER")
public class User implements Serializable {
    @NotNull(message = "Email can not be null!!")
    @NotEmpty(message = "Email can not be empty!!")
    @Email(message = "Please Enter a valid email")
    @Id
    private String email;

    @NotNull(message = "First Name can not be null!!")
    @NotEmpty(message = "First Name can not be empty!!")
    private String firstName;

    @NotNull(message = "Last Name can not be null!!")
    @NotEmpty(message = "Last Name can not be empty!!")
    private String lastName;
    private String occupation;

    @NotNull
    @Min(1)
    private int age;
    private String gender;


    @NotNull(message = "Password can not be null!!")
    @NotEmpty(message = "Password can not be empty!!")
    @Min(value = 8, message = "Password can't be less than 8 Character")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
