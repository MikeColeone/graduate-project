package org.example.graduatemanage.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.graduatemanage.components.JWTComponent;
import org.example.graduatemanage.exception.Code;
import org.example.graduatemanage.exception.XException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final JWTComponent jwtComponent;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(token == null){
            throw XException.builder().code(Code.UNAUTHORIZED).build();
        }

        DecodedJWT decodedJWT = jwtComponent.decode(token);
        String uid = decodedJWT.getClaim("uid").asString();
        String role = decodedJWT.getClaim("role").asString();
        request.setAttribute("uid",uid);
        request.setAttribute("role",role);
        return true;
    }
}
