package com.lym.utils;

import com.lym.entity.Result;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2020/1/15
 * @auth linyimin
 * @Desc json web token 工具类
 **/
@Component
public class JwtUtil {

    public static final String EXPIRE_TIME = "jwt_exp";

    public static final String RESULT = "jwt_result_code";

    public static final String DATA = "jwt_data";

    public static final String ID_KEY = "jwt_id";
    public static final String ROLE_KEY = "jwt_role";

    public static final int JWT_SUCCESS = 0;
    public static final int JWT_PARSE_FAILED = 1;
    public static final int JWT_EXPIRED = 2;

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

    public String createTokenHS256(Map<String, Object> payLoadMap) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        Payload payload = new Payload(new JSONObject(payLoadMap));
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            JWSSigner jwsSigner = new MACSigner(jwtSecret);
            jwsObject.sign(jwsSigner);
            return jwsObject.serialize();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Object> parseTokenHS256(String token) throws ParseException, JOSEException {
        JWSObject jwsObject = JWSObject.parse(token);
        JWSVerifier jwsVerifier = new MACVerifier(jwtSecret);
        return verify(jwsObject, jwsVerifier);
    }

    public String genToken(Long userId) {
        HashMap<String, Object> tokenMap = new HashMap<>();
        tokenMap.put(JwtUtil.ID_KEY, userId);
        tokenMap.put(JwtUtil.EXPIRE_TIME, LocalDateTime.now().plusDays(1).toString());
        return createTokenHS256(tokenMap);
    }

    public String genTokenWithAdminRole(Long userId) {
        HashMap<String, Object> tokenMap = new HashMap<>();
        tokenMap.put(JwtUtil.ID_KEY, userId);
        tokenMap.put(JwtUtil.ROLE_KEY, 1);
        tokenMap.put(JwtUtil.EXPIRE_TIME, LocalDateTime.now().plusDays(1).toString());
        return createTokenHS256(tokenMap);
    }

    public Result getResult(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            JWSVerifier jwsVerifier = new MACVerifier(jwtSecret);
            Map<String, Object> verify = verify(jwsObject, jwsVerifier);
            int i = (int) verify.get(RESULT);
            if (i == JWT_EXPIRED) {
                return new Result(ResultUtil.TOKEN_EXPIRED, "token过期");
            }
            if (i == JWT_PARSE_FAILED) {
                return new Result(ResultUtil.INVALIDE_TOKEN, "无效的token");
            }
            return ResultUtil.getSuccess(verify.get(DATA));
        } catch (ParseException | JOSEException e) {
            e.printStackTrace();
        }
        return ResultUtil.getError();
    }

    private Map<String, Object> verify(JWSObject jwsObject, JWSVerifier jwsVerifier) throws JOSEException {
        Map<String, Object> resultMap = new HashMap<>();
        Payload payload = jwsObject.getPayload();
        if (jwsObject.verify(jwsVerifier)) {
            resultMap.put(RESULT, JWT_SUCCESS);
            JSONObject jsonObject = payload.toJSONObject();
            resultMap.put(DATA, jsonObject);
            if (jsonObject.containsKey(EXPIRE_TIME)) {
                LocalDateTime o = LocalDateTime.parse((CharSequence) jsonObject.get(EXPIRE_TIME));
                if (LocalDateTime.now().isAfter(o)) {
                    resultMap.put(RESULT, JWT_EXPIRED);
                }
            }
        } else {
            resultMap.put(RESULT, JWT_PARSE_FAILED);
        }
        return resultMap;
    }
}
