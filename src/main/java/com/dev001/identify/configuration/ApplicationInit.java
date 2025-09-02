package com.dev001.identify.configuration;

import com.dev001.identify.entity.user.User;
import com.dev001.identify.enums.Role;
import com.dev001.identify.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@Slf4j
public class ApplicationInit {
    @Autowired
    private PasswordEncoder BcryptPasswordEncoder;

    @Bean
    @ConditionalOnProperty(prefix = "spring", name = "driverClassName", havingValue = "com.mysql.cj.jdbc.Driver")
    public ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if(userRepository.findByUserName("admin").isEmpty()) {
                Set<String> roles = Set.of(Role.ADMIN.name());
                User user = User.builder()
                        .userName("admin")
                        .passWord(BcryptPasswordEncoder.encode("admin"))
//                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.info("Admin user has been created with default password : admin , please change it");
            }
        };


    }
}
