package com.luanvan.learningprogress.utils;


import org.springframework.security.oauth2.jwt.Jwt;
import com.luanvan.learningprogress.exception.AppException;
import com.luanvan.learningprogress.exception.ErrorCode;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static Long getId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        Object principal = authentication.getPrincipal();
        if(principal instanceof Jwt){
            return ((Jwt) principal).getClaim("id");
        }
        throw new AppException(ErrorCode.UNAUTHENTICATED);
    }
}
