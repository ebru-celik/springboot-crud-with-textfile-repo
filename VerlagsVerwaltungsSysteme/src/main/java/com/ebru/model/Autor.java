package com.ebru.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Autor {
    private Long autorID;
    private String vorname;
    private String name;
    private Long buchID;
}
