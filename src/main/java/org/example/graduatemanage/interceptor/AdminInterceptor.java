package org.example.graduatemanage.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.graduatemanage.dox.User;
import org.example.graduatemanage.exception.Code;
import org.example.graduatemanage.exception.XException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getAttribute("admin").equals(User.ADMIN_ROLE)) {
            return true;
        }
        throw XException.builder().code(Code.FORBIDDEN).build();
    }
}
