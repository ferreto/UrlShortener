package com.rabchuk.UrlShortener.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST) // 400
public class ResourceDuplicatedException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public ResourceDuplicatedException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("Duplicated %s with %s : '%s'", resourceName, fieldName, fieldValue));
        // Duplicated Manufacturer with id : 1
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
