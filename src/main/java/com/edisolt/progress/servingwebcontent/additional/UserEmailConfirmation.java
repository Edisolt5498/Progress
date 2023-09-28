package com.edisolt.progress.servingwebcontent.additional;

import com.edisolt.progress.servingwebcontent.entity.User;
import com.edisolt.progress.servingwebcontent.service.MailSender;
import com.mysql.cj.util.StringUtils;

public class UserEmailConfirmation {
    public static void sendMessageToUserWithConfirmLink (User user, MailSender mailSender) {
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
    }
}
