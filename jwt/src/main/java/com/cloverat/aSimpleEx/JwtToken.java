package com.cloverat.aSimpleEx;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT Token
 *
 * @author cloverat
 * @date 18-8-18
 */
public class JwtToken {

    /**
     * 公用秘钥-保存在服务端，客户端是不会知道密钥的，以放被攻击
     */
    private static String SECRET = "Cloverat";

    /**
     * 生成Token
     *
     * @return Token
     * @throws Exception 异常
     */
    public static String createToken() throws Exception {

        // 签发时间
        Date iatDate = new Date();

        // 过期时间-1分钟过期
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 1);
        Date expiresDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create()
                /*
                 * header
                 */
                .withHeader(map)
                /*
                 * payload
                 */
                .withClaim("name", "Cloverat")
                .withClaim("age", "18")
                .withClaim("org", "CT")
                /*
                 * 设置过期时间-过期时间要大于签发时间
                 */
                .withExpiresAt(expiresDate)
                /*
                 * 设置签发时间
                 */
                .withIssuedAt(iatDate)
                /*
                 * 加密
                 */
                .sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 解密token
     *
     * @param token token
     * @return 解密结果
     * @throws Exception 异常
     */
    public static Map<String, Claim> verifyToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("登录凭证已过期，请重新登录");
        }

        return jwt.getClaims();
    }
}
