package co.icesi.taskManager.utils;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

    private static final String SECRET_KEY = "p5rT9$wKm3#sV1q8ZbX4Lk2!uYhEjR6M";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 1 día

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .claim("authorities", authentication.getAuthorities().stream().map(Object::toString).toList())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @SuppressWarnings("unchecked")
    public List<SimpleGrantedAuthority> extractAuthorities(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        List<String> authorities = claims.get("authorities", List.class);
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    public UserDetails getUserDetailsFromToken(String token) {
        String username = extractUsername(token);
        List<SimpleGrantedAuthority> authorities = extractAuthorities(token);
        return new org.springframework.security.core.userdetails.User(username, "", authorities);
    }

    public boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }
}
