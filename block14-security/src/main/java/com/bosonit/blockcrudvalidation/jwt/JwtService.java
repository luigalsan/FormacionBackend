package com.bosonit.blockcrudvalidation.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service

public class JwtService {

    private static final String SECRET_KEY="3aR9vY2ePzXJkA1S6I8wG7oH4fDqB2pU9W5nK3mY7cX3uK1aS\n";

    public String getToken(UserDetails person){
        return myToken(new HashMap<>(), person);
    }

    private String myToken(HashMap<String, Object> extractClaims, UserDetails person) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(person.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
                .signWith(getKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyByte= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String getUserNameFromToken(String token) {
        return getClaims(token,Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Date getExpiration(String token){
        return getClaims(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }

    public <T> T getClaims(String token, Function<Claims,T> claimsTFunction){
        final Claims claims = getAllClaims(token);
        return claimsTFunction.apply(claims);
    }
}
