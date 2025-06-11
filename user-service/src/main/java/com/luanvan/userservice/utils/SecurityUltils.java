package com.luanvan.userservice.utils;

import com.luanvan.userservice.exception.AppException;
import com.luanvan.userservice.exception.ErrorCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class SecurityUltils{
    public static String getCurrentUserRole(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        Object principal = auth.getPrincipal();
        if (principal instanceof Jwt) {
            return ((Jwt) principal).getClaim("scope");
        }
        throw new AppException(ErrorCode.UNAUTHENTICATED);
    }
}
