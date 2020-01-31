package com.wildcodeschool.GPADID.Controller;

import com.wildcodeschool.GPADID.Entity.Idea;
import com.wildcodeschool.GPADID.Entity.User;
import com.wildcodeschool.GPADID.Entity.UserIdea;
import com.wildcodeschool.GPADID.Repository.IdeaRepository;
import com.wildcodeschool.GPADID.Repository.UserIdeaRepository;
import com.wildcodeschool.GPADID.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfilController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private UserIdeaRepository userIdeaRepository;

    @GetMapping("/profil")
    public String profil(Model out, HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        if (user == null) {
            return "redirect:/";
        }
        out.addAttribute("ideas", user.getIdeaList());
        out.addAttribute("credits", user.getCredit());
        out.addAttribute("admin", user.isAdmin());
        return "profile";
    }
}
