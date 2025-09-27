package com.dev001.identify;

import com.dev001.identify.dto.request.RegisterRequest;
import com.dev001.identify.dto.request.UserCreationRequest;
import com.dev001.identify.enums.Role;
import com.dev001.identify.service.AuthenticationService;
import com.dev001.identify.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

import static com.dev001.identify.enums.Role.*;

@Slf4j
@SpringBootApplication
public class IdentifyApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdentifyApplication.class, args);
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
}
