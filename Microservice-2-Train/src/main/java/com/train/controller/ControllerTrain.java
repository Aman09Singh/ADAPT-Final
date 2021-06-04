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
import com.train.model.ModelTrain;
import com.train.service.ServiceTrain;

@CrossOrigin(origins= "*", allowedHeaders="*")
@RestController	
@RequestMapping("/train")
public class ControllerTrain {

	@Autowired
	ServiceTrain trainService;
	
	 @PostMapping("/add")
	    public ResponseEntity<ModelTrain> addTrain(@RequestBody ModelTrain train) {
	        Optional<ModelTrain> trainData = trainService.createTrain(train);
	        if (trainData.isPresent()){
	            return ResponseEntity.ok(trainData.get());
	        } else throw new ApiRequestException("Bad JSON.");

	    }

	    @PutMapping("/update/{trainId}")
	    public ResponseEntity<ModelTrain> updateTrain(@PathVariable String trainId, @RequestBody ModelTrain train){
	        Optional<ModelTrain> trainData = trainService.updateTrain(trainId, train);
	        if (trainData.isPresent()){
	            return ResponseEntity.ok(trainData.get());
	        }else throw new EntityNotFoundException("Train with id " + trainId + " was not found.");
	    }

	    @DeleteMapping("/delete/{trainId}")
	    public ResponseEntity<ModelTrain> deleteTrain(@PathVariable String trainId){
	        Optional<ModelTrain> trip = trainService.deleteTrain(trainId);
	        if (trip.isPresent()){
	            return ResponseEntity.ok(trip.get());
	        }else throw new EntityNotFoundException("Train with id " + trainId + " was not found.");
	    }

	    @GetMapping("/get-by-id/{trainId}")
	    public ResponseEntity<ModelTrain> getTrainById(@PathVariable String trainId){
	        Optional<ModelTrain> train = trainService.getTrainById(trainId);
	        if (train.isPresent()){
	            return ResponseEntity.ok(train.get());
	        } else throw new EntityNotFoundException("Train with id " + trainId + " was not found.");
	    }

	    @GetMapping("/get/{number}")
	    public ResponseEntity<ModelTrain> getTrainByNumber(@PathVariable String number){
	        Optional<ModelTrain> train = trainService.getTrainByNumber(number);
	        if (train.isPresent()){
	            return ResponseEntity.ok(train.get());
	        } else throw new EntityNotFoundException("Train with number " + number + " was not found.");
	    }

	    @GetMapping("search/{fromStationCode}/{toStationCode}")
	    public List<ModelTrain> searchTrains(@PathVariable String fromStationCode, @PathVariable String toStationCode){
	        System.out.println(fromStationCode + " " + toStationCode);
	        return trainService.searchTrainsByFromStationCodeAndToStationCode(fromStationCode, toStationCode);
	    }
	    
	    @GetMapping("/getAll")
	    public ResponseEntity<List<ModelTrain>> getAllStation(){
	    	Optional<List<ModelTrain>> allTrains = trainService.getAll();
	    	if(allTrains.isPresent()) {
	    		return ResponseEntity.ok(allTrains.get());
	    	}
	    	else {
	    		throw new EntityNotFoundException("Stations are not found");
	    	}
	    }
	    
	    
}
