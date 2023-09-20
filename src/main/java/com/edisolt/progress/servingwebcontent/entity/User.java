package com.edisolt.progress.servingwebcontent.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private String activationCode;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable( name = "user_role", joinColumns = @JoinColumn(name = "user_id") )
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "user_workspace"
            , joinColumns = @JoinColumn(name = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "workspace_id")
    )
    private List<Workspace> workspaces;
    private boolean active;
    private boolean emailConfirmation;

    public void addWorkspace (Workspace workspace) {
        if (workspaces == null) workspaces = new ArrayList<>();
        workspaces.add(workspace);
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.email = email;
        this.password = password;

        this.addWorkspace(new Workspace("start workspace"));
    }

    //implements UserDetails//
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
    //implements UserDetails//
}

