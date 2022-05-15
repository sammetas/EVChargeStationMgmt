package com.dzm.EVChargeStationMgmt.service;

import com.dzm.EVChargeStationMgmt.dao.EVCSMStationRepository;
import com.dzm.EVChargeStationMgmt.model.Company;
import com.dzm.EVChargeStationMgmt.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EVCSMStationService {
    @Autowired
    private EVCSMStationRepository stationRepository;

    public void processAndSaveStation(Station station) {
        stationRepository.save(station);
    }

    public boolean processAndUpdateCompany(Station station) {

        if(stationRepository.existsById(station.getId())){
                Optional<Station> existing=stationRepository.findById(station.getId());
                Station newStation=existing.get();
                newStation.setName(station.getName());
                newStation.setCompanyId(station.getCompanyId());
                newStation.setLatitude(station.getLatitude());
                newStation.setLongitude(station.getLongitude());
                stationRepository.save(newStation);
                return true;
            }
            return false;
        }

}
