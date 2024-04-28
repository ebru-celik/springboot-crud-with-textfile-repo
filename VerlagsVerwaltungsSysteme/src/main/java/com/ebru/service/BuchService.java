package com.ebru.service;

import com.ebru.model.Buch;
import com.ebru.repository.BuchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuchService {

    private final BuchRepository buchRepository;

    public BuchService(BuchRepository buchRepository) {
        this.buchRepository = buchRepository;
    }

    public List<Buch> getAllBuecher(){
        return buchRepository.findAll();
    }
}
