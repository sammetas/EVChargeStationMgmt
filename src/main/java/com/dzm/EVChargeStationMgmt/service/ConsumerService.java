package com.dzm.EVChargeStationMgmt.service;

import com.dzm.EVChargeStationMgmt.dao.EVCSMStationRepository;
import com.dzm.EVChargeStationMgmt.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumerService {
    RestTemplate restTemplate= new RestTemplate();
    @Autowired
    private EVCSMStationRepository evcsmStationRepository;
    @Value("${FULL_URL_FOR_STATIONS}")
    String url;

/*
        Get all stations using RespTemplate so that task 3 will be satisfied
 */
    public   List<Station> findStations(int radius, Point p) {

        List<Station> allStations=evcsmStationRepository.findAll();
     return    allStations.stream().filter(station ->{
            Point stationPos = new Point(station.getLatitude(),station.getLongitude());
            if(isNearStation(radius,p,stationPos)) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());

    }
    /*
     Calclulates the distance bw given point and each station point and if it fall below then true.
     Thanks to Google.
    */
    private boolean isNearStation(int radius, Point p1, Point p2) {
     double diffRadius =   6371 * Math.acos(
                Math.sin(p1.getX()) * Math.sin(p2.getX())
                        + Math.cos(p1.getX()) * Math.cos(p2.getX()) * Math.cos(p2.getY() - p1.getY()));

      return diffRadius<=radius;

    }

    public   List<Station> getAllStations(int companyId) {
        List<Station> stationList =null;
        ResponseEntity<Station[]> response = restTemplate.getForEntity(url +"/" +companyId,Station[].class);
        if(response.getStatusCode()== HttpStatus.OK){
            Station[]  stations =  response.getBody();
            stationList= Arrays.asList(stations);
            System.out.println(stationList);
       }
        return stationList;
    }
}
