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
        out.addAttribute("listideas", ideaRepository.findByThemeAndUserIsNot(theme.get(), user));
        out.addAttribute("admin", user.isAdmin());
        return "idea";
    }

    @GetMapping("/buyidea")
    public String buyidea(Model out, @RequestParam(value = "id", required = true) Long id, HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        Optional<Idea> idea = ideaRepository.findById(id);
        List<UserIdea> usersIdea = user.getIdeaList();
        out.addAttribute("admin", user.isAdmin());
        if (idea.isPresent()) {
            Optional<UserIdea> existing = userIdeaRepository.findByPurchasedIdeaAndPurchasedByUser(idea.get(), user);
            out.addAttribute("ideaname", idea.get().getTitle());
            try {
                if (existing.isEmpty()) {
                    if (user.getCredit() >= 0 && user.getCredit() >= idea.get().getPrice()) {
                        user.setCredit(user.getCredit() - idea.get().getPrice());
                        UserIdea newUserIdea = new UserIdea(idea.get(), user, null);
                        usersIdea.add(newUserIdea);
                        userIdeaRepository.save(newUserIdea);
                        userRepository.save(user);
                        return "purchased";
                    }
                }
            } catch (Exception e) {
                return "nocredit";
            }
        }
        out.addAttribute("credits", user.getCredit());

        return "nocredit";
    }

    @PostMapping("/ideaSave")
    public String ideaSave(Model out, @ModelAttribute Idea idea, HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        ideaRepository.save(idea);
        List<Idea> myideas = user.getUserIdeas();
        myideas.add(idea);
        userRepository.save(user);
        out.addAttribute("admin", user.isAdmin());
        return "redirect:/admin";
    }
}