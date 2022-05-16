package com.dzm.EVChargeStationMgmt.controller;

import com.dzm.EVChargeStationMgmt.exception.NoRecordsFoundException;
import com.dzm.EVChargeStationMgmt.model.Station;
import com.dzm.EVChargeStationMgmt.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ev/v1/consume/")
public class ConsumerController {

    @Autowired
    private  ConsumerService service;
    @RequestMapping("/stations/{radius}/{latitude}/{longitude}")
    public ResponseEntity<List<Station>> getNearByStationsList(@PathVariable("radius") int radius,@PathVariable("latitude") double latitude, @PathVariable("longitude") double longitude){
        Point p = new Point(latitude,longitude);
        List<Station> stations = service.findStations(radius,p);
        if(stations.isEmpty()){
         throw new NoRecordsFoundException("No Stations found within "+ radius +" radius.");
        }else {
            return new ResponseEntity<>(stations, HttpStatus.OK);
        }

    }


    @RequestMapping("/stations/{companyId}")
    public ResponseEntity<List<Station>> getAllStations(@PathVariable("companyId") int companyId){
       Optional<List<Station>> optionalStations = Optional.ofNullable(service.getAllStations(companyId));
        if(optionalStations.isPresent()) {
            return new ResponseEntity<>(optionalStations.get(),HttpStatus.OK);  }
        else{
            throw new NoRecordsFoundException("No Stations found for "+ companyId + " company");
        }
    }

}
