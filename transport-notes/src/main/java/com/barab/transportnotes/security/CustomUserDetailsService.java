package com.barab.transportnotes.security;

import com.barab.transportnotes.entity.User;
import com.barab.transportnotes.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User userRepositoryByEmail = userRepository.findByEmail(usernameOrEmail);
        if (userRepositoryByEmail != null) {
            Set<SimpleGrantedAuthority> roles = userRepositoryByEmail.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

            return new org.springframework.security.core.userdetails.User( userRepositoryByEmail.getEmail() ,userRepositoryByEmail.getPassword(),
                    userRepositoryByEmail.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet())
                    );
        } else {
            throw new UsernameNotFoundException("Invalid Email or Password!");
        }
    }
}
