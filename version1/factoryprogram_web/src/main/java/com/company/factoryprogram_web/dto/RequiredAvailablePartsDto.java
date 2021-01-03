package com.company.factoryprogram_web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequiredAvailablePartsDto {
    private int id;
    private String partName;
    private int partRef;
    private int requiredQty;
    private int availQty;


}
