package com.ebru.model.dto;

import com.ebru.model.Autor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BuchDetailInfo {
    private Long buchID;
    private String titel;
    private double preis;
    private String isbn;
    private Long verlagID;
    List<Autor> autoren;
}
