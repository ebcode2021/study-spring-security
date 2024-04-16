package com.example.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Otp {
    @Id
    private String username;

    private String code;

    public void setCode(String code) {
        this.code = code;
    }
}
