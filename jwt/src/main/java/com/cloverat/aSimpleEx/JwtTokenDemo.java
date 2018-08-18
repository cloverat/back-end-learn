package com.cloverat.aSimpleEx;

import com.auth0.jwt.interfaces.Claim;

import java.util.Map;

/**
 * Jwt使用的demo
 *
 * @author cloverat
 * @date 18-8-18
 */
public class JwtTokenDemo {

    public static void main(String[] args) throws Exception {

        String token = JwtToken.createToken();
        System.out.println("Token:" + token);

        Map<String, Claim> claims = JwtToken.verifyToken(token);
        System.out.println(claims.get("name").asString());
        System.out.println(claims.get("age").asString());
        System.out.println(claims.get("org") == null ? null : claims.get("org").asString());

        // 使用过期后的Token进行校验
        String tokenExpire = "xxx";
        Map<String, Claim> claimsExpire = JwtToken.verifyToken(tokenExpire);
        System.out.println(claimsExpire);
    }
}
