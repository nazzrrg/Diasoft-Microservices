package com.example.hw8;

import com.example.hw8.models.Role;
import com.example.hw8.models.User;
import com.example.hw8.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        ArrayList<Role> roles = new ArrayList<>();
        if (userRepository.findByUsername("user").isEmpty()) {
            User user = User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("user"))
                    .accountNonLocked(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();
            roles.add(Role.builder().authority("USER").user(user).build());
            user.setAuthorities(roles);
            userRepository.save(user);
        }
        roles.clear();
        if (userRepository.findByUsername("root").isEmpty()) {
            User user = User.builder()
                    .username("root")
                    .password(passwordEncoder.encode("root"))
                    .accountNonLocked(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();
            roles.add(Role.builder().authority("ADMIN").user(user).build());
            roles.add(Role.builder().authority("USER").user(user).build());
            user.setAuthorities(roles);
            userRepository.save(user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User " + username + " was not found!")
        );
    }
}