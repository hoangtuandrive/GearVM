package com.gearvmstore.GearVM.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.CREATED;

public interface ResponseHandler {

    default <T> ResponseEntity<T> created(T content) {
        return response(content, CREATED);
    }

    default <T> ResponseEntity<T> response(T content, HttpStatus status) {
        return new ResponseEntity<>(content, status);
    }

}
