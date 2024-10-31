package com.vnua.blog.util;

import com.vnua.blog.entity.User;
import com.vnua.blog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class tokenUtils {

    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.signerKey}")
    private String SECRET_KEY;

    public User getUser(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

//    @Autowired
//    private UserRepository userRepository;
//
//    public  User getUsernameFromToken(String token) {
//        try {
//            // Parse the token to extract claims
//            Claims claims = Jwts.parser()
//                    .setSigningKey(SECRET_KEY)
//                    .parseClaimsJws(token)
//                    .getBody();
//
//            String username = claims.get("username", String.class);
//
//            return userRepository.findById(username).orElse(null);
//        } catch (Exception e) {
//            // Handle token parsing exceptions
//            return null; // Or throw a custom exception
//        }
//    }
}
