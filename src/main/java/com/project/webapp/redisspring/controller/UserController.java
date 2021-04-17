package com.project.webapp.redisspring.controller;

import com.project.webapp.redisspring.model.User;
import com.project.webapp.redisspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String showHome(Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        Boolean authenticated = (Boolean) session.getAttribute("Authenticated");
        model.addAttribute("authenticated", authenticated);
        return "index";
    }

    @GetMapping("/registration_form")
    public String showRegistrationForm(Model model, HttpSession session) {
        User user = new User();
        @SuppressWarnings("unchecked")
        Boolean authenticated = (Boolean) session.getAttribute("Authenticated");
        model.addAttribute("user", user);
        model.addAttribute("authenticated", authenticated);
        return "registration_form";
    }

    @GetMapping("/login_form")
    public String showLoginForm(Model model, HttpSession session) {
        User user = new User();
        @SuppressWarnings("unchecked")
        Boolean authenticated = (Boolean) session.getAttribute("Authenticated");
        model.addAttribute("user", user);
        if(authenticated == null || !authenticated){
            model.addAttribute("authenticated", false);
        } else {
            model.addAttribute("authenticated", true);
        }
        return "login_form";
    }

    @PostMapping("/getDetails")
    public String getDetails(@ModelAttribute User user, Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        Boolean authenticated = (Boolean) session.getAttribute("Authenticated");
        if(authenticated== null || !authenticated){
            model.addAttribute("authenticated", authenticated);
            return "index";
        }
        Optional<User> temp = userService.fetchUserById(user.getEmail());
        if(temp.isPresent()){
            model.addAttribute("user", temp.get());
        }else{
            model.addAttribute("user", new User());
        }
        return "user_details";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("user") User user, Errors errors, Model model) {
        if (null != errors && errors.getErrorCount() > 0) {
            return "registration_form";}
        model.addAttribute("user", user);
        Optional<User> temp = userService.fetchUserById(user.getEmail());
        if (temp.isEmpty()) {
            userService.saveUser(user);
            return "login_form";
        }else{
            model.addAttribute("userExist",true);
            return "registration_form";
        }
    }

    @GetMapping("/login")
    public String login(Model model, HttpSession httpSession){
        @SuppressWarnings("unchecked")
        Boolean authenticated = (Boolean) httpSession.getAttribute("Authenticated");
        if(authenticated== null || !authenticated){
            model.addAttribute("authenticated", false);
            return "index";
        }

        model.addAttribute(new User());
        return "user_details";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpServletRequest request) {
        Optional<User> temp = userService.fetchUserById(user.getEmail());
        if (temp.isPresent()) {
            if (user.getPassword().equals(temp.get().getPassword())) {
                model.addAttribute("user", temp.get());
                request.getSession().setAttribute("Authenticated", true);
                model.addAttribute("authenticated", true);
                return "user_details";
            }
        }
        model.addAttribute("authenticated", false);
        return "login_form";
    }

    @GetMapping("/logout")
    public String logout( Model model, HttpSession httpSession){
        httpSession.invalidate();
        model.addAttribute("authenticated", false);
        return "index";
    }
}
