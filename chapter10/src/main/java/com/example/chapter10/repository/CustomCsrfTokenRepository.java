package com.example.chapter10.repository;


import com.example.chapter10.entity.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    private final JpaTokenRepository jpaTokenRepository;


    // 애플리케이션이 새 토큰을 생성할때 호출하는 메서드
    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String uuid = UUID.randomUUID().toString();
        return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
    }

    // 특정 클라이언트를 위해 생성된 토큰을 저장
    // 기본 구현에서는 HTTP 세션을 이용하여 구현
    // 클라이언트는 요청에 X-IDENTIFIER 라는 헤더와 함께 보냄
    // 데이터베이스에 해당 ID가 존재하면 새로운 토큰으로 업데이트하고 없다면 DB에 컬럼을 추가
    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        String identifier = request.getHeader("X-IDENTIFIER");
        Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);

        if (existingToken.isPresent()) {
            Token csrfToken = existingToken.get();
            csrfToken.setToken(token.getToken());
        } else {
            Token csrfToken = new Token(identifier, token.getToken());
            jpaTokenRepository.save(csrfToken);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String identifier = request.getHeader("X-IDENTIFIER");

        Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);

        if (existingToken.isPresent()) {
            Token csrfToken = existingToken.get();
            return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", csrfToken.getToken());
        }
        return null;
    }
}
