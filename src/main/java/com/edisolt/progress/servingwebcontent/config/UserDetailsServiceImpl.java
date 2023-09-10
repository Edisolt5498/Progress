package com.edisolt.progress.servingwebcontent.config;

import com.edisolt.progress.servingwebcontent.entity.User;
import com.edisolt.progress.servingwebcontent.ropository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) return userOptional.get();
        throw new UsernameNotFoundException("user \"" + username + "\" not found ");

    }
}
