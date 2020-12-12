package com.app.admin.data.user;

import com.app.admin.data.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

/**
 * Represents application user.
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

    private String username;
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role",
            joinColumns = {
                    @JoinColumn(name = "account_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
            })
    @JsonIgnoreProperties("users")
    private Set<Role> roles = Collections.emptySet();

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }
}
