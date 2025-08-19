package com.luanvan.kehoachhoctapservice.exception;


import com.luanvan.kehoachhoctapservice.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<Object>> exception(Exception e){
        return ResponseEntity.badRequest().body(ApiResponse.builder()
                .code(1001)
                .message(e.getMessage())
                .build());
    }
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<Object>> handlingAppException(AppException ex){
        ErrorCode errorCode = ex.getErrorCode();
        // Ưu tiên lấy message từ exception (custom message), nếu không có thì lấy từ errorCode
        String message = ex.getMessage() != null && !ex.getMessage().equals(errorCode.getMessage())
                        ? ex.getMessage()
                        : errorCode.getMessage();
        return ResponseEntity.badRequest().body(ApiResponse.builder()
                .code(errorCode.getCode())
                .message(message)
                .build());
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<Object>> handlingValidation(MethodArgumentNotValidException ex){
        String enumKey = Objects.requireNonNull(ex.getFieldError()).getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
        return ResponseEntity.badRequest().body(ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build());
    }
}
