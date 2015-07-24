package ru.fsep.enterprise.fseper.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.fsep.enterprise.fseper.controllers.dto.ResponseObjectDto;

/**
 * Created by Ôëþð on 13.07.2015.
 */
public class  ResponseBuilder {
    public static ResponseEntity<ResponseObjectDto> buildResponsePost(Object data) {
        ResponseObjectDto body = new ResponseObjectDto("201", "success", data);
        ResponseEntity<ResponseObjectDto> response = new ResponseEntity<ResponseObjectDto>(body, HttpStatus.CREATED);
        return response;
    }

    public static ResponseEntity<ResponseObjectDto> buildResponseGet(Object data) {
        ResponseObjectDto body = new ResponseObjectDto("200", "success", data);
        ResponseEntity<ResponseObjectDto> response = new ResponseEntity<ResponseObjectDto>(body, HttpStatus.OK);
        return response;
    }
    public static ResponseEntity<ResponseObjectDto> buildResponseDelete() {
        ResponseObjectDto body = new ResponseObjectDto("200", "success", null);
        ResponseEntity<ResponseObjectDto> response = new ResponseEntity<ResponseObjectDto>(body, HttpStatus.OK);
        return response;
    }
}
