package com.edisolt.progress.servingwebcontent.controller;

import com.edisolt.progress.servingwebcontent.entity.Role;
import com.edisolt.progress.servingwebcontent.entity.User;
import com.edisolt.progress.servingwebcontent.ropository.UserRepository;
import com.edisolt.progress.servingwebcontent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String addUserMap () {
        return "admin-add-user";
    }

    @PostMapping()
    public String regNewUser (@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String email,
                              Model model) {
        User user = new User(username, password, email);
        user.setRoles(Collections.singleton(Role.USER));

        String message;

        if (userService.addNewUserToDb(user)) {
            message = "User successfully registered!";
        } else message = "User already exists!";

        model.addAttribute("message", message);
        return "redirect:/users";
    }

    @PutMapping("{user}")
    public String updateUser (@PathVariable User user,
                              @RequestParam String newUsername,
                              @RequestParam String newEmail,
                              @RequestParam String newPassword,
                              @RequestParam boolean active,
                              @RequestParam boolean emailConfirmation,
                              @RequestParam Set<Role> roles,
                              Model model) {

        if (newPassword != null && !newPassword.isEmpty()) user.setPassword(newPassword);

        user.setUsername(newUsername);
        user.setEmail(newEmail);
        user.setActive(active);
        user.setEmailConfirmation(emailConfirmation);
        user.setRoles(roles);

        userService.updateUserToDb(user);

        model.addAttribute("user", user);
        return "admin-edit-user";
    }

    @GetMapping("{user}")
    public String userEditFrom (@PathVariable User user, Model model) {
        model.addAttribute("user", user);

        return "admin-edit-user";
    }

    @GetMapping
    public String users (Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin-show-users";
    }
}
