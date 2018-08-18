package com.cloverat.jjwt;

import io.jsonwebtoken.Claims;

/**
 * Jjwt使用的demo
 *
 * @author cloverat
 * @date 18-8-18
 */
public class JjwtUtilDemo {

    public static void main(String[] args) throws Exception {
        JjwtUtil util = new JjwtUtil();
        String ab = util.createJWT("jwt", "{id:100,name:xiaohong}", 60000);
        System.out.println(ab);

        String jwt =
                "eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJEU1NGQVdEV0FEQVMuLi4iLCJzdWIiOiJ7aWQ6MTAwLG5hbWU6eGlhb2hvbmd9IiwidXNlcl9uYW1lIjoiYWRtaW4iLCJuaWNrX25hbWUiOiJEQVNEQTEyMSIsImV4cCI6MTUzNDU4MjE4NiwiaWF0IjoxNTM0NTgyMTI2LCJqdGkiOiJqd3QifQ.N80KkdVyCMm35l0Ern-CBi3LdNs0seHd7KVQoZGZ0qc";
        //注意：如果jwt已经过期了，这里会抛出jwt过期异常。
        Claims c = util.parseJWT(jwt);
        //jwt
        System.out.println(c.getId());
        //Mon Feb 05 20:50:49 CST 2018
        System.out.println(c.getIssuedAt());
        //{id:100,name:xiaohong}
        System.out.println(c.getSubject());
        // null
        System.out.println(c.getIssuer());
        // DSSFAWDWADAS...
        System.out.println(c.get("uid", String.class));
    }
}

