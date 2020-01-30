package com.wildcodeschool.GPADID.Controller;

import com.wildcodeschool.GPADID.Entity.Idea;
import com.wildcodeschool.GPADID.Entity.Theme;
import com.wildcodeschool.GPADID.Entity.User;
import com.wildcodeschool.GPADID.Entity.UserIdea;
import com.wildcodeschool.GPADID.Repository.IdeaRepository;
import com.wildcodeschool.GPADID.Repository.ThemeRepository;
import com.wildcodeschool.GPADID.Repository.UserIdeaRepository;
import com.wildcodeschool.GPADID.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class IdeaController {

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private UserIdeaRepository userIdeaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @GetMapping("/idea")
    public String idea(Model out, @RequestParam(value = "themeid", required = true) Long id, HttpSession session) {
        //TODO faire un random
        User user = (User) session.getAttribute("sessionUser");
        Optional<Theme> theme = themeRepository.findById(id);
        List<Idea> filterdideas = ideaRepository.findByThemeId(id);

        out.addAttribute("listideas", ideaRepository.findByThemeAndUserIsNot(theme.get(), user));
        return "idea";
    }

    @GetMapping("/buyidea")
    public String buyidea(Model out, @RequestParam(value = "id", required = true) Long id, HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        Optional<Idea> idea = ideaRepository.findById(id);
        if (idea.isPresent()) {
            if (user.getCredit() >= 0 && user.getCredit() >= idea.get().getPrice()) {
                user.setCredit(user.getCredit() - idea.get().getPrice());
                userIdeaRepository.save(new UserIdea(idea.get(), user, null));
                userRepository.save(user);
                return "purchased";
            }
        }
        out.addAttribute("credits", user.getCredit());
        return "nocredit";
    }

    @PostMapping("/ideaSave")
    public String ideaSave(@ModelAttribute Idea idea) {
        ideaRepository.save(idea);
        return "redirect:/admin";
    }
}