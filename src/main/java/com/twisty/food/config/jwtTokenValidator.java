package com.twisty.food.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

@Configuration
public class jwtTokenValidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(JwtConstatnt.JWT_VALIDATOR);
        if(jwt != null) {
            jwt.substring(7);
            try {
                SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstatnt.SECRET_KEY.getBytes());
                Claims body = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
                String email = String.valueOf(body.get("email"));
                String authoritys = String.valueOf(body.get("authorities"));

                List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authoritys);
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auth);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("User authenticated successfully: " + email);


            } catch (Exception e) {
                response.sendError(401, e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}
