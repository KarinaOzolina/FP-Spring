package com.company.factoryprogram_web.data;


import lombok.*;

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

    @Id
    @ManyToOne(targetEntity = Configuration.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "configuration_id", referencedColumnName = "id")
    private Configuration configuration;

    @Id
    @ManyToOne(targetEntity = Part.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "part_id", referencedColumnName = "id")
    private Part part;

    @Id
    @MapsId(value = "part_id")
    @ManyToOne(targetEntity = Storage.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "part_id", referencedColumnName = "id")
    private Storage storage;

    @Column(name = "qty_req")
    private int qtyRequired;
}
