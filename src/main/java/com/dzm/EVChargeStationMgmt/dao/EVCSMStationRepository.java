package com.dzm.EVChargeStationMgmt.dao;

import com.dzm.EVChargeStationMgmt.model.Company;
import com.dzm.EVChargeStationMgmt.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EVCSMStationRepository extends JpaRepository<Station,Integer> {

    List<Station> findByName(String name);
}
