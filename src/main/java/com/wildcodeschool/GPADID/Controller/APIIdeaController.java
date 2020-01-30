package com.wildcodeschool.GPADID.Controller;

import com.wildcodeschool.GPADID.Entity.Idea;
import com.wildcodeschool.GPADID.Entity.Theme;
import com.wildcodeschool.GPADID.Repository.IdeaRepository;
import com.wildcodeschool.GPADID.Repository.ThemeRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class APIIdeaController {

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @GetMapping(path = "/api/ideas", produces = "application/json")
    public List<Idea> getIdeas() {
        return ideaRepository.findAll();
    }

    @GetMapping(path = "/api/ideas/{id}", produces = "application/json")
    public Idea getIdea(@PathVariable Long id) {
        return ideaRepository.findById(id).get();
    }

    /**
     * {
     *     "title": "titre",
     *     "description": "Desc courte",
     *     "longDescription": "Desc longue",
     *     "price": 22,
     *     "theme": {
     *         "id": 1
     *     },
     *     "user": {
     *             "id": 1
     *     }
     * }
     * @param idea
     * @return
     */

    @PostMapping(path = "/api/ideas", produces = "application/json")
    public Idea createIdea(@RequestBody Idea idea) {
        return ideaRepository.save(idea);
    }


    /**
     * {
     *     "title": "Update 7",
     *     "description": "Desc Update 7",
     *     "longDescription": "Desc Update 7",
     *     "price": 7,
     *     "theme": {
     *         "id": 1
     *     },
     *     "user": {
     *             "id": 2
     *     }
     * }
     * @param idea
     * @param id
     * @return
     */

    @PutMapping(path = "/api/ideas/{id}", produces = "application/json")
    public Idea update(@RequestBody Idea idea, @PathVariable Long id) {
        idea.setId(id);
        return ideaRepository.save(idea);
    }

    @DeleteMapping(path = "/api/ideas/{id}", produces = "application/json")
    public boolean deleteIdea(@PathVariable Long id) {
        ideaRepository.deleteById(id);
        return true;
    }
}