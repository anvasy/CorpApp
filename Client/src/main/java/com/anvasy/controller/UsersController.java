package com.anvasy.controller;

import com.anvasy.model.User;
import com.anvasy.rest.RestUserConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private RestUserConnector restUserConnector;

    @RequestMapping(method = RequestMethod.GET, value = "/admin")
    public ModelAndView getData() {
        ModelAndView modelAndView = new ModelAndView("admin");
        List<User> users = restUserConnector.getUsers();
        modelAndView.addObject("users", users);
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
