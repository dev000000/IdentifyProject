package com.dev001.identify.entity.role;

import java.util.Set;

import jakarta.persistence.*;

import com.dev001.identify.entity.permission.Permission;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "role")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {

    @Id
    String name;

    String description;

    @ManyToMany
    Set<Permission> permissions;
}
