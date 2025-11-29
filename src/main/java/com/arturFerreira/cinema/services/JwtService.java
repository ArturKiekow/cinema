package com.arturFerreira.cinema.services;

import com.arturFerreira.cinema.entity.Role;
import com.arturFerreira.cinema.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private final JwtEncoder encoder;

    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(User user){

        Instant now = Instant.now();
        long expire = 3600L;

        String scopes = user.getRoles()
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("cinema-backend")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expire))
                .subject(user.getId().toString())
                .claim("scope", scopes)
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
