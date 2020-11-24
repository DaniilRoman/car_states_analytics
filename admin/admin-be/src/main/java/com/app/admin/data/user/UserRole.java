package com.app.admin.data.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRole {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");

    public final String value;
}
