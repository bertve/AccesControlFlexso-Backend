package com.springBoot.keyAPI.model;

import java.io.Serializable;

public class KeyValidation implements Serializable {
    private Boolean succes;
    private String message,data;

    public KeyValidation(boolean succes, String message, String data) {
        this.succes = succes;
        this.message = message;
        this.data= data;
    }


    public Boolean getSucces() {
        return succes;
    }

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }
}
