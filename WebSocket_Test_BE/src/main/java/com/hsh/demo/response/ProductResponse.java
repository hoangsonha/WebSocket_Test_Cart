package com.hsh.demo.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse<T> {
    private String code;
    private String message;
    private T data;
}
