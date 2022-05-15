package com.dzm.EVChargeStationMgmt.controller;


import com.dzm.EVChargeStationMgmt.model.Company;
import com.dzm.EVChargeStationMgmt.model.Station;
import com.dzm.EVChargeStationMgmt.service.EVCSMCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ev/v1/")
public class EVCSMCompanyController {

    @Autowired
    private EVCSMCompanyService evcsmCompanyService;

    @PostMapping("/company")
    public ResponseEntity<Company> saveCompany(@RequestBody Company company){
        if(validate(company)) {
            evcsmCompanyService.processAndSaveCompany(company);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<Company>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/company")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company){
        if(validate(company)) {
       boolean isUpdated=  evcsmCompanyService.processAndUpdateCompany(company);
       if(isUpdated) {
           return new ResponseEntity<>(HttpStatus.ACCEPTED);
       }else{
           //record not exists
           return new ResponseEntity<Company>(HttpStatus.NOT_ACCEPTABLE);
       }
        }
        return new ResponseEntity<Company>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/company/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable("id") int companyId){
       boolean isDeleted= evcsmCompanyService.processAndDelete(companyId);
       if(isDeleted){
           return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
       }
        return new ResponseEntity<Company>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getAllCompanies(){
        List<Company> list= evcsmCompanyService.getAllCompanies();
        if(!list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<List<Company>>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/company/stations/{companyId}")
    public ResponseEntity<Map<Integer,List<Station>>> getAllStations(@PathVariable("companyId") int companyId){
    return   new ResponseEntity<>(evcsmCompanyService.getAllStations(companyId),HttpStatus.OK);
    }

    @GetMapping("/company/stationslist/{companyId}")
    public ResponseEntity<List<Station>> getAllStationsList(@PathVariable("companyId") int companyId){
        Map<Integer,List<Station>> mappedStations=evcsmCompanyService.getAllStationsV2(companyId);
        List<Station> alStations = mappedStations.entrySet().stream().flatMap(e->e.getValue().stream()).collect(Collectors.toList());
        return new ResponseEntity<>(alStations,HttpStatus.OK);

    }



    private boolean validate(Company company) {
         if((company.getName().length()==0 || company.getName()==null) &&
             company.getParentId()==0){
             return false;
         }
             return true;
    }
}
