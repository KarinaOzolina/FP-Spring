package com.company.factoryprogram_web.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "storage")
public class UpdatedPartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @Column(name = "part_id")
//    private int partId;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "part_id", referencedColumnName = "id")
    private RequiredQuantity requiredQuantity;

    @Column(name = "avail_qty")
    private int availQty;

}
