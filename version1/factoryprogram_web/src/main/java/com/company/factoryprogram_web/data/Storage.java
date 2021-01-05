package com.company.factoryprogram_web.data;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "storage")
//@IdClass(ConfigurationStorageId.class)
public class Storage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @MapsId(value = "part_id")
//    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "part_id", referencedColumnName = "part_id")
//    private RequiredQuantity requiredQuantity;
//
//    @MapsId(value = "part_id")
//    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "part_id", referencedColumnName = "id")
//    private Part part;


    @Column(name = "part_id")
    private int partId;

    @Column(name = "avail_qty")
    private int availQty;


}
