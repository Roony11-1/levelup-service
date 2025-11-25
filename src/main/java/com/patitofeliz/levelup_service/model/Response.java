package com.patitofeliz.levelup_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Response<T>
{
    private String message;
    private T data;
}
