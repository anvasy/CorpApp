package com.anvasy.controller;

import com.anvasy.model.User;
import com.anvasy.rest.RestUserConnector;
import com.anvasy.utils.OAuthUtils;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    @RequestMapping(method = RequestMethod.POST, value = "/logout")
    public String logOut(HttpSession session) {
        session.removeAttribute("role");
        session.removeAttribute("username");

        return "redirect:/home";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ModelAndView login() {
        User user = new User();
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/changerole")
    public String changeRole(@RequestParam("role") String role, @RequestParam("username") String username) {
        restUserConnector.changeRole(username, role);
        return "redirect:/admin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public ModelAndView register() {
        User user = new User();
        ModelAndView modelAndView = new ModelAndView("/register");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ModelAndView addUser(@ModelAttribute("user") User user, HttpSession session) {
        user = restUserConnector.logInOrRegister(user, "1");
        if(user == null) {
            ModelAndView modelAndView = new ModelAndView("/register");
            modelAndView.addObject("exists", true);
            return modelAndView;
        }
        session.setAttribute("role", user.getRole());
        session.setAttribute("username", user.getUsername());
        return new ModelAndView("/home");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public RedirectView logIn(@ModelAttribute("user") User user, HttpSession session, RedirectAttributes attributes) {
        user = restUserConnector.logInOrRegister(user, "0");
        if(user == null) {
            attributes.addAttribute("incorr", true);
            return new RedirectView("login");
        }
        session.setAttribute("role", user.getRole());
        session.setAttribute("username", user.getUsername());
        return new RedirectView("home");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/auth")
    public String logGit(@RequestParam("code") String code, HttpSession session) throws IOException, JSONException {
        User user = restUserConnector.logInOrRegister(OAuthUtils.getGitAccess(code), "2");
        session.setAttribute("role", user.getRole());
        session.setAttribute("username", user.getUsername());
        return "redirect:/home";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/authvk")
    public String logVK(@RequestParam("code") String code, HttpSession session) throws IOException, JSONException {
        User user = restUserConnector.logInOrRegister(OAuthUtils.getVkAccess(code), "2");
        session.setAttribute("role", user.getRole());
        session.setAttribute("username", user.getUsername());
        return "redirect:/home";
    }
}
