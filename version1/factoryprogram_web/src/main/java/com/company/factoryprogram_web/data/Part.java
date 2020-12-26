package com.company.factoryprogram_web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="part")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "p_name")
    private String partName;
    @Column(name = "p_type")
    private String partType;
    @Column(name = "p_ref")
    private int partRefNumber;
    @Column(name = "p_price")
    private double partPrice;
}
