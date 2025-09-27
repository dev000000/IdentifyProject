package com.dev001.identify.configuration;

import com.dev001.identify.enums.Role;
import com.dev001.identify.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static com.dev001.identify.enums.Permission.*;
import static com.dev001.identify.enums.Role.ADMIN;
import static com.dev001.identify.enums.Role.MANAGER;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    //    private final String[] PUBLIC_URLS = {
    //        "/api/v1/users", "/api/v1/auth/**", "/api/v1/auth/logout", "/api/v1/auth/refresh-token",
    //    };


    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 1. cau hinh quyen truy cap cho tung endpoint
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // cho phep CORS preflight
                                .requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()

                                /*.requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())

                                .requestMatchers(HttpMethod.GET,"/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
                                .requestMatchers(HttpMethod.PUT,"/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
                                .requestMatchers(HttpMethod.POST,"/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
                                .requestMatchers(HttpMethod.DELETE,"/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())*/

                                .requestMatchers("/api/v1/manager/**").hasAnyRole(ADMIN.name(),MANAGER.name())
                                .requestMatchers(HttpMethod.GET,"/api/v1/manager/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                                .requestMatchers(HttpMethod.PUT,"/api/v1/manager/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                                .requestMatchers(HttpMethod.POST,"/api/v1/manager/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                                .requestMatchers(HttpMethod.DELETE,"/api/v1/manager/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())

                                //.requestMatchers(HttpMethod.GET, "/api/v1/users").hasRole(Role.ADMIN.name())
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutUrl("/api/v1/auth/logout").addLogoutHandler(logoutHandler).logoutSuccessHandler(logoutSuccessHandler))
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                        .accessDeniedHandler(new JwtAccessDeniedHandler()));

        // 2. cau hinh resource server de validate JWT
        //        http.oauth2ResourceServer(oauth2 -> oauth2.jwt
        //                (
        //                jwt -> jwt.decoder(jwtDecoder()) // custom decoder
        //                        .jwtAuthenticationConverter(jwtAuthenticationConverter())));
//        http.oauth2ResourceServer(
//                oauth2 -> oauth2.authenticationEntryPoint(new JwtAuthenticationEntryPoint()) //
//                        .accessDeniedHandler(new JwtAccessDeniedHandler())
//                        .jwt(jwt -> jwt.decoder(jwtDecoder()) // inject bean, đừng gọi method
//                                .jwtAuthenticationConverter(jwtAuthenticationConverter())));
        // 3. Xu li loi
        //        http.exceptionHandling(ex -> ex.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
        //                .accessDeniedHandler(new JwtAccessDeniedHandler()));

        return http.build();
    }

    @Bean
    CorsFilter corsFilter() {

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:5173");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

//    @Bean
//    JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(""); // remove prefix ROLE_ in SecurityContext
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//        return jwtAuthenticationConverter;
//    }
}
