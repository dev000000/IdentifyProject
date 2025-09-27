package com.dev001.identify.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.dev001.identify.enums.Permission.*;

@RequiredArgsConstructor
public enum Role {
    ADMIN(
            Set.of(
                ADMIN_READ,
                ADMIN_CREATE,
                ADMIN_DELETE,
                ADMIN_UPDATE,
                MANAGER_READ,
                MANAGER_CREATE,
                MANAGER_DELETE,
                MANAGER_UPDATE
            )
    ),
    MANAGER(
            Set.of(
                    MANAGER_READ,
                    MANAGER_CREATE,
                    MANAGER_DELETE,
                    MANAGER_UPDATE
            )
    ),
    USER(Collections.emptySet());

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
