package com.wildcodeschool.GPADID.Controller;


import com.wildcodeschool.GPADID.Entity.Theme;
import com.wildcodeschool.GPADID.Entity.User;
import com.wildcodeschool.GPADID.Repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ThemeController {

    @Autowired
    private ThemeRepository themeRepository;

    @GetMapping("/catalogue")
    public String catalogue(Model out, HttpSession session) {
        User user = (User) session.getAttribute("sessionUser");
        out.addAttribute("themelist", themeRepository.findAll());
        out.addAttribute("admin", user.isAdmin());
        return "themes";
    }

    @GetMapping("/selecttheme")
    public String ideas(@RequestParam(value = "id", required = true) Long id, HttpSession session, Model out) {
        User user = (User) session.getAttribute("sessionUser");
        out.addAttribute("admin", user.isAdmin());
        return "redirect:/idea?themeid=" + id;
    }

    @PostMapping("/themeSave")
    public String themeSave(@ModelAttribute Theme theme, HttpSession session, Model out) {
        User user = (User) session.getAttribute("sessionUser");
        themeRepository.save(theme);
        out.addAttribute("admin", user.isAdmin());
        return "redirect:/admin";
    }
}

