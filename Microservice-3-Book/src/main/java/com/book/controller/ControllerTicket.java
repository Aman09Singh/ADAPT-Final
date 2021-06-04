package com.book.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

import com.book.exception.ApiRequestException;
import com.book.exception.TicketNotFoundException;
import com.book.model.BookTicketRequest;
import com.book.model.ModelTicket;
import com.book.model.ModelTrain;
import com.book.model.ModelTrip;
import com.book.model.ModelTripSchedule;
import com.book.service.ServiceTicket;


@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/booking")
public class ControllerTicket {

	
	@Value("${status.booked}")
    private String bookedStatus;

    @Value("${status.cancel}")
    private String cancelledStatus;

    @Value("${urls.get-trip-url}")
    private String getTripUrl;

    @Value("${urls.post-trip-schedule-url}")
    private String addTripSchedule;

    @Value("${urls.put-trip-schedule-url}")
    private String updateTripScheduleUrl;

    @Value("${urls.get-trip-schedule-url}")
    private String getTripScheduleUrl;

    @Value("${urls.get-train-url}")
    private String getTrainUrl;

    @Value("${codes.first-ac}")
    private String firstAcCode;

    @Value("${codes.second-ac}")
    private String secondAcCode;

    @Value(("${codes.third-ac}"))
    private String thirdAcSeats;

    @Value("${codes.first-class}")
    private String firstClass;

    @Value("${codes.chair-car}")
    private String chairCar;

    @Value("${codes.sleeper}")
    private String sleeper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ServiceTicket ticketService;

