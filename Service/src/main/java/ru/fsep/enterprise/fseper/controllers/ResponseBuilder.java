package ru.fsep.enterprise.fseper.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Ôëþð on 13.07.2015.
 */
public class  ResponseBuilder {
    public static ResponseEntity<ResponseObjectDto> buildResponsePut(DataTransferObject data) {
        ResponseObjectDto body = new ResponseObjectDto("201", "success", data);
        ResponseEntity<ResponseObjectDto> response = new ResponseEntity<ResponseObjectDto>(body, HttpStatus.CREATED);
        return response;
    }

    public static ResponseEntity<ResponseObjectDto> buildResponseGet(DataTransferObject data) {
        ResponseObjectDto body = new ResponseObjectDto("200", "success", data);
        ResponseEntity<ResponseObjectDto> response = new ResponseEntity<ResponseObjectDto>(body, HttpStatus.OK);
        return response;
    }
}
