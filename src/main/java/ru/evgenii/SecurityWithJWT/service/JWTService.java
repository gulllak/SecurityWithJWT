package ru.evgenii.SecurityWithJWT.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.evgenii.SecurityWithJWT.model.User;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTService {

    @Value("${jwt.secret_key}")
    private String SECRET_KEY;

    @Value("${jwt.expiration-time}")
    private Long EXPIRATION_TIME;

    public String getUsernameFromJWT(String jwtToken) {
        return getClaimFromJWT(jwtToken, Claims::getSubject);
    }

    public <T> T getClaimFromJWT(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromJWT(jwtToken);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        return generateToken(claims, user);
    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        final String username = getUsernameFromJWT(jwtToken);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return getClaimFromJWT(jwtToken, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> extraClaims,
                                UserDetails userDetails) {
        return Jwts.builder()
                .claims()
                .add(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRATION_TIME))
                .and()
                .signWith(getSecretSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    private Claims getAllClaimsFromJWT(String jwtToken) {
        return Jwts.parser()
                .verifyWith(getSecretSigningKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    private SecretKey getSecretSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
