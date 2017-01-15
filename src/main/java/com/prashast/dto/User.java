package com.prashast.dto;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "T_USER")
public class User {

    private String username;
    private String firstName;
    private String lastName;
    private String name;
    private String password;
    private ArrayList<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public String toString(){
        return "User= {" +
                "username: " + getUsername()+ ", "+
                "firstName: "+ getFirstName()+ ", "+
                "lastName: "+getLastName()+ ", "+
                "name: "+ getName()+ ", "+
                "roles: " + getRoles()+
                "}";
    }
}
