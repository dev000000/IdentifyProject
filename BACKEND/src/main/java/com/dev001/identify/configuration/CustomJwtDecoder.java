//package com.dev001.identify.configuration;
//
//import java.text.ParseException;
//import java.util.Objects;
//import javax.crypto.spec.SecretKeySpec;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
//import org.springframework.security.oauth2.jwt.*;
//import org.springframework.stereotype.Component;
//
//import com.dev001.identify.dto.request.IntrospectRequest;
//import com.dev001.identify.exception.AppException;
//import com.dev001.identify.service.AuthenticationService;
//import com.nimbusds.jose.JOSEException;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Component
//public class CustomJwtDecoder implements JwtDecoder {
//
//    private NimbusJwtDecoder nimbusJwtDecoder = null;
//
//    @Autowired
//    private AuthenticationService authenticationService;
//
//    @Value("${jwt.access-token-key}")
//    private String accessTokenKey;
//
//    @Override
//    public Jwt decode(String token) throws JwtException {
//        try {
//            authenticationService.introspect(
//                    IntrospectRequest.builder().token(token).build());
//
//        } catch (AppException ae) {
//            // Đẩy AppException vào cause của JwtException để EntryPoint có thể lấy ra và map 410 GONE
//            log.error("AppException1: {}", ae.getMessage());
//            log.error(ae.getMessage());
//            throw new JwtException(ae.getMessage(), ae);
//        } catch (JOSEException | ParseException e) {
//            throw new JwtException(e.getMessage());
//        }
//        if (Objects.isNull(nimbusJwtDecoder)) {
//            SecretKeySpec secretKeySpec = new SecretKeySpec(accessTokenKey.getBytes(), "HS512");
//            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
//                    .macAlgorithm(MacAlgorithm.HS512)
//                    .build();
//        }
//
//        return nimbusJwtDecoder.decode(token);
//    }
//}
