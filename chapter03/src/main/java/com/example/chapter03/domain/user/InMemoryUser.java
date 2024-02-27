package com.example.chapter03.domain.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class InMemoryUser implements UserDetails {
    
    private final String username;
    private final String password;
    private final String authority; // 예제를 간단하게 하기 위해 한 사용자에게 하나의 권한만 적용한다.

    public InMemoryUser(String username, String password, String authority) {
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> authority); // 인스턴스를 만들 때 지정한 이름의 GrantedAuthority 객체만 포함하는 목록 반환
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
