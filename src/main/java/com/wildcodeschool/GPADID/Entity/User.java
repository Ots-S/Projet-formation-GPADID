package com.wildcodeschool.GPADID.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    public String email;
    private int credit;
    private boolean admin;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Idea> userIdeas;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "purchasedByUser", cascade = CascadeType.REFRESH)
    private List<UserIdea> ideaList = new ArrayList<>();


    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Idea> getUserIdeas() {
        return userIdeas;
    }

    public void setUserIdeas(List<Idea> userIdeas) {
        this.userIdeas = userIdeas;
    }

    public List<UserIdea> getIdeaList() {
        return ideaList;
    }

    public void setIdeaList(List<UserIdea> ideaList) {
        this.ideaList = ideaList;
    }
}
