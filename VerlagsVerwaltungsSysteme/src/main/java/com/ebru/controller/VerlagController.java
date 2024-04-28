package com.ebru.controller;

import com.ebru.model.Verlag;
import com.ebru.model.dto.VerlagDetailInfo;
import com.ebru.service.VerlagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")  //  http://localhost:9090/api
public class VerlagController {

    // verlag service injected
    private final VerlagService verlagService;

    public VerlagController(VerlagService verlagService) {
        this.verlagService = verlagService;
    }

    @GetMapping("/verlage")
    public ResponseEntity<List<Verlag>> getAllVerlags(){
        List<Verlag> verlagslist = verlagService.getAllVerlage();
        if (verlagslist.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(verlagslist, HttpStatus.OK);
    }

    @GetMapping("/verlage-with-buecher-and-autoren")
    public ResponseEntity<List<VerlagDetailInfo>> getAllVerlageWithBooksAndAuthors() {
        List<VerlagDetailInfo> list = verlagService.getAllVerlageWithBuecherAndAutoren();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/verlag-with-buecher-and-autoren/{id}")
    public ResponseEntity<VerlagDetailInfo> getVerlagByIdWithBooksAndAuthors(@PathVariable("id") Long id){
        VerlagDetailInfo verlag_info = verlagService.getVerlagByIdWithBooksAndAuthors(id);
        if (verlag_info==null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(verlag_info, HttpStatus.OK);
    }

}
