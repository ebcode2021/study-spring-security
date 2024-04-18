package com.example.business.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter @Setter
public class User {

    private final String username;

    private final String password;

//    private final String code;

}
