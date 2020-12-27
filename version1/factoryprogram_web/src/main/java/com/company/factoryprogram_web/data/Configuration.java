package com.company.factoryprogram_web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="configuration")
public class Configuration{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "c_c1")
    private int c1;
    @Column(name = "c_c2")
    private int c2;
    @Column(name = "c_c3")
    private int c3;
    @Column(name = "c_c4")
    private int c4;
    @Column(name = "c_c5")
    private int c5;
    @Column(name = "c_c6")
    private int c6;
    @Column(name = "c_c7")
    private int c7;
    @Column(name = "c_c8")
    private int c8;
    @Column(name = "c_c9")
    private int c9;
    @Column(name = "c_c10")
    private int c10;
    @Column(name = "c_c11")
    private int c11;
    @Column(name = "c_c12")
    private int c12;
    @Column(name = "c_c13")
    private int c13;
    @Column(name = "c_c14")
    private int c14;
}
