package com.gearvmstore.GearVM.utility;

import com.gearvmstore.GearVM.model.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil implements Serializable {

    private static final long serialVersionUID = -448731673411758558L;
    public static final long TOKEN_VALIDITY = 10 * 60 * 60;
    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateJwtToken(String id, String email) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().claim("id", id).claim("email", email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public Boolean validateJwtToken(String token, Customer customer) {
        String email = getEmailFromToken(token);
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (email.equals(customer.getEmail()) && !isTokenExpired);
    }

    public String getEmailFromToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.get("id").toString();
    }

    public String getIdFromToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.get("email").toString();
    }
}
