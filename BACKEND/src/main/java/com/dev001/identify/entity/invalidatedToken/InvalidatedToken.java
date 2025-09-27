package com.dev001.identify.entity.invalidatedToken;

import java.util.Date;

import com.dev001.identify.enums.TokenType;
import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "invalidated_token")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvalidatedToken {

    @Id
    String id;

    Date expiryTime;

    @Enumerated(EnumType.STRING)
    TokenType tokenType;
}
