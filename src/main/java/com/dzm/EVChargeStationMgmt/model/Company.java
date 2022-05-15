package com.dzm.EVChargeStationMgmt.model;

import jdk.jfr.DataAmount;
import lombok.*;

import javax.persistence.*;


@Entity
@Setter
@Getter
@Table(name = "company")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private  int id;
  @Column
  private  String name;
  @Column(name = "PARENT_COMPANY_ID")
  private int parentId;
}
