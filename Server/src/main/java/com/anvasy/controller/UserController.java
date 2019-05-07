package com.anvasy.controller;

import com.anvasy.model.User;
import com.anvasy.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<User> getUsers() {
        UserService userService = new UserService();
        return userService.getAll();
    }

    @RequestMapping(value = "/user/{username}/{role}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeRole(@PathVariable("username") String username, @PathVariable("role") String role) {
        UserService userService = new UserService();
        userService.changeRole(username, role);
        return ResponseEntity.ok("");
    }

    @PostMapping("/user/{new}")
    public @ResponseBody User registerLogin(@PathVariable("new") String isNew, @RequestBody User user) {
        UserService userService = new UserService();
        if(isNew.equals("0"))
            return userService.get(user.getUsername(), user.getPassword());
        else
            return userService.register(user);
    }
}
