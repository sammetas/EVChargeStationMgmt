package com.dzm.EVChargeStationMgmt.controller;

import com.dzm.EVChargeStationMgmt.service.EVCSMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ev/v1/")
public class EVCSMStationController {

    @Autowired
    private EVCSMService evcsmService;



}
