package com.project.conforzone.auth;

import com.project.conforzone.exception.TokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${SECRET_KEY}")
    private String SECRET_KEY; //SECRET KEY del token

    /**
     * Obtiene token
     * @param extraClaims
     * @param user Contiene el correo del usuario
     * @return Token
     */
    public String getTokenFromService(Map<String, Object> extraClaims, UserDetails user){
        return getToken(extraClaims, user);
    }

    /**
     * Convierte los diferentes datos en token
     * @param extraClaims Diferentes datos que contiene el token aparte del asunto (correo)
     * @param user Contiene el correo del usuario
     * @return Token
     */
    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*2))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Decodifica una clave secreta en Base64 y la convierte en una clave HMAC-SHA.
     * @return una instancia de Key para HMAC-SHA
     */
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Obtiene username (en este caso correo) a partir de un token
     * @param token Token del usuario
     * @return username
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Comprueba que el token sea válido
     * @param token Token
     * @param userDetails username del usuario
     * @return true si es valido, false si no
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    /**
     * Obtiene los detalles del token
     * @param token cadena token
     * @return Datos de token
     */
    private Claims getAllClaims(String token)
    {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException ex){
            throw new TokenExpiredException(ex.getMessage() + "El token ha caducado");
        }
    }

    /**
     * Obtiene el detalle del token que se necesite
     * @param token Token
     * @param claimsResolver Detalle que se quiere obtener
     * @return Detalle
     */
    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Obtiene expiracion del token
     * @param token Token
     * @return Expiracion
     */
    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * Comprueba que el token no esta expirado
     * @param token token
     * @return true si no esta expirado, false si lo esta
     */
    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }
}
