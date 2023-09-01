package com.lucas.crudapirest.exception;

import java.util.UUID;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(UUID id) {
        super(String.format("Registro não encontrado [%s]", id));
    }

}
