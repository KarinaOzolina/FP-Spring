package com.company.factoryprogram_web.data;

import lombok.*;

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

    @Column(name = "name")
    private String partName;
    @Column(name = "type")
    private String partType;
    @Column(name = "ref")
    private int partRefNumber;
    @Column(name = "price")
    private double partPrice;

}
