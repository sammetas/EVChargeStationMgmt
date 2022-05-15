package com.dzm.EVChargeStationMgmt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.DataAmount;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Setter
@Getter
@Table(name = "company")
public class Company {
  @Transient
  List<Company> childrens;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private  int id;
  @Column
  private  String name;
  @Column(name = "PARENT_COMPANY_ID")
  private int parentId;
}
