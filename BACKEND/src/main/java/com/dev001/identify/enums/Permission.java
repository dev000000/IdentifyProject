package com.dev001.identify.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:write"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_CREATE("admin:create"),

    MANAGER_READ("manager:read"),
    MANAGER_UPDATE("manager:write"),
    MANAGER_DELETE("manager:delete"),
    MANAGER_CREATE("manager:create");


    @Getter
    private final String permission;
}
