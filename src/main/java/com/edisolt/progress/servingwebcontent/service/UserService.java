package com.edisolt.progress.servingwebcontent.service;

import com.edisolt.progress.servingwebcontent.entity.Role;
import com.edisolt.progress.servingwebcontent.entity.User;
import com.edisolt.progress.servingwebcontent.entity.Workspace;
import com.edisolt.progress.servingwebcontent.ropository.UserRepository;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MailSender mailSender;

    public User updateUserToDb (User user) {
        userRepository.save(user);

        return user;
    }

    public boolean addNewUserToDb (User user) {//in future throws ex

        if (userRepository.findByUsername(user.getUsername()).isPresent()/*|| email*/) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));//password exists. encoding

        if (user.getRoles() == null) user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setActive(true);

        userRepository.save(user);

        if(!StringUtils.isNullOrEmpty(user.getEmail())) {
            String massage = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Progress Pulse! Please, visit next link: " +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Activation code", massage);
        }

        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) return false;

        user.setActivationCode(null);
        user.setEmailConfirmation(true);

        userRepository.save(user);

        return true;
    }
}
