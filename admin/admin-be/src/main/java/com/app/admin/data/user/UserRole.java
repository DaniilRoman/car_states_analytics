package com.app.admin.data.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRole {
    ROLE_USER,
    ROLE_ADMIN;

    public static String USER = "USER";
    public static String ADMIN = "ADMIN";
}
