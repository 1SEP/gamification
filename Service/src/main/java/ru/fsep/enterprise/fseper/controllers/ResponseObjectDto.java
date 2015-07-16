package ru.fsep.enterprise.fseper.controllers;

/**
 * Created by Ôëþð on 13.07.2015.
 */
public class ResponseObjectDto implements DataTransferObject {
    private String code;
    private String status;
    private Object data;

    public ResponseObjectDto(String code, String status, Object data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
}
