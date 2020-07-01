package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/distilleries")
public class DistilleryController {
    
    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping
    public ResponseEntity<List<Distillery>> findDistilleries(@RequestParam(name = "region", required = false) String region,
                                                             @RequestParam(name = "whiskiesAge", required = false) Integer whiskiesAge) {
        if (region != null) {
            return new ResponseEntity<>(distilleryRepository.findDistilleriesByRegion(region), HttpStatus.OK);
        } else if (whiskiesAge != null) {
            return new ResponseEntity<>(distilleryRepository.findDistilleriesByWhiskiesAge(whiskiesAge), HttpStatus.OK);
        }
        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
    }

}
