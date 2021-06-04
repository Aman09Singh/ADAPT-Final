package com.train.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.train.exception.ApiRequestException;
import com.train.exception.EntityNotFoundException;
import com.train.model.ModelStation;
import com.train.service.ServiceStation;

@CrossOrigin(origins= "*", allowedHeaders="*")
@RestController
@RequestMapping("/train/station")
public class ControllerStation {

	@Autowired 
    ServiceStation stationsService;

    @PostMapping("/add")
    public ResponseEntity<ModelStation> createStation(@RequestBody ModelStation station){
        Optional<ModelStation> stationData = stationsService.createStation(station);
        if (stationData.isPresent()){
            return ResponseEntity.ok(stationData.get());
        }else throw new ApiRequestException("Bad JSON.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ModelStation> updateStation(@PathVariable String id, @RequestBody ModelStation station){
        Optional<ModelStation> stationData = stationsService.updateStation(id, station);
        if (stationData.isPresent()){
            return ResponseEntity.ok(stationData.get());
        }else throw new EntityNotFoundException("Station with id " + id + " was not found.");
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<ModelStation> deleteStation(@PathVariable String code){
        Optional<ModelStation> station = stationsService.deleteStation(code);
        if (station.isPresent()){
            return ResponseEntity.ok(station.get());
        }else throw new EntityNotFoundException("Station with id " + code + " was not found.");
    }

    @GetMapping("/get/{stationCode}")
    public ResponseEntity<ModelStation> getStation(@PathVariable String stationCode){
        Optional<ModelStation> station = stationsService.getStationByCode(stationCode);
        if (station.isPresent()){
            return ResponseEntity.ok(station.get());
        }else throw new EntityNotFoundException("Station with stationCode " + stationCode + " was not found.");
    }
	
    @GetMapping("/getAll")
    public ResponseEntity<List<ModelStation>> getAllStation(){
    	Optional<List<ModelStation>> allStation = stationsService.getAll();
    	if(allStation.isPresent()) {
    		return ResponseEntity.ok(allStation.get());
    	}
    	else {
    		throw new EntityNotFoundException("Stations are not found");
    	}
    }
    
    
}
