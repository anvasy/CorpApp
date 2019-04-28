package com.anvasy.controller;

import com.anvasy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UsersController {

    @RequestMapping(method = RequestMethod.GET, value = "/admin")
    public ModelAndView getData() {
        ModelAndView modelAndView = new ModelAndView("admin");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admin", params = "delete")
    public String logOut(HttpSession session) {

        session.removeAttribute("role");
        session.removeAttribute("username");

        return "redirect:/home";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ModelAndView logIn() {
        User user = new User();
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public ModelAndView register() {
        User user = new User();
        ModelAndView modelAndView = new ModelAndView("/register");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String addUser(@ModelAttribute("user") User user) {
        System.out.println(user.getUsername());

        return "redirect:/home";
    }
}
