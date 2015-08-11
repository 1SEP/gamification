package ru.fsep.enterprise.fseper.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.fsep.enterprise.fseper.controllers.dto.ResponseDto;

/**
 * Created by Ôëþð on 13.07.2015.
 */
public class  ResponseBuilder {
    public static ResponseEntity<ResponseDto> buildResponsePut(Object data) {
        ResponseDto body = new ResponseDto("201", "success", data);
        ResponseEntity<ResponseDto> response = new ResponseEntity<ResponseDto>(body, HttpStatus.CREATED);
        return response;
    }

    public static ResponseEntity<ResponseDto> buildResponseGet(Object data) {
        ResponseDto body = new ResponseDto("200", "success", data);
        ResponseEntity<ResponseDto> response = new ResponseEntity<ResponseDto>(body, HttpStatus.OK);
        return response;
    }
    public static ResponseEntity<ResponseDto> buildResponseDelete() {
        ResponseDto body = new ResponseDto("200", "success", null);
        ResponseEntity<ResponseDto> response = new ResponseEntity<ResponseDto>(body, HttpStatus.OK);
        return response;
    }
}
