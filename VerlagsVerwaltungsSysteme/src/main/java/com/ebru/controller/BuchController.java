package com.ebru.controller;

import com.ebru.model.Buch;
import com.ebru.service.BuchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")  //  http://localhost:9090/api
public class BuchController {
    private final BuchService buchService;

    public BuchController(BuchService buchService) {
        this.buchService = buchService;
    }

    @GetMapping("/buecher")
    public ResponseEntity<List<Buch>> getAllBuecher(){
        List<Buch> buchlist = buchService.getAllBuecher();
        if (buchlist.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(buchlist, HttpStatus.OK);
    }
}
