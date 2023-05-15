package com.nttdata.service;

import com.nttdata.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class TokenService {

    private static final String SECRET_KEY = "hkjhljh297384923b23n4bn343434n34534nj5435n435j345nj435nj859484jr9451jrj88r5j8j88r85j85j8r8j85rjrj85j8r5898jr5j8885j88j43o5n349534";

    public String generateToken(User user) {
        LocalDateTime currentTime = LocalDateTime.now();

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("userId", user.getId())
                .claim("name", user.getName())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Timestamp.valueOf(currentTime.plusMinutes(30)))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}