package com.app.admin.repository;

import com.app.admin.data.user.Role;
import com.app.admin.data.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role getByName(UserRole name);
}


