package com.dzm.EVChargeStationMgmt.dao;

import com.dzm.EVChargeStationMgmt.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EVCSMRepository extends  JpaRepository<Company,Integer>{
}
