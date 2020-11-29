package com.app.admin.security;

import com.app.admin.api.model.AccountRequest;
import com.app.admin.api.model.TokenResponse;
import com.app.admin.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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

    @SneakyThrows
    public TokenResponse login(AccountRequest accountForLogin) {
        val account = accountService.findByUsername(accountForLogin.getUsername());

        val authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(accountForLogin.getUsername(), accountForLogin.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        val token = tokenProvider.createToken(accountForLogin.getUsername());
        val authorities = account.getRoles().stream().map( role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

        log.info("User $username has been login.");
        return new TokenResponse().accountId(account.getId()).token(token).username(accountForLogin.getUsername()).authorities(authorities.stream().map(SimpleGrantedAuthority::getAuthority).collect(Collectors.toList()));
    }
}
