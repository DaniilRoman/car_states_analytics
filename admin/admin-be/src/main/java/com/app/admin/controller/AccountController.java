package com.app.admin.controller;


import com.app.admin.api.AccountApi;
import com.app.admin.api.model.AccountRequest;
import com.app.admin.api.model.AccountResponse;
import com.app.admin.api.model.TokenResponse;
import com.app.admin.data.user.Account;
import com.app.admin.data.user.UserRole;
import com.app.admin.security.AuthService;
import com.app.admin.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountApi {

    private final AccountService accountService;
    private final AuthService authService;

    @Override
    public ResponseEntity<TokenResponse> login(AccountRequest accountRequest) {
        return ResponseEntity.ok().body(authService.login(accountRequest));
    }

    @Override
    public ResponseEntity<AccountResponse> singUp(AccountRequest accountRequest) {
        return createAccount(accountRequest, UserRole.ROLE_USER);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccountResponse> createAdmin(AccountRequest accountRequest) {
        return createAccount(accountRequest, UserRole.ROLE_ADMIN);
    }

    private ResponseEntity<AccountResponse> createAccount(AccountRequest request, UserRole role) {
        request.setPassword(authService.encode(request.getPassword()));
        Account account = accountService.create(request, role);
        return ResponseEntity.ok().body(new AccountResponse().username(account.getUsername()).id(account.getId()));

    }

    @SneakyThrows
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccountResponse> getAccount(UUID accountId) {
        Account account = accountService.findById(accountId);
        return ResponseEntity.ok().body(new AccountResponse().username(account.getUsername()).id(account.getId()));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AccountResponse>> getAccounts() {
        return ResponseEntity.ok().body(accountService.findAll().stream()
                .map(it -> new AccountResponse().username(it.getUsername()).id(it.getId()))
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Void> deleteAccount(UUID accountId) {
        accountService.delete(accountId);
        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @Override
    public ResponseEntity<AccountResponse> updateAccount(UUID accountId, AccountRequest accountRequest) {
        Account account = accountService.findById(accountId);
        account.setUsername(accountRequest.getUsername());
        account.setPassword(accountRequest.getPassword());
        account = accountService.update(account);
        return ResponseEntity.ok().body(new AccountResponse().username(account.getUsername()).id(account.getId()));
    }
}
