package com.example.chapter06.service;

import com.example.chapter06.domain.user.model.User;
import com.example.chapter06.domain.user.repository.UserRepository;
import com.example.chapter06.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                    .findUserByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Problem during authentication!"));
        return new CustomUserDetails(user);
    }
}
