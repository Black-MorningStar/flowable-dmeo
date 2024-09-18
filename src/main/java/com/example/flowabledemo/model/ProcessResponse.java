package com.example.flowabledemo.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 君墨笑
 * @date 2024/9/18
 */
@Getter
@Setter
public class ProcessResponse<T> {

    private int code = 200;

    private T data;

    public static <T> ProcessResponse build(T data) {
        ProcessResponse<T> response = new ProcessResponse<>();
        response.setData(data);
        return response;
    }
}