    @PostMapping("/book")
    public ResponseEntity<ModelTicket> createTicket(@RequestBody BookTicketRequest bookTicketRequest) throws ParseException {
        System.out.println(getTripUrl + "/" + bookTicketRequest.getTripId());
        System.out.println(bookTicketRequest);
        ModelTrip trip = restTemplate.getForObject(getTripUrl + "/" + bookTicketRequest.getTripId(), ModelTrip.class);
        System.out.println(trip);
        if (trip != null) {
        	ModelTripSchedule tripScheduleData = restTemplate.getForObject(getTripScheduleUrl + "/" + bookTicketRequest.getTripId() + "/" + bookTicketRequest.getTripDate(), ModelTripSchedule.class);
            System.out.println("TripSchedule " + tripScheduleData);
            ModelTrain train = restTemplate.getForObject(getTrainUrl + "/" + bookTicketRequest.getTrainId(), ModelTrain.class);
            if (tripScheduleData == null) {
                System.out.println("Train " + train);
                if (train != null) {
                    tripScheduleData = new ModelTripSchedule();
                    tripScheduleData.setTripId(bookTicketRequest.getTripId());
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    tripScheduleData.setTripDate(LocalDate.parse(bookTicketRequest.getTripDate(), dateTimeFormatter));
                    System.out.println(tripScheduleData.getTripDate());
                    tripScheduleData.setFirstAcAvailableSeats(train.getFirstAcSeats());
                    tripScheduleData.setSecondAcAvailableSeats(train.getSecondAcSeats());
                    tripScheduleData.setThirdAcAvailableSeats(train.getThirdAcSeats());
                    tripScheduleData.setChairCarAcAvailableSeats(train.getChairCarSeats());
                    tripScheduleData.setFirstClassAcAvailableSeats(train.getFirstClassSeats());
                    tripScheduleData.setSleeperAvailableSeats(train.getSleeperSeats());
                    tripScheduleData = restTemplate.exchange(addTripSchedule, HttpMethod.POST, new HttpEntity<ModelTripSchedule>(tripScheduleData), ModelTripSchedule.class).getBody();
                } else throw new ApiRequestException("Invalid trainId");
            }
            ModelTicket ticket = new ModelTicket();
            HashMap<String, Integer> seatsReq = bookTicketRequest.getSeats();
            HashMap<String, List<Integer>> assignedSeats = new HashMap<>();
            if (seatsReq.containsKey(firstAcCode)) {
                if (tripScheduleData.getFirstAcAvailableSeats() >= seatsReq.get(firstAcCode)) {
                    for (int i = 1; i <= seatsReq.get(firstAcCode); i++) {
                        if (assignedSeats.containsKey(firstAcCode)) {
                            assignedSeats.get(firstAcCode).add(train.getFirstAcSeats() - tripScheduleData.getFirstAcAvailableSeats() + i);
                        } else {
                            assignedSeats.put(firstAcCode, new ArrayList<>(List.of(train.getFirstAcSeats() - tripScheduleData.getFirstAcAvailableSeats() + i)));
                        }
                    }
                    tripScheduleData.setFirstAcAvailableSeats(tripScheduleData.getFirstAcAvailableSeats() - seatsReq.get(firstAcCode));
                }
            }
            if (seatsReq.containsKey(secondAcCode)) {
                if (tripScheduleData.getSecondAcAvailableSeats() >= seatsReq.get(secondAcCode)) {
                    for (int i = 1; i <= seatsReq.get(secondAcCode); i++) {
                        if (assignedSeats.containsKey(secondAcCode)) {
                            assignedSeats.get(secondAcCode).add(train.getSecondAcSeats() - tripScheduleData.getSecondAcAvailableSeats() + i);
                        } else {
                            assignedSeats.put(secondAcCode, new ArrayList<>(List.of(train.getSecondAcSeats() - tripScheduleData.getSecondAcAvailableSeats() + i)));
                        }
                    }
                    tripScheduleData.setSecondAcAvailableSeats(tripScheduleData.getSecondAcAvailableSeats() - seatsReq.get(secondAcCode));
                }
            }
            if (seatsReq.containsKey(thirdAcSeats)) {
                if (tripScheduleData.getThirdAcAvailableSeats() >= seatsReq.get(thirdAcSeats)) {
                    for (int i = 1; i <= seatsReq.get(thirdAcSeats); i++) {
                        if (assignedSeats.containsKey(thirdAcSeats)) {
                            assignedSeats.get(thirdAcSeats).add(train.getThirdAcSeats() - tripScheduleData.getThirdAcAvailableSeats() + i);
                        } else {
                            assignedSeats.put(thirdAcSeats, new ArrayList<>(List.of(train.getThirdAcSeats() - tripScheduleData.getThirdAcAvailableSeats() + i)));
                        }
                    }
                    tripScheduleData.setThirdAcAvailableSeats(tripScheduleData.getThirdAcAvailableSeats() - seatsReq.get(thirdAcSeats));
                }
            }
            if (seatsReq.containsKey(firstClass)) {
                if (tripScheduleData.getFirstClassAcAvailableSeats() >= seatsReq.get(firstClass)) {
                    for (int i = 1; i <= seatsReq.get(firstClass); i++) {
                        if (assignedSeats.containsKey(firstClass)) {
                            assignedSeats.get(firstClass).add(train.getFirstClassSeats() - tripScheduleData.getFirstClassAcAvailableSeats() + i);
                        } else {
                            assignedSeats.put(firstClass, new ArrayList<>(List.of(train.getFirstClassSeats() - tripScheduleData.getFirstClassAcAvailableSeats() + i)));
                        }
                    }
                    tripScheduleData.setFirstClassAcAvailableSeats(tripScheduleData.getFirstClassAcAvailableSeats() - seatsReq.get(firstClass));
                }
            }
            if (seatsReq.containsKey(chairCar)) {
                if (tripScheduleData.getChairCarAcAvailableSeats() >= seatsReq.get(chairCar)) {
                    for (int i = 1; i <= seatsReq.get(chairCar); i++) {
                        if (assignedSeats.containsKey(chairCar)) {
                            assignedSeats.get(chairCar).add(train.getChairCarSeats() - tripScheduleData.getChairCarAcAvailableSeats() + i);
                        } else {
                            assignedSeats.put(chairCar, new ArrayList<>(List.of(train.getChairCarSeats() - tripScheduleData.getChairCarAcAvailableSeats() + i)));
                        }
                    }
                    tripScheduleData.setChairCarAcAvailableSeats(tripScheduleData.getChairCarAcAvailableSeats() - seatsReq.get(chairCar));
                }
            }
            if (seatsReq.containsKey(sleeper)) {
                if (tripScheduleData.getSleeperAvailableSeats() >= seatsReq.get(sleeper)) {
                    for (int i = 1; i <= seatsReq.get(sleeper); i++) {
                        if (assignedSeats.containsKey(sleeper)) {
                            assignedSeats.get(sleeper).add(train.getSleeperSeats() - tripScheduleData.getSleeperAvailableSeats() + i);
                        } else {
                            assignedSeats.put(sleeper, Arrays.asList(train.getSleeperSeats() - tripScheduleData.getSleeperAvailableSeats() + i));
                        }
                    }
                    tripScheduleData.setSleeperAvailableSeats(tripScheduleData.getSleeperAvailableSeats() - seatsReq.get(sleeper));
                }
            }
            ticket.setCancellable(false);
            ticket.setJourneyDate(tripScheduleData.getTripDate());
            ticket.setTripScheduleId(tripScheduleData.getId());
            ticket.setEmail(bookTicketRequest.getEmail());
            String PNR = train.getNumber()+tripScheduleData.getTripDate().toString().replaceAll("-","")+assignedSeats.keySet().toArray()[0] + assignedSeats.values().stream().findFirst().get().get(0);
            System.out.println(PNR);
            ticket.setPNR(PNR);
            ticket.setSeats(assignedSeats);
            ticket.setFromStationCode(trip.getSourceStationCode());
            ticket.setToStationCode(trip.getDestinationStationCode());
            ticket.setPassengers(bookTicketRequest.getPassengers());
            ticket.setStatus(bookedStatus);
            System.out.println(tripScheduleData);
            restTemplate.exchange(updateTripScheduleUrl + "/" + tripScheduleData.getId(), HttpMethod.PUT, new HttpEntity<ModelTripSchedule>(tripScheduleData), ModelTripSchedule.class);
            return ResponseEntity.ok(ticketService.createTicket(ticket));
        } else throw new ApiRequestException("Invalid tripID");
    }

