package com.example.chapter03.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class InMemoryUserDetailsService implements UserDetailsService {
    private final List<UserDetails> users; //UserDetailsService는 메모리 내 사용자의 목록을 관리

    public InMemoryUserDetailsService(List<UserDetails> users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream()
                .filter(u -> u.getUsername().equals(username)) // 사용자의 목록에서 요청된
                .findFirst() // 일치하는 사용자가 있으면 반환
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
