package com.dev001.identify.entity.invalidatedToken;

import com.dev001.identify.entity.role.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

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

}
