package com.example.chapter10.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String identifier; // 클라이언트 식별자
    private String token;

    public Token(String identifier, String token) {
        this.identifier = identifier;
        this.token = token;
    }
}