    @PutMapping("/update/{ticketId}")
    public ResponseEntity<ModelTicket> updateTicket(@PathVariable String ticketId, @RequestBody ModelTicket ticket) {
        Optional<ModelTicket> ticketOptional = ticketService.updateTicket(ticketId, ticket);
        if (ticketOptional.isPresent()){
            return ResponseEntity.ok(ticketOptional.get());
        }
        throw new TicketNotFoundException("Ticket with ticketId " + ticketId + " was not found.");
    }


    @PutMapping("/cancel/{PNR}")
    public ResponseEntity<ModelTicket> cancelTicket(@PathVariable String PNR) {
        Optional<ModelTicket> ticket = ticketService.getTicketByPNR(PNR);
        if (ticket.isPresent()) {
        	ModelTicket ticketData = ticket.get();
            HashMap<String, List<Integer>> seats = ticketData.getSeats();
            System.out.println(seats);
            ModelTripSchedule tripSchedule = restTemplate.getForObject(getTripScheduleUrl + "/" + ticketData.getTripScheduleId(), ModelTripSchedule.class);
            if (tripSchedule != null) {
                if (seats.containsKey(firstAcCode)) {
                    System.out.println(firstAcCode);
                    tripSchedule.setFirstAcAvailableSeats(tripSchedule.getFirstAcAvailableSeats() + seats.get(firstAcCode).size());
                }
                if (seats.containsKey(secondAcCode)) {
                    tripSchedule.setSecondAcAvailableSeats(tripSchedule.getSecondAcAvailableSeats() + seats.get(secondAcCode).size());
                }
                if (seats.containsKey(thirdAcSeats)) {
                    tripSchedule.setThirdAcAvailableSeats(tripSchedule.getThirdAcAvailableSeats() + seats.get(thirdAcSeats).size());
                }
                if (seats.containsKey(firstClass)) {
                    tripSchedule.setFirstClassAcAvailableSeats(tripSchedule.getFirstClassAcAvailableSeats() + seats.get(firstClass).size());
                }
                if (seats.containsKey(chairCar)) {
                    tripSchedule.setChairCarAcAvailableSeats(tripSchedule.getChairCarAcAvailableSeats() + seats.get(chairCar).size());
                }
                if (seats.containsKey(sleeper)) {
                    tripSchedule.setSleeperAvailableSeats(tripSchedule.getSleeperAvailableSeats() + seats.get(sleeper).size());
                }
                restTemplate.exchange(updateTripScheduleUrl + "/" + tripSchedule.getId(), HttpMethod.PUT, new HttpEntity<ModelTripSchedule>(tripSchedule), ModelTripSchedule.class);
            }
            System.out.println(tripSchedule);
            ticketData.setStatus(cancelledStatus);
            System.out.println(ticketData);
            return ResponseEntity.ok(ticketService.updateTicketByPNR(PNR, ticketData));
        }else throw new TicketNotFoundException("Ticket with PNR " + PNR + " was not found");
    }

    @GetMapping("/get/{PNR}")
    public ResponseEntity<ModelTicket> getTicket(@PathVariable String PNR) {
        Optional<ModelTicket> ticket = ticketService.getTicketByPNR(PNR);
        if(ticket.isPresent()){
            return ResponseEntity.ok(ticket.get());
        }else throw new TicketNotFoundException("Ticket with PNR " + PNR + " was not found");
    }
    
   
    @PutMapping("/cancel-all/{tripScheduleID}")
    public List<ModelTicket> cancelAllTicketsByTripScheduleId(@PathVariable String tripScheduleID) {
        return ticketService.getTicketsByTripScheduleId(tripScheduleID);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ModelTicket>> getAllTicket(){
    	Optional<List<ModelTicket>> allStation = ticketService.getAll();
    	if(allStation.isPresent()) {
    		return ResponseEntity.ok(allStation.get());
    	}
    	else {
    		throw new TicketNotFoundException("Stations are not found");
    	}
    }
    
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ModelTicket> getTicketById(@PathVariable String id){
        Optional<ModelTicket> ticket = ticketService.getTicketByTicketId(id);
        if (ticket.isPresent()){
            return ResponseEntity.ok(ticket.get());
        }else throw new TicketNotFoundException("Ticket with id " + id + " was not found");
    }
    
    @DeleteMapping("/delete/{ticketId}")
    public ResponseEntity<ModelTicket> deleteTicket(@PathVariable String ticketId){
        Optional<ModelTicket> ticket = ticketService.deleteTicket(ticketId);
        if (ticket.isPresent()){
            return ResponseEntity.ok(ticket.get());
        }else throw new ApiRequestException("Ticket not found");
    }
}
