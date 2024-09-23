package com.twisty.food.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

@Configuration
public class JwtProvider {

    SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstatnt.SECRET_KEY.getBytes());

    public String genratetoken(Authentication auth){
        return Jwts.builder()
                .setSubject(auth.getName())
                .claim("authorities", auth.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstatnt.EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();

    }
          public String getEmailFromJwtToken(String jwt){
              jwt = jwt.substring(7);
              Claims claims = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
              String email = String.valueOf(claims.get("email"));
              return email;

          }

}
