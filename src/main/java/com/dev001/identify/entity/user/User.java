package com.dev001.identify.entity.user;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.*;

import com.dev001.identify.entity.role.Role;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String id;

    @Column(name = "user_name")
    String userName;

    @Column(name = "pass_word")
    String passWord;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    LocalDate dob;

    @ManyToMany
    Set<Role> roles;
}
