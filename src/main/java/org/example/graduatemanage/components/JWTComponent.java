package org.example.graduatemanage.components;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.example.graduatemanage.exception.Code;
import org.example.graduatemanage.exception.XException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

//工具性质的组件 生成token
@Component
@Slf4j
public class JWTComponent {
    @Value("${my.secretkey}")
    private String secretkey;
    private Algorithm algorithm;

    @PostConstruct //在组件初始化之后
    private void init(){
        algorithm = Algorithm.HMAC256(secretkey);
    }

    //进行编码 用户
    public String encode(Map<String,Object> map){
        LocalDateTime time = LocalDateTime.now().plusDays(1);
        return JWT.create()
                .withPayload(map)
                .withIssuedAt(new Date()) //开始时间 过期时间
                .withExpiresAt(Date.from(time.atZone(ZoneId.systemDefault()).toInstant()))
                .sign(algorithm); //基于的算法实现的签名
    }


    //解密算法
    public DecodedJWT decode(String token){
        try {
            return JWT.require(algorithm).build().verify(token);
        } catch(TokenExpiredException | SignatureVerificationException e){ //token过期或者签名被篡改
            if (e instanceof SignatureVerificationException){
                throw XException.builder().code(Code.FORBIDDEN).build();
            }
        }
        throw XException.builder().code(Code.TOKEN_EXPIRED).build();
    }



}
