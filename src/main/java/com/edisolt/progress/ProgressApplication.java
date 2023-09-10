package com.edisolt.progress;

import com.edisolt.progress.servingwebcontent.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProgressApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProgressApplication.class, args);
    }

}
