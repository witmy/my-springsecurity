package com.codermy.myspringsecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author codermy
 * @createTime 2020/7/4
 */
public class JwtTest {
    public static void main(String[] args) {
        JwtBuilder builder = Jwts.builder().setId("111")
                .setSubject("codermy")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512,"codermy");//设置签名;
        String jwt = builder.compact();
        System.out.println(jwt);

        //解析
        Claims claims= Jwts.parser().setSigningKey("codermy").parseClaimsJws(jwt).getBody();
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
    }
}
