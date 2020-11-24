package com.app.admin.controller;


import com.app.admin.data.user.Account;
import com.app.admin.data.user.Role;
import com.app.admin.repository.AccountRepository;
import com.app.admin.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/accounts")
    public List<Account> all() {
        return accountRepository.findAll();
    }

    @GetMapping("/role")
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }
}
