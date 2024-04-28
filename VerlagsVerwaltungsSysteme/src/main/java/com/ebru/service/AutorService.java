package com.ebru.service;

import com.ebru.model.Autor;
import com.ebru.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> getAllAutors(){
        return autorRepository.findAll();
    }
}
