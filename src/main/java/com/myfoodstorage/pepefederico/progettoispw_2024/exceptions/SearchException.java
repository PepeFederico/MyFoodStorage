package com.myfoodstorage.pepefederico.progettoispw_2024.exceptions;

import java.io.Serial;

public class SearchException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;
    public SearchException(String message) {
        super(message);
    }
}
