package com.dzm.EVChargeStationMgmt.service;

import com.dzm.EVChargeStationMgmt.model.Station;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ConsumerService {
    RestTemplate restTemplate= new RestTemplate();
    @Value("${FULL_URL_FOR_STATIONS}")
    String url;

/*
        Get all stations using RespTemplate so that task 3 will be satisfied
 */
    public void findStations(int radius, double latitude, Point p) {



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
