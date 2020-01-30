package com.wildcodeschool.GPADID.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UserIdea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea purchasedIdea;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User purchasedByUser;

    private LocalDate date;

    public UserIdea() {
    }

    public UserIdea(Idea idea, User user, LocalDate date) {
        this.purchasedIdea = idea;
        this.purchasedByUser = user;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Idea getPurchasedIdea() {
        return purchasedIdea;
    }

    public void setPurchasedIdea(Idea purchasedIdea) {
        this.purchasedIdea = purchasedIdea;
    }

    public User getPurchasedByUser() {
        return purchasedByUser;
    }

    public void setPurchasedByUser(User purchasedByUser) {
        this.purchasedByUser = purchasedByUser;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
