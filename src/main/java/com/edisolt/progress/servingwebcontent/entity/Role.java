package com.edisolt.progress.servingwebcontent.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN("Administrator"),
    USER("User");
    // Add more roles here...

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getAuthority() {
        return name();
    }

    public String getDisplayName() {
        return displayName;
    }
    }

