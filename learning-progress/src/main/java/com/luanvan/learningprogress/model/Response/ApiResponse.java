package com.luanvan.learningprogress.model.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;
}