package kr.limc.limcblog.Config.Security;

import static kr.limc.limcblog.Config.Security.SecurityConstants.ISSUER;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.limc.limcblog.Controller.Comm.Dto.UserDto;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenManager {
    @Value("${JWT_SECRET_KEY}")
    private String JWT_SECRET_KEY;

    public String getToken(UserDto userDto, long tokenType) {
        final String loginId = userDto.getUserId();
        return generateToken(loginId, tokenType);
    }

    private String generateToken(String subject, long dueDateSeconds) {
        final Claims claims = Jwts.claims().setSubject(subject);
        final long currentTimeMillis = System.currentTimeMillis();

        return Jwts.builder()
            .setClaims(claims)
            .setIssuer(ISSUER)
            .setIssuedAt(new Date(currentTimeMillis))
            .setExpiration(new Date(currentTimeMillis + dueDateSeconds * 10000 * 400))
            .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
            .compact();
    }
    
    public boolean validateToken(String authToken) {
        try {
            getAllClaimsFromToken(authToken);
            return true;
        } catch (ExpiredJwtException exception) {
            exception.printStackTrace();
            return false;
        } catch (JwtException exception) {
            exception.printStackTrace();
            return false;
        } catch (NullPointerException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public String getUserIdFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.getSubject();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public boolean isTokenExpired(String token) {

        final Date expirationDateFromToken = getExpirationDateFromToken(token);
        return expirationDateFromToken.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {

        final Claims claims = getAllClaimsFromToken(token);
        return claims.getExpiration();
    }


}
