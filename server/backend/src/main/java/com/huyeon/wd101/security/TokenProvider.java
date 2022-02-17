package com.huyeon.wd101.security;

import com.huyeon.wd101.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {
    private static final String SECRET_KEY = "huyeon123_WD101";


    public String create(UserEntity userEntity) {
        Date expiryDate = Date.from(
                Instant.now().plus(1, ChronoUnit.DAYS)
        );
        return Jwts.builder()
                //header
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                //payload
                .setSubject(userEntity.getId())
                .setIssuer("WD101 app")
                .setIssuedAt(expiryDate)
                .compact();
    }

    public String validateAndGetUserId(String token) {
        /*parseClaimsJws가 Base64로 디코딩 및 파싱
          header와 payload를 setSigningKey로 넘어온 시크릿을 이용해 서명한 후 token의 서명과 비교
          위조되지 않았다면 페이로드(Claims) 리턴, 위조라면 예외를 날림
          userId가 필요하므로 getBody 호출
         */
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); //위에서 할당한 Id가 들어온다
    }
}
