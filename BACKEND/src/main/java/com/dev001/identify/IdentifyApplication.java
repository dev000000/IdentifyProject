package com.dev001.identify;

import static com.dev001.identify.enums.Role.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class IdentifyApplication {
    @Value("${server.port}")
    public  String port;

    @Value("${application.security.cookie.secure}")
    private  boolean secure;

    @Value("${application.security.cookie.same-site}")
    private  String sameSite;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private  long refreshExpiration; // in milliseconds

    @Value("${spring.datasource.url}")
    private  String url;

    public static void main(String[] args) {
        SpringApplication.run(IdentifyApplication.class, args);

    }
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            log.error("Server started on port: " + port);
            log.error("Secure: " + secure);
            log.error("SameSite: " + sameSite);
            log.error("Refresh Expiration: " + refreshExpiration);
            log.error("Database URL: " + url);
        };
    }
    }


    // this section for test token
    //    @Bean
    //    public CommandLineRunner commandLineRunner(AuthenticationService authenticationService) {
    //        return args -> {
    //            var admin = RegisterRequest.builder()
    //                    .firstName("Admin")
    //                    .lastName("Admin")
    //                    .dob(LocalDate.parse("2005-05-20"))
    //                    .username("adminadminadmin")
    //                    .password("adminadminadmin")
    //                    .role(ADMIN)
    //                    .build();
    //            log.error("Admin token:" + authenticationService.register(admin).getAccessToken());
    //            var manager = RegisterRequest.builder()
    //                    .firstName("Manager")
    //                    .lastName("Manager")
    //                    .dob(LocalDate.parse("2005-05-20"))
    //                    .username("managermanager")
    //                    .password("managermanager")
    //                    .role(MANAGER)
    //                    .build();
    //            log.error("Manager token:" + authenticationService.register(manager).getAccessToken());
    //            var user = RegisterRequest.builder()
    //                    .firstName("User")
    //                    .lastName("User")
    //                    .dob(LocalDate.parse("2005-05-20"))
    //                    .username("useruseruser")
    //                    .password("useruseruser")
    //                    .role(USER)
    //                    .build();
    //            log.error("User token:" + authenticationService.register(user).getAccessToken());
    //        };
    //    }

