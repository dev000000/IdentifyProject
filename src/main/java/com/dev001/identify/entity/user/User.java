package com.dev001.identify.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "users")
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

}
