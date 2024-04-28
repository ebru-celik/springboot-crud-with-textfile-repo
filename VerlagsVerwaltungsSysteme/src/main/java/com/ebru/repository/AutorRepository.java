package com.ebru.repository;

import com.ebru.model.Autor;
import com.ebru.service.DataInitializer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AutorRepository {
    private final DataInitializer dataInitializer;

    public AutorRepository(DataInitializer dataInitializer) {
        this.dataInitializer = dataInitializer;
    }

    public List<Autor> findAll(){
        return dataInitializer.loadAutoren();
    }

    public List<Autor> findAutorenByBuchID(Long id){
        return dataInitializer.findAutorenByBuchId(id);
    }
}
