package com.myfoodstorage.pepefederico.progettoispw_2024.exceptions;

import java.io.Serial;

public class ZeroOrderException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;
    public ZeroOrderException(String message) {
        super(message);
    }
}
