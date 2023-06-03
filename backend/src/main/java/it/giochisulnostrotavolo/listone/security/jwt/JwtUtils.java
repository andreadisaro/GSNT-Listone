package it.giochisulnostrotavolo.listone.security.jwt;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import it.giochisulnostrotavolo.listone.security.UserDetailsImpl;

@Component
public class JwtUtils {
	private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationMs}")
	private long jwtExpirationMs;

	public String generateJwtToken(Authentication authentication, Boolean session) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 1);
		if(session) {
		return Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				//.claim("custom_payload_field_name", "custom_payload_field_value")
				.setIssuedAt(new Date())
				.setExpiration(c.getTime())
				.setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
				.compact();
		}else {
			return Jwts.builder()
					.setSubject(userPrincipal.getUsername())
					//.claim("custom_payload_field_name", "custom_payload_field_value")
					.setIssuedAt(new Date())
					.setExpiration(c.getTime())
					//TODO ripristinare qui per avere un token che scade
//					.setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
					.signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
					.compact();
		}
	}

	public String getUserNameFromJwtToken(String token) {
		return getClaims(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			getClaims(authToken);
			return true;
		} catch (SignatureException e) {
			LOG.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			LOG.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			LOG.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			LOG.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			LOG.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
	
	public Jws<Claims> getClaims(String token) {
		return Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token);
	}
}
