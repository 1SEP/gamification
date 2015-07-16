package ru.fsep.enterprise.fseper.controllers;

/**
 * Created by Ôëþð on 13.07.2015.
 */
public class ResponseObjectDto implements DataTransferObject {
    private String code;
    private String status;
    private DataTransferObject data;

    public ResponseObjectDto(String code, String status, DataTransferObject data) {
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

    public DataTransferObject getData() {
        return data;
    }
}
