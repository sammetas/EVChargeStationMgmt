package com.dzm.EVChargeStationMgmt.controller;

import com.dzm.EVChargeStationMgmt.exception.NoRecordsFoundException;
import com.dzm.EVChargeStationMgmt.model.Company;
import com.dzm.EVChargeStationMgmt.model.Station;
import com.dzm.EVChargeStationMgmt.service.EVCSMStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ev/v1/")
public class EVCSMStationController {

    @Autowired
    private EVCSMStationService evcsmStationService;


    @PostMapping("/station")
    public ResponseEntity<Station> saveCompany(@RequestBody Station station){
        if(validate(station)) {
            evcsmStationService.processAndSaveStation(station);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<Station>(HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/station")
    public ResponseEntity<Company> updateCompany(@RequestBody Station station){
        if(validate(station)) {
            boolean isUpdated=  evcsmStationService.processAndUpdateStation(station);
            if(isUpdated) {
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }else{
                //record not exists
                return new ResponseEntity<Company>(HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return new ResponseEntity<Company>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/station/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable("id") int stationId){
        boolean isDeleted= evcsmStationService.processAndDelete(stationId);
        if(isDeleted){
            return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Company>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/stations")
    public ResponseEntity<List<Station>> getAllStations(){
        List<Station> list= evcsmStationService.getStations();
        if(!list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }else {
            throw new NoRecordsFoundException("No stations found to show");
        }

    }

    private boolean validate(Station station) {
        return true;
    }



}
