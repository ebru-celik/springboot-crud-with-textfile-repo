package com.ebru.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Buch {
    private Long buchID;
    private String titel;
    private double preis;
    private String isbn;
    private Long verlagID;


}
