package com.wildcodeschool.GPADID.Controller;

import com.wildcodeschool.GPADID.Entity.Idea;
import com.wildcodeschool.GPADID.Entity.Theme;
import com.wildcodeschool.GPADID.Entity.User;
import com.wildcodeschool.GPADID.Repository.UserIdeaRepository;
import com.wildcodeschool.GPADID.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.nio.channels.ScatteringByteChannel;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserIdeaRepository userIdeaRepository;

    @GetMapping("/")
    public String index(Model out) {
        out.addAttribute("newuser", new User());
        return "index";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup.html";
    }


    @PostMapping("/userSave")
    public String userSave(@ModelAttribute User user) {
        user.setCredit(3);
        userRepository.save(user);
        return "index";
    }

    @PostMapping("/login")
    public String login(Model out, @ModelAttribute User user, HttpSession session) {
        Optional<User> verifUser = userRepository.findByEmail(user.getEmail());
        if (verifUser.isPresent()) {
            if (user.getPassword().equals(verifUser.get().getPassword())) {
                user = verifUser.get();
                session.setAttribute("sessionUser", user);
                return "redirect:/profil";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("sessionUser");
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String admin(Model out, HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        if (user == null || !user.isAdmin()) {
            return "redirect:/profil";
        }
        out.addAttribute("theme", new Theme());
        out.addAttribute("idea", new Idea());
        out.addAttribute("admin", user.isAdmin());
        return "admin";
    }



}
