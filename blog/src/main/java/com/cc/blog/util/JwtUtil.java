package com.cc.blog.util;


import com.alibaba.fastjson.JSONObject;
import com.cc.blog.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * JSON WEB TOKEN
 */
public class JwtUtil {

    public static final String KEY_TOKEN = "token";
    public static final String KEY_TIMESTAMP = "timestamp";
    public static final String SECRET = "dongxicc";

    /**
     * 加密token
     *
     * @return
     */
    public static String createJWT( User user) {
        //生成签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        SecretKey key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setId(user.getId().toString())
                .claim(KEY_TOKEN, JSONObject.toJSONString(user))
                .claim(KEY_TIMESTAMP,new Date().getTime())
                .signWith(signatureAlgorithm, key);
        //添加Token过期时间
        //生成JWT
        return builder.compact();
    }

    /**
     * 解密
     *
     * @param token
     * @param secrete
     * @return
     */
    public static String parseJWT(String token, String secrete) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(secrete))
                    .parseClaimsJws(token).getBody();
            return claims.get(KEY_TOKEN).toString();
        } catch (Exception ex) {
            return null;
        }
    }

}
