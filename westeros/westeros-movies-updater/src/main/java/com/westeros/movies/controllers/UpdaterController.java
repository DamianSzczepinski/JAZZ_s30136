package com.westeros.movies.controllers;

import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public class UpdaterController {

    public ResponseEntity start(LocalDate from, LocalDate To){

        return ResponseEntity.ok().build();
    }

}
