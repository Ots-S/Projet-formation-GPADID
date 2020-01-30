package com.wildcodeschool.GPADID.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "theme")
    private List<Idea> themeIdeas;

    public Theme() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Idea> getThemeIdeas() {
        return themeIdeas;
    }

    public void setThemeIdeas(List<Idea> themeIdeas) {
        this.themeIdeas = themeIdeas;
    }
}
