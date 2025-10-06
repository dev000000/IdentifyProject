package com.dev001.identify.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInit {

    private final PasswordEncoder passwordEncoder;

    //    @Bean
    //    @ConditionalOnProperty(
    //            prefix = "spring.datasource",
    //            name = "driver-class-name",
    //            havingValue = "com.mysql.cj.jdbc.Driver"
    //    )
    //    public ApplicationRunner applicationRunner(UserRepository userRepository) {
    //        return args -> {
    //            if (userRepository.findByUserName("admin").isEmpty()) {
    //                //                Set<String> roles = Set.of(Role.ADMIN.name());
    //                User user = User.builder()
    //                        .userName("admin")
    //                        .passWord(passwordEncoder.encode("admin"))
    //                        //                        .roles(roles)
    //                        .role(Role.ADMIN)
    //                        .build();
    //                userRepository.save(user);
    //                log.info("Admin user has been created with default password : admin , please change it");
    //            }
    //        };
    //    }
}
