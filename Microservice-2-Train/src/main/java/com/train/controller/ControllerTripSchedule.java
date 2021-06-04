package com.train.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.client.RestTemplate;

import com.train.exception.ApiRequestException;
import com.train.exception.EntityNotFoundException;
import com.train.model.ModelTrain;
import com.train.model.ModelTrip;
import com.train.model.ModelTripSchedule;
import com.train.service.ServiceTrain;
import com.train.service.ServiceTrip;
import com.train.service.ServiceTripSchedule;
@CrossOrigin(origins= "*", allowedHeaders="*")	
@RestController
@RequestMapping("/train/trip-schedule/")
public class ControllerTripSchedule {

	@Value("${codes.trip-schedule-active}")
    String activeCode;

    @Value(("${codes.trip-schedule-cancelled}"))
    String cancelCode;

    @Autowired
    ServiceTrip tripService;
    
    @Autowired
    ServiceTrain trainService;
    
    @Autowired
    ServiceTripSchedule tripSchedulesService;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/add")
    public ResponseEntity<ModelTripSchedule> createTripSchedule(@RequestBody ModelTripSchedule tripSchedule) {
        tripSchedule.setStatus(activeCode);
        Optional<ModelTripSchedule> tripScheduleData = tripSchedulesService.createTripSchedule(tripSchedule);
        if (tripScheduleData.isPresent()) {
            return ResponseEntity.ok(tripScheduleData.get());
        } else throw new ApiRequestException("Bad JSON");
    }

    
    
    
    @PutMapping("/update/{tripScheduleId}")
    public ResponseEntity<ModelTripSchedule> updateTripSchedule(@PathVariable String tripScheduleId, @RequestBody ModelTripSchedule tripSchedule) {
        Optional<ModelTripSchedule> tripScheduleData = tripSchedulesService.updateTripSchedule(tripScheduleId, tripSchedule);
        if (tripScheduleData.isPresent()) {
            return ResponseEntity.ok(tripScheduleData.get());
        } else throw new EntityNotFoundException("TripSchedule with id " + tripScheduleId + " was not found.");

    }

    @DeleteMapping("/delete/{tripScheduleId}")
    public ResponseEntity<ModelTripSchedule> deleteTripSchedule(@PathVariable String tripScheduleId) {
        Optional<ModelTripSchedule> tripSchedule = tripSchedulesService.deleteTripSchedule(tripScheduleId);
        if (tripSchedule.isPresent()) {
            return ResponseEntity.ok(tripSchedule.get());
        } else throw new EntityNotFoundException("TripSchedule with id " + tripScheduleId + " was not found.");
    }

    @GetMapping("/get/{tripScheduleId}")
    public ResponseEntity<ModelTripSchedule> getTripSchedule(@PathVariable String tripScheduleId) {
        Optional<ModelTripSchedule> tripSchedule = tripSchedulesService.getTripSchedule(tripScheduleId);
        if (tripSchedule.isPresent()){
            return ResponseEntity.ok(tripSchedule.get());
        } else throw new EntityNotFoundException("TripSchedule with id " + tripScheduleId + " was not found.");
    }

    @GetMapping("/get-trip-by-id/{tripId}/{date}")
    public ResponseEntity<ModelTripSchedule> getTripScheduleByTripIdAndDate(@PathVariable String tripId, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.NONE) LocalDate date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Optional<ModelTripSchedule> tripSchedule = tripSchedulesService.getTripScheduleByTripIdAndDate(tripId, date);
         System.out.println(DateTimeFormat.ISO.TIME);
        System.out.println("tripId & Date " + date.toString() + " " + tripSchedule);
        return tripSchedule.map(ResponseEntity::ok).orElse(null);
    }

    @GetMapping("/get-trip-by-id/{tripScheduleId}")
    public ResponseEntity<ModelTripSchedule> getTripScheduleByTripScheduleId(@PathVariable String tripScheduleId) {
        Optional<ModelTripSchedule> tripSchedule = tripSchedulesService.getTripSchedule(tripScheduleId);
        if (tripSchedule.isPresent()){
            return ResponseEntity.ok(tripSchedule.get());
        }else throw new EntityNotFoundException("TripSchedule with id " + tripScheduleId + " was not found.");
    }

    @GetMapping("/get-trip-schedules-by-date-and-stations/{fromCode}/{toCode}/{date}")
    public ResponseEntity<List<ModelTripSchedule>> getTripSchedulesByStationsAndDate(@PathVariable String fromCode, @PathVariable String toCode, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.NONE) LocalDate date){
       List<ModelTrip> trips = tripService.getAlTripsBySrcAndDest(fromCode, toCode);
        System.out.println(trips);
        if (trips != null) {
            List<ModelTripSchedule> existingTripSchedules = new ArrayList<>();
            for (ModelTrip trip : trips){
                Optional<ModelTripSchedule> tripSchedule = tripSchedulesService.getTripScheduleByTripIdAndDate(trip.getTripId(), date);
                if (tripSchedule.isPresent()){
                    existingTripSchedules.add(tripSchedule.get());
                }else {
                    Optional<ModelTrain> trainData = trainService.getTrainByNumber(trip.getTrainNo());
                    if (trainData.isPresent()){
                        ModelTrain train = trainData.get();
                        ModelTripSchedule newTripSchedule = new ModelTripSchedule(
                        		date,
                                train.getFirstAcSeats(),
                                train.getSecondAcSeats(),
                                train.getThirdAcSeats(),
                                train.getFirstClassSeats(),
                                train.getChairCarSeats(),
                                train.getSleeperSeats(),
                                trip.getTripId(),
                                train.getNumber(),
                                activeCode);
                        
                        Optional<ModelTripSchedule> tripScheduleData = tripSchedulesService.createTripSchedule(newTripSchedule);
                        tripScheduleData.ifPresent(existingTripSchedules::add);
                    }
                }
            }
            return ResponseEntity.ok(existingTripSchedules);
        }
        throw new EntityNotFoundException("No trips available!");
    }
    
    @PutMapping("/cancel-trip-schedule/{tripScheduleId}")
    public void cancelTripSchedule(@PathVariable String tripScheduleId) {
        Optional<ModelTripSchedule> tripSchedule = tripSchedulesService.getTripSchedule(tripScheduleId);
        if (tripSchedule.isPresent()) {
            tripSchedule.get().setStatus(cancelCode);
        }
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<ModelTripSchedule>> getAllRoutesSchedule(){
    	Optional<List<ModelTripSchedule>> allSchedule = tripSchedulesService.getAll();
    	if(allSchedule.isPresent()) {
    		return ResponseEntity.ok(allSchedule.get());
    	}else {
    		throw new EntityNotFoundException("Scheduled Routes are not found");
    	}
    }
}
