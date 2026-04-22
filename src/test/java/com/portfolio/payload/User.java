package com.portfolio.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonIgnore
    private int id;
    private String name;
    private String email;
    private String gender;  // "male" or "female"
    private String status;  // "active" or "inactive"

    // Default constructor (needed by Jackson)
    public User() {}

    // Constructor for creating request body
    public User(String name, String email, String gender, String status) {
        this.name   = name;
        this.email  = email;
        this.gender = gender;
        this.status = status;
    }

    // Getters and Setters
    public int getId()              { return id; }
    public void setId(int id)       { this.id = id; }
    public String getName()         { return name; }
    public void setName(String n)   { this.name = n; }
    public String getEmail()        { return email; }
    public void setEmail(String e)  { this.email = e; }
    public String getGender()       { return gender; }
    public void setGender(String g) { this.gender = g; }
    public String getStatus()       { return status; }
    public void setStatus(String s) { this.status = s; }
}