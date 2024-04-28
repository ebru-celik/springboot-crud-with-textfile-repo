package com.ebru.repository;

import com.ebru.model.Verlag;
import com.ebru.service.DataInitializer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VerlagRepository {
    private final DataInitializer dataInitializer;

    public VerlagRepository(DataInitializer dataInitializer) {
        this.dataInitializer = dataInitializer;
    }

    public List<Verlag> findAll(){
        return dataInitializer.loadVerlage();
    }

    public Optional<Verlag> findVerlageByVerlagID(Long id){
        return dataInitializer.findVerlagByVerlagId(id);
    }
}
