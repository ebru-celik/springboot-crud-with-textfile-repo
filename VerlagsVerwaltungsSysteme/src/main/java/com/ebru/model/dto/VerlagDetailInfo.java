package com.ebru.model.dto;

import com.ebru.model.Buch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VerlagDetailInfo {
    private Long verlagID;
    private String VerlagName;
    List<BuchDetailInfo> buecher;


}
