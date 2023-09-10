package com.edisolt.progress.servingwebcontent.controller;

import com.edisolt.progress.servingwebcontent.config.MailConfig;
import com.edisolt.progress.servingwebcontent.entity.User;
import com.edisolt.progress.servingwebcontent.service.MailSender;
import com.edisolt.progress.servingwebcontent.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;
    @Autowired
    MailSender mailSender;


    /*@PostMapping("/main")
    public String addNewNote (@RequestParam String text, @RequestParam String tag, Model model) {
        noteRepository.save(new Note(text, tag));
        Iterable<Note> notes = noteRepository.findAll();
        model.addAttribute("notes", notes);
        return "main";
    }*/
    /*@GetMapping("/main")
    public String findAllNotes (Model model, @RequestParam(required = false) String tag) {
        Iterable<Note> notes = new MainFilterForNote(noteRepository).filterByTag(tag);
        model.addAttribute("notes", notes);
        return "main";
    }*/

    @PostMapping("/registration")
    public String regNewUser (@RequestParam String username, @RequestParam String password
            , @RequestParam String email, HttpSession session) {

        User user = new User(username, password, email);

        String message;
        if (userService.addNewUserToDb(user)) {
            message = "User \"" + user.getUsername() + "\" successfully registered";
            session.setAttribute("message", message);
            return "activate-account";
        }

        message = "User with name \"" + user.getUsername() + "\" already exists!";
        session.setAttribute("message", message);

        return "redirect:/registration";
    }

    @GetMapping("/registration")
    public String registrationStartPage () {
        return "registration";
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "Your account has been successfully activated.");
        } else {
            model.addAttribute("message", "Account activation failed. Please contact support.");
        }

        return "login";
    }
}
