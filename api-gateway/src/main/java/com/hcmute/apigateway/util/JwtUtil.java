package com.hcmute.apigateway.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {


    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";



    public DecodedJWT decode(String token) {
        return JWT.require(Algorithm.HMAC256("mysecretkey"))
                .build()
                .verify(token);
    }


}