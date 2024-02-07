package com.senac.listou.services;

import com.senac.listou.entidades.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${jwt.secret}")
    private String CHAVE_SECRETA;

    private static final long TEMPO_EXPIRACAO = 86400000;

    public String generateAccessToken (Usuario usuario) {

        List<String> listaCargos = List.of(usuario.getCargo().getNome());

        return "Bearer " + Jwts.builder()
                .issuer("listou")
                .claim(Claims.ID, usuario.getIdUsuario())
                .claim("cargos", listaCargos)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + TEMPO_EXPIRACAO))
                .signWith(SignatureAlgorithm.HS256, CHAVE_SECRETA)
                .compact();
    }

    public UsernamePasswordAuthenticationToken isValid(String token) {
        if (token == null) {
            return null;
        }

        Claims body = Jwts.parser()
                .setSigningKey(CHAVE_SECRETA)
                .build().parseSignedClaims(token)
                .getPayload();

        Integer idUsuario = body.get(Claims.ID, Integer.class);

        if (idUsuario != null) {
            List<String> cargos = body.get("cargos", List.class);

            List<SimpleGrantedAuthority> cargosGrantedAuthority = cargos.stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            return new UsernamePasswordAuthenticationToken(idUsuario, null, cargosGrantedAuthority);
        }
        return null;
    }
}
