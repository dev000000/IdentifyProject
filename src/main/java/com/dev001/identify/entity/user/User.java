package com.dev001.identify.entity.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.util.Set;

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
    Set<String> roles;

}
