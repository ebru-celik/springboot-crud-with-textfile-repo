package com.ebru.controller;

import com.ebru.model.Autor;
import com.ebru.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")  //  http://localhost:9090/api
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping("/autoren")
    public ResponseEntity<List<Autor>> getAllAutors(){
        List<Autor> autorList = autorService.getAllAutors();
        if (autorList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(autorList, HttpStatus.OK);
    }


}
