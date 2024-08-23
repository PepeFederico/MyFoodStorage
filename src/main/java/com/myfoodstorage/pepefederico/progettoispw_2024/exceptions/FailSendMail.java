package com.myfoodstorage.pepefederico.progettoispw_2024.exceptions;

import java.io.Serial;

public class FailSendMail extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;
    public FailSendMail(String message) {
        super(message);
    }
}
