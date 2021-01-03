package com.company.factoryprogram_web.data;


import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="storage")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "part_id")
    private int partId;

//    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "id", referencedColumnName = "part_id")
//    private RequiredQuantity requiredQuantity;

    @Column(name = "avail_qty")
    private int availQty;







}
