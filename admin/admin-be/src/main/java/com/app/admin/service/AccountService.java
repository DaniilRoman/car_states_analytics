package com.app.admin.service;

import com.app.admin.api.model.AccountRequest;
import com.app.admin.data.user.Account;
import com.app.admin.data.user.Role;
import com.app.admin.data.user.UserRole;
import com.app.admin.repository.AccountRepository;
import com.app.admin.repository.RoleRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findById(UUID id) throws NotFoundException {
        return accountRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id: " + id + " not found"));
    }

    public Account findByUsername(String username) throws NotFoundException {
        return accountRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User with username: " + username + " not found"));
    }

    public void delete(UUID id) {
        accountRepository.deleteById(id);
    }

    public Account update(Account account) {
        return accountRepository.save(account);
    }

    public Account create(AccountRequest request, UserRole role) {
        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setPassword(request.getPassword());
        accountRepository.findByUsername(account.getUsername())
                .ifPresent((it) -> { throw new IllegalArgumentException("User " + account.getUsername() + " already exists!"); });


        Role userRole = roleRepository.getByName(role);
        account.setRoles(Collections.singleton(userRole));
        return accountRepository.save(account);
    }

}
