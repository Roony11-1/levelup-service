package com.patitofeliz.levelup_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T>
{
    private boolean success;
    private String message;
    private String status;
    private T entity;
}
