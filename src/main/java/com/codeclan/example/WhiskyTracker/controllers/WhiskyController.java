package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/whiskies")
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping
    public ResponseEntity<List<Whisky>> findWhiskies(@RequestParam(name="year", required = false) Integer year,
                                                     @RequestParam(name="distillery", required = false) Long distilleryId,
                                                     @RequestParam(name="age", required = false) Integer age,
                                                     @RequestParam(name="region", required = false) String region) {
        if (year != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByYear(year), HttpStatus.OK);
        } else if (distilleryId != null && age != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryIdAndAge(distilleryId, age), HttpStatus.OK);
        } else if (region != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryRegion(region), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

}
