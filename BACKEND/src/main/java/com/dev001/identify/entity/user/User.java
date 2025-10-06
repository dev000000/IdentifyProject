package com.dev001.identify.entity.user;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dev001.identify.entity.token.Token;
import com.dev001.identify.enums.Role;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String id;

    @Column(name = "user_name", unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String username;

    @Column(name = "pass_word")
    String password;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    LocalDate dob;

    //    @ManyToMany
    //    Set<Role> roles;
    @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany(mappedBy = "user")
    List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //        return roles.stream().flatMap(role -> role.getAuthorities().stream()).collect(Collectors.toSet());
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }
}
