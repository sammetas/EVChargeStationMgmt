package com.dzm.EVChargeStationMgmt.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private double latitude ;
    @Column
    private double longitude;
    @Column
    private int company_id;
}
