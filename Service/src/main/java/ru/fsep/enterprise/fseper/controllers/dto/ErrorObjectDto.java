package ru.fsep.enterprise.fseper.controllers.dto;

/**
 * Created by Fedorov on 14.07.2015.
 */
public class ErrorObjectDto {
    private String code;
    private String status;
    private String message;
    private String data;

    public ErrorObjectDto(String code, String status, String message, String data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
