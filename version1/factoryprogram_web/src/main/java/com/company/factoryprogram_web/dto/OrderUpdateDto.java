package com.company.factoryprogram_web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateDto {
    // private int requiredQty;
    // private int partId;
    private int id;
    private int availQty;
}
