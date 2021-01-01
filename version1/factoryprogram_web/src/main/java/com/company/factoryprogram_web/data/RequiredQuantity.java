package com.company.factoryprogram_web.data;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="config_parts")
@IdClass(ConfigurationPartsId.class)
public class RequiredQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
//    @JoinColumn(name="part_id", referencedColumnName = "id")
//    private Part part;

    @Id
    @ManyToOne(targetEntity = Configuration.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "configuration_id", referencedColumnName = "id")
    private Configuration configuration;

    @Id
    @ManyToOne(targetEntity = Part.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "part_id", referencedColumnName = "id")
    private Part part;

    @Column(name = "qty_req")
    private int qtyRequired;
}