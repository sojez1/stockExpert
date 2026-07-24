package com.jpstechno.stock_back.securites;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jpstechno.stock_back.modeles.CompteActeurMagasin;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String SECRET_KEY;

    public JwtService(@Value("${jwt.secret-key}") String secretKey) {
        this.SECRET_KEY = secretKey;
    }

    public String generateAccessToken(CompteActeurMagasin connetedUser) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("nom", String.format("%s %s", connetedUser.getActeur().getFirstName(),
                connetedUser.getActeur().getLastName().substring(0, 1)));
        claims.put("email", connetedUser.getUsername());
        claims.put("magasinId", connetedUser.getMagasin().getId());
        return Jwts.builder().header().and()
                .claims(claims)
                .subject(connetedUser.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date())
                .signWith(getKey())
                .compact();

    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public long getMagasinIdFromToken(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMagasinIdFromToken'");
    }

    public long getActeurMagasinIdFromToken(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActeurMagasinIdFromToken'");
    }

}
