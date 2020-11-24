package com.app.admin.security;

import com.app.admin.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder encoder;

    public String encode(String password) {
        return encoder.encode(password);
    }


}
