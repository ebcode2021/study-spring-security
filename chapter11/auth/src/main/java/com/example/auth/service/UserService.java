package com.example.auth.service;

import com.example.auth.model.Otp;
import com.example.auth.model.User;
import com.example.auth.repository.OtpRepository;
import com.example.auth.repository.UserRepository;
import com.example.auth.util.GenerateCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final OtpRepository otpRepository;

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void auth(User user) {
        User userFromDB = userRepository.findUserByUsername(user.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Bad Credentials!"));

        if (passwordEncoder.matches(user.getPassword(), userFromDB.getPassword())) {
            renewOtp(userFromDB);
        } else {
            throw new BadCredentialsException("Bad Credentials!");
        }
    }

    private void renewOtp(User user) {
        // OTP를 위한 임의의 수 생성
        String code = GenerateCodeUtil.generateCode();

        // 사용자 이름으로 OTP 검색
        Optional<Otp> otpFromDB = otpRepository.findOtpByUsername(user.getUsername());

        if (otpFromDB.isPresent()) {
            Otp otp = otpFromDB.get();
            otp.setCode(code);
        } else {
            Otp otp = new Otp(user.getUsername(), code);
            otpRepository.save(otp);
        }
    }

    // OTP 검증
    public boolean check(Otp otpToValidate) {
        Optional<Otp> userOtp = otpRepository.findOtpByUsername(otpToValidate.getUsername());

        if (userOtp.isPresent()) {
            Otp otp = userOtp.get();

            if (otpToValidate.getCode().equals(otp.getCode())) {
                return true;
            }
        }
        return false;
    }
}
