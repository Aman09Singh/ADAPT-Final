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
import com.train.model.ModelTrip;
import com.train.model.TripSearchRequestBody;
import com.train.service.ServiceTrip;
@CrossOrigin(origins= "*", allowedHeaders="*")
@RestController
@RequestMapping("/train/trip")
public class ControllerTrip {

	@Autowired
	ServiceTrip tripService;
	
	@PostMapping("/add")
    public ResponseEntity<ModelTrip> addTrip(@RequestBody ModelTrip trip){
        Optional<ModelTrip> tripData = tripService.createTrip(trip);
        if (tripData.isPresent()) {
            return ResponseEntity.ok(tripData.get());
        } else throw new ApiRequestException("Bad Json");
    }

    @PutMapping("/update/{tripId}")
    public ResponseEntity<ModelTrip> updateTrip(@PathVariable String tripId, @RequestBody ModelTrip trip){
        Optional<ModelTrip> tripData = tripService.updateTrip(tripId, trip);
        if (tripData.isPresent()){
            return ResponseEntity.ok(tripData.get());
        }else throw new EntityNotFoundException("Trip with id " + tripId + " was not found.");
    }

    @DeleteMapping("/delete/{tripId}")
    public ResponseEntity<ModelTrip> deleteTrip(@PathVariable String tripId){
        Optional<ModelTrip> trip = tripService.deleteTrip(tripId);
        if (trip.isPresent()){
            return ResponseEntity.ok(trip.get());
        }else throw new EntityNotFoundException("Trip with id " + tripId + " was not found.");
    }

    @GetMapping("/get/{tripId}")
    public ResponseEntity<ModelTrip> getTrip(@PathVariable String tripId){
        System.out.println(tripId);
        Optional<ModelTrip> trip = tripService.getTrip(tripId);
        if(trip.isPresent()){
            return ResponseEntity.ok(trip.get());
        }else throw new EntityNotFoundException("Trip with id " + tripId + " was not found.");
    }

    @GetMapping("/get-trip-by-src-dest")
    public List<ModelTrip> getTripsBySrcAndDest(@RequestBody TripSearchRequestBody tripSearchRequestBody){
        return tripService.getAlTripsBySrcAndDest(tripSearchRequestBody.getSrcStationCode(), tripSearchRequestBody.getDestStationCode());
    }

    
    @GetMapping("/getAll")
    public ResponseEntity<List<ModelTrip>> getAllRoute(){
    	Optional<List<ModelTrip>> allTrip = tripService.getAll();
    	if(allTrip.isPresent()) {
    		return ResponseEntity.ok(allTrip.get());
    	}else {
    		throw new EntityNotFoundException("Trips not found");
    	}
    }
}
