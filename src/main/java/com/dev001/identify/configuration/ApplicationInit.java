package com.dev001.identify.configuration;

import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dev001.identify.entity.user.User;
import com.dev001.identify.enums.Role;
import com.dev001.identify.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInit {

    private final PasswordEncoder bcryptPasswordEncoder;

    @Bean
    @ConditionalOnProperty(prefix = "spring", name = "driverClassName", havingValue = "com.mysql.cj.jdbc.Driver")
    public ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUserName("admin").isEmpty()) {
//                Set<String> roles = Set.of(Role.ADMIN.name());
                User user = User.builder()
                        .userName("admin")
                        .passWord(bcryptPasswordEncoder.encode("admin"))
                        //                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.info("Admin user has been created with default password : admin , please change it");
            }
        };
    }
}
