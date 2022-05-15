package com.dzm.EVChargeStationMgmt.dao;

import com.dzm.EVChargeStationMgmt.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EVCSMStationRepository extends JpaRepository<Station,Integer> {

}
