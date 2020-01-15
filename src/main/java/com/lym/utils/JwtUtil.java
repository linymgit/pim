package com.lym.utils;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * @Date 2020/1/15
 * @auth linyimin
 * @Desc json web token 工具类
 **/
@Component
public class JwtUtil {

    @Value("${jwtSecret}")
    private String jwtSecret;

    public String genJWT(JWTClaimsSet set) throws JOSEException {
        JWSSigner jwsSigner = new MACSigner(jwtSecret);
        final JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        SignedJWT signedJWT = new SignedJWT(header, set);
        signedJWT.sign(jwsSigner);
        return signedJWT.serialize();
    }

    public JWTClaimsSet parseJWT(String token) throws ParseException, JOSEException {
        SignedJWT parseJWT = SignedJWT.parse(token);
        JWSVerifier jwsVerifier = new MACVerifier(jwtSecret);
        if (parseJWT.verify(jwsVerifier)) {
            return parseJWT.getJWTClaimsSet();
        }
        return null;
    }
}
