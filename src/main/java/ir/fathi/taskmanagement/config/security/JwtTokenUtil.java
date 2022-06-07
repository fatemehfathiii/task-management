package ir.fathi.taskmanagement.config.security;

import io.jsonwebtoken.*;
import ir.fathi.taskmanagement.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    private final String jwtKey = "qsc#wdv#54321*efb#@#rgNthn#*#236#UK,opl";

    public String generateAccessToken(User user) {
        var issuer = "example.io";
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtKey)
                .compact();

    }

    public String getUsername(String token) {
        Claims claim = Jwts.parser()
                .setSigningKey(jwtKey)
                .parseClaimsJws(token)
                .getBody();

        return claim.getSubject();
    }

    public Date getExpirationDate(String token){
        Claims claims=Jwts.parser()
                .setSigningKey(jwtKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }


    public boolean validate(String token){

        Logger logger = Logger.getLogger(JwtTokenUtil.class.getName());

        try {
            Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(token);
            return true;
        }catch (SignatureException exception){
            logger.log(Level.WARNING,"token signature is invalid",new SignatureException("SignatureException"));

        }catch (MalformedJwtException exception){
            logger.log(Level.WARNING,"token and key do not match",new MalformedJwtException("MalformedJwtException"));

        }catch (ExpiredJwtException exception){
            logger.log(Level.WARNING,"expired token");

        }catch (UnsupportedJwtException exception){
            logger.log(Level.WARNING,"unsupported algorithm",new UnsupportedJwtException("UnsupportedJwtException"));

        }catch (IllegalArgumentException exception){
            logger.log(Level.WARNING,"jwt token claims string is empty",new IllegalArgumentException("IllegalArgumentException"));
        }catch (Exception exception){
            logger.log(Level.WARNING,"exception occur");
        }
        return false;
    }




}
