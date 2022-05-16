package com.dzm.EVChargeStationMgmt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.DataAmount;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Setter
@Getter
@ToString
@Table(name = "company")
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private  int id;
  @Column
  private  String name;
  @Column(name = "PARENT_COMPANY_ID")
  private int parentId;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "COMPANY_ID",referencedColumnName = "id")
   private List<Station> stations;
}
