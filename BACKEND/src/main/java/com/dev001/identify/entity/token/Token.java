package com.dev001.identify.entity.token;

import com.dev001.identify.entity.user.User;
import com.dev001.identify.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@Table(name = "token")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Token {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    String id;

    String token;

    @Enumerated(EnumType.STRING)
    TokenType tokenType;

    Date expiryTime;
    boolean revoked;

    boolean isAccessToken;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

}
