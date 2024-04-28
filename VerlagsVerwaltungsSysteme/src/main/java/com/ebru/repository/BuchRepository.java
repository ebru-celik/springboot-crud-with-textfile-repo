package com.ebru.repository;

import com.ebru.model.Buch;
import com.ebru.service.DataInitializer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BuchRepository {

    private final DataInitializer dataInitializer;

    public BuchRepository(DataInitializer dataInitializer) {
        this.dataInitializer = dataInitializer;
    }

    public List<Buch> findAll(){
        return dataInitializer.loadBuecher();
    }

    public List<Buch> findBuecherByVerlagID(Long id){
        return dataInitializer.findBuecherByVerlagId(id);
    }
}
