package com.ebru.service;

import com.ebru.model.Autor;
import com.ebru.repository.AutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class AutorServiceTest {
    @InjectMocks
    private AutorService autorService;

    @Mock
    private AutorRepository autorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllAutoren()  {
        // Mock
        List<Autor> mockAutoren = List.of(new Autor(1L, "Ebru", "Cinar", 1L),
                new Autor(2L, "Jane", "Smith", 2L));

        //repo
        when(autorRepository.findAll()).thenReturn(mockAutoren);

        // get datas from service
        List<Autor> autoren = autorService.getAllAutors();

        // result
        assertEquals(2, autoren.size());
        assertEquals("Ebru", autoren.get(0).getVorname());
        assertEquals("Jane", autoren.get(1).getVorname());
    }
}
