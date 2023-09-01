package com.lucas.crudapirest.handle;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ChangeSetPersister.NotFoundException.class })
    public ResponseEntity<Object> handleNotFound(ChangeSetPersister.NotFoundException notFoundException) {
        return new ResponseEntity<>(notFoundException.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleBusinessException(DataIntegrityViolationException dataIntegrityViolationException) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Este registro não pode ser removido pois já está sendo utilizado.");
    }

}
