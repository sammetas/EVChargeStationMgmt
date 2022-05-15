package com.dzm.EVChargeStationMgmt.controller;

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

@RestController
@RequestMapping("/ev/v1/consume/")
public class ConsumerController {

    @Autowired
    private  ConsumerService service;
    @RequestMapping("/stations/{radius}/{latitude}/{longitude}")
    public ResponseEntity<List<Station>> getNearByStationsList(@PathVariable("radius") int radius,@PathVariable("latitude") double latitude, @PathVariable("longitude") double longitude){
        Point p = new Point(latitude,longitude);
       return  new ResponseEntity<>(service.findStations(radius,p),HttpStatus.OK);

    }


    @RequestMapping("/stations/{companyId}")
    public ResponseEntity<List<Station>> getAllStations(@PathVariable("companyId") int companyId){

      return   new ResponseEntity<>(service.getAllStations(companyId), HttpStatus.OK);
    }

}
